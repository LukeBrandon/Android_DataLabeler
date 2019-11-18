package dev.threepebbles.datalabeler.presenter;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.view.HomeActivity;

public class HomeActivityPresenter implements MainContract.Presenter{
    private static final String TAG = "HomeActivityPresenter";

    private HomeActivity view;

    public HomeActivityPresenter(HomeActivity activity) {
        this.view = activity;
    }

    // This has fake data, should make requests to the server
    public void getDataLabels() {
        List<DataLabel> dataLabels = new ArrayList<>();

        Callback callback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String message = e.getMessage();
                Log.w("failure_Response", message);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String message = response.body().string();

                try {
                    JSONObject objectWithArray = new JSONObject(message);
                    JSONArray jsonArray = objectWithArray.getJSONArray("labels");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        dataLabels.add(new DataLabel(jsonArray.getJSONObject(i)));
                    }

                } catch (JSONException e) {
                    Log.w(TAG, "onResponse: JSON Parse failure");
                }

                view.runOnUiThread(() -> view.updateDataLabels(dataLabels));
            }
        };

        // Get dataLabels from server
        getHttpResponse("https://vast-taiga-78775.herokuapp.com/labels", callback);
    }

    private void getHttpResponse(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(callback);
    }
}
