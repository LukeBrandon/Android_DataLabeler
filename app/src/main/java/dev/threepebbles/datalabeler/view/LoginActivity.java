package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.Post;
import dev.threepebbles.datalabeler.remote.APIService;
import dev.threepebbles.datalabeler.remote.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private TextView emailField;
    private TextView passwordField;
    private Button loginButton;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = APIUtils.getAPIService();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            attemptLogin(email, password);
        });
    }

    public void attemptLogin(String email, String password) {
        apiService.loginPost(email, password).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                boolean canLogin = response.body().getLoginSuccessful();

                if (canLogin) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // TODO: Show the user that the login was invalid here
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "Login post request faliure!");
            }
        });
    }
}
