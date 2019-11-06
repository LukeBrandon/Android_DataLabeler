package dev.threepebbles.datalabeler.presenter;

import android.util.Log;

import com.squareup.okhttp.Call;
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
import dev.threepebbles.datalabeler.model.Question;

public class MainActivityPresenter implements MainContract.Presenter{
    private static final String TAG = "MainActivityPresenter";

    private MainContract.View view;

    public MainActivityPresenter(MainContract.View view) {
        this.view = view;
    }

    // This has fake data, should make requests to the server
    public List<DataLabel> getDataLabels() {
        List<DataLabel> dataLabels = new ArrayList<>();

        // Callback for the HTTP request to get the datalabels
        Callback callback = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String message = e.getMessage();
                Log.w("failure_Response", message);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String stringResponse = response.body().string();

                try {
                    // Create the DataLabel ArrayList from the JSON object using json unmarshal constructors
                    JSONArray jsonArray = new JSONArray(stringResponse);
                    for(int i =0; i < jsonArray.length(); i++){
                        dataLabels.add(new DataLabel(jsonArray.getJSONObject(i)));
                    }
                } catch (JSONException e){
                    Log.d(TAG, "onResponse: JSON Parse failure");
                }
            }
        };

        // Get dataLabels from server
        getHttpResponse("https://vast-taiga-78775.herokuapp.com/labels", callback);

        return dataLabels;

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
