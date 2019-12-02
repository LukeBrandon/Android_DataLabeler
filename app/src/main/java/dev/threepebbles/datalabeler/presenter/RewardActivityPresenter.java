package dev.threepebbles.datalabeler.presenter;


import dev.threepebbles.datalabeler.model.User;
import dev.threepebbles.datalabeler.remote.APIService;
import dev.threepebbles.datalabeler.remote.APIUtils;
import dev.threepebbles.datalabeler.view.RewardActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardActivityPresenter {
    private static final String TAG = "LoginActivityPresenter";

    RewardActivity view;
    APIService apiService;

    public RewardActivityPresenter(RewardActivity activity){
        this.view = activity;
        this.apiService = APIUtils.getAPIService();
    }

    public void fetchUserRewardData(int id) {
        apiService.getUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                double reward = response.body().getTotalRewards();
                view.setRewardText(reward);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}