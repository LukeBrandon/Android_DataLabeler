package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.presenter.LoginActivityPresenter;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private LoginActivityPresenter presenter;

    private TextView emailField;
    private TextView passwordField;
    private Button loginButton;
    private FrameLayout spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginActivityPresenter(this);
        spinner = findViewById(R.id.progress_overlay);

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            // Tries to login with the current email and password
            setSpinnerVisiblity(View.VISIBLE);
            presenter.attemptLogin(emailField.getText().toString(), passwordField.getText().toString());
        });
    }

    public void launchHomeActivity() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void showLoginFailed(){
        setSpinnerVisiblity(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "Invalid Login Atttempt", Toast.LENGTH_SHORT).show();
    }

    public void showInternetFailed(){
        setSpinnerVisiblity(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "We are having trouble reaching the Internet, please check your connection and try again!", Toast.LENGTH_SHORT).show();
    }

    public void setSpinnerVisiblity(int visiblity) {
        if(visiblity == View.VISIBLE){
            this.loginButton.setVisibility(View.INVISIBLE);
            this.spinner.setVisibility(View.VISIBLE);
        } else {
            this.loginButton.setVisibility(View.VISIBLE);
            this.spinner.setVisibility(View.INVISIBLE);
        }

    }
}
