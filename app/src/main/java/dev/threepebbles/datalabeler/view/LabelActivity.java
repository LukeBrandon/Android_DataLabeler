package dev.threepebbles.datalabeler.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dev.threepebbles.datalabeler.R;

public class LabelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.label_layout);
    }
}
