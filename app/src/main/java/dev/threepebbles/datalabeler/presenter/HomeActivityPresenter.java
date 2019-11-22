package dev.threepebbles.datalabeler.presenter;

import android.content.Context;

import java.util.List;

import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.remote.APIService;
import dev.threepebbles.datalabeler.remote.APIUtils;
import dev.threepebbles.datalabeler.sharedPreferences.SharedPreferencesHandler;
import dev.threepebbles.datalabeler.view.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivityPresenter implements MainContract.Presenter{
    private static final String TAG = "HomeActivityPresenter";

    private HomeActivity view;
    private Context context;
    private APIService apiService;

    public HomeActivityPresenter(HomeActivity activity) {
        this.view = activity;
        this.context = view.getApplicationContext();
        this.apiService = APIUtils.getAPIService();
    }

    // Returns all of the data labels
    public void getDataLabelsForAccount(){
        int id = SharedPreferencesHandler.getStoredAccountId(this.context);

        apiService.getDataLabels(id).enqueue(new Callback<List<DataLabel>>() {
            @Override
            public void onResponse(Call<List<DataLabel>> call, retrofit2.Response<List<DataLabel>> response) {
                List<DataLabel> dataLabels = response.body();

                // Update the recycler view to show the data
                view.updateDataLabels(dataLabels);
            }

            @Override
            public void onFailure(Call<List<DataLabel>> call, Throwable t) {
                view.showInternetFailed();
            }
        });
    }

}
