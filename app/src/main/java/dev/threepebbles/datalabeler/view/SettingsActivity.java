package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.threepebbles.datalabeler.R;

public class SettingsActivity extends AppCompatActivity {
    private View rewardRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        rewardRow = findViewById(R.id.rewardRow);
        rewardRow.setOnClickListener(v -> {
            Intent intent = new Intent(this, RewardActivity.class);
            startActivity(intent);
        });
    }
}
