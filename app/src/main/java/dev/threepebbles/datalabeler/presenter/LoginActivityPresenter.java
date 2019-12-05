package dev.threepebbles.datalabeler.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessaging;

import dev.threepebbles.datalabeler.model.Post;
import dev.threepebbles.datalabeler.utils.APIService;
import dev.threepebbles.datalabeler.utils.APIUtils;
import dev.threepebbles.datalabeler.utils.SharedPreferencesHandler;
import dev.threepebbles.datalabeler.view.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter {
    private static final String TAG = "LoginActivityPresenter";
    private static final String FCM_TOPIC_PREFIX = "newData_userId";

    Context context;
    LoginActivity view;
    APIService apiService;

    public LoginActivityPresenter(LoginActivity activity){
        this.view = activity;
        this.context = view.getApplicationContext();
        this.apiService = APIUtils.getAPIService();
    }

    public void attemptLogin(String email, String password){
        apiService.loginPost(email, password).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                boolean canLogin = response.body().getLoginSuccessful();
                int accountId = response.body().getAccountId();

                if (canLogin) {
                    // Save the accountId
                    SharedPreferencesHandler.saveAccountId(context, accountId);

                    // Subscribe to FCM
                    subscribeToFCM(SharedPreferencesHandler.getStoredAccountId(context));

                    // Update UI
                    view.setSpinnerVisiblity(View.INVISIBLE);
                    view.launchHomeActivity();
                } else {
                    // Show the user that the login was invalid
                    view.showLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                view.showInternetFailed();
            }
        });
    }

    private void subscribeToFCM(int accountId){
        // This sets up the firebase messaging by subscribing to the topic for this user
        FirebaseMessaging.getInstance().subscribeToTopic(FCM_TOPIC_PREFIX + accountId);

        Log.d(TAG, "onResponse: firebase token is: " + SharedPreferencesHandler.getStoredFirebaseToken(context));
        Log.d(TAG, "onNewToken: subscribed to topic: " + FCM_TOPIC_PREFIX + accountId);
    }

}
