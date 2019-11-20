package dev.threepebbles.datalabeler.presenter;

import android.util.Log;

import dev.threepebbles.datalabeler.model.DataLabelSubmission;
import dev.threepebbles.datalabeler.model.SimpleResponse;
import dev.threepebbles.datalabeler.remote.APIService;
import dev.threepebbles.datalabeler.remote.APIUtils;
import dev.threepebbles.datalabeler.view.LabelActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabelActivityPresenter {
    private static final String TAG = "LabelActivityPresenter";

    LabelActivity view;
    APIService apiService;

    public LabelActivityPresenter(LabelActivity activity){
        this.view = activity;
        this.apiService = APIUtils.getAPIService();
    }

    public void postAnswer(DataLabelSubmission dataLabelSubmission){
        this.apiService.dataLabelSubmit(dataLabelSubmission).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                boolean wasSuccess = response.body().getSuccess();

                Log.d(TAG, "onResponse: response from postAnswer: success=" + wasSuccess);

                // Update the view
                //view.runOnUiThread(() -> view.updateDataLabels(dataLabels));
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: getBlogPosts FAILED");
                t.printStackTrace();
            }
        });
    }
}
