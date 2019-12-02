package dev.threepebbles.datalabeler.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.presenter.RewardActivityPresenter;
import dev.threepebbles.datalabeler.sharedPreferences.SharedPreferencesHandler;

public class RewardActivity extends AppCompatActivity {
    private static final String TAG = "RewardActivity";

    Button redeemButton;
    TextView valueTextView;
    RewardActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_reward);
        this.presenter = new RewardActivityPresenter(this);

        this.valueTextView = findViewById(R.id.valueTextView);
        this.redeemButton = findViewById(R.id.redeemButton);
        this.redeemButton.setOnClickListener(v -> {
            finish();
        });

        getDataFromIntent();

    }

    private void getDataFromIntent() {
        presenter.fetchUserRewardData(SharedPreferencesHandler.getStoredAccountId(this));
    }

    public void setRewardText(double rewardAmount) {
        String processedText = "$" + rewardAmount;
        this.valueTextView.setText(processedText);
    }

}
