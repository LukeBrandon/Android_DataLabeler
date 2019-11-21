package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.Post;
import dev.threepebbles.datalabeler.presenter.LoginActivityPresenter;
import dev.threepebbles.datalabeler.remote.APIService;
import dev.threepebbles.datalabeler.remote.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private LoginActivityPresenter presenter;

    private TextView emailField;
    private TextView passwordField;
    private Button loginButton;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginActivityPresenter(this);
        apiService = APIUtils.getAPIService();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            // Tries to login with the current email and password
            presenter.attemptLogin(emailField.getText().toString(), passwordField.getText().toString());
        });
    }

    public void launchHomeActivity(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void showLoginFailed(){
        Toast.makeText(getApplicationContext(), "Invalid Login Atttempt", Toast.LENGTH_SHORT).show();
    }

    public void showInternetFailed(){
        Toast.makeText(getApplicationContext(), "We are having trouble reaching the Internet, please check your connection and try again!", Toast.LENGTH_SHORT).show();
    }
}
