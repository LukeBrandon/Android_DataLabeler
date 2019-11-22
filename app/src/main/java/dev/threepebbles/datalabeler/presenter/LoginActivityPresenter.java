package dev.threepebbles.datalabeler.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import dev.threepebbles.datalabeler.model.Post;
import dev.threepebbles.datalabeler.remote.APIService;
import dev.threepebbles.datalabeler.remote.APIUtils;
import dev.threepebbles.datalabeler.sharedPreferences.SharedPreferencesHandler;
import dev.threepebbles.datalabeler.view.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter {
    private static final String TAG = "LoginActivityPresenter";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ACCOUNT_ID = "accountId";

    Context context;
    LoginActivity view;
    APIService apiService;

    public LoginActivityPresenter(LoginActivity activity, Context context){
        this.view = activity;
        this.context = context;
        this.apiService = APIUtils.getAPIService();
    }

    public void attemptLogin(String email, String password){
        apiService.loginPost(email, password).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                boolean canLogin = response.body().getLoginSuccessful();
                int accountId = response.body().getAccountId();

                if (canLogin) {
                    SharedPreferencesHandler.saveAccountId(context, accountId);
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
