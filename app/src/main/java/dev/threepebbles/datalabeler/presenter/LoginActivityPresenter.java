package dev.threepebbles.datalabeler.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import dev.threepebbles.datalabeler.model.Post;
import dev.threepebbles.datalabeler.remote.APIService;
import dev.threepebbles.datalabeler.remote.APIUtils;
import dev.threepebbles.datalabeler.view.HomeActivity;
import dev.threepebbles.datalabeler.view.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter {
    private static final String TAG = "LoginActivityPresenter";

    LoginActivity view;
    APIService apiService;

    public LoginActivityPresenter(LoginActivity activity){
        this.view = activity;
        this.apiService = APIUtils.getAPIService();
    }

    public void attemptLogin(String email, String password){
        apiService.loginPost(email, password).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                boolean canLogin = response.body().getLoginSuccessful();

                if (canLogin) {
                    view.launchHomeActivity();
                } else {
                    // TODO: Show the user that the login was invalid here -- Better way??
                    view.showLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                view.showInternetFailed();
            }
        });
    }
}
