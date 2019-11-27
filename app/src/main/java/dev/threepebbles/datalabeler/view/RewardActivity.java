package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.threepebbles.datalabeler.R;

public class RewardActivity extends AppCompatActivity {
    private static final String TAG = "RewardActivity";

    Button redeemButton;
    TextView valueTextView;

    @Override
    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_reward);

        this.valueTextView = findViewById(R.id.valueTextView);
        this.redeemButton = findViewById(R.id.redeemButton);
        this.redeemButton.setOnClickListener(v -> {
            finish();
        });

        getDataFromIntent();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        double rewardValue = intent.getDoubleExtra(LabelActivity.VALUE_INTENT_TAG, 0.0);

        Log.d(TAG, "getDataFromIntent: rewardValue is " + rewardValue);

        // Show the value on the reward screen
        this.valueTextView.setText("$" + Double.toString(rewardValue));
    }


}
