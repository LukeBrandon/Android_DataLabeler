package dev.threepebbles.datalabeler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import java.util.List;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.presenter.MainActivityPresenter;

public class HomeScreenActivity extends AppCompatActivity {
    private MainActivityPresenter presenter;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<DataLabel> dataLabels;
    private ImageButton settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        settingButton = findViewById(R.id.settingsButton);
        settingButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        presenter = new MainActivityPresenter(this);

        dataLabels = presenter.getDataLabels();

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.labelItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ItemAdapter(this, dataLabels, position -> {
            Intent intent = new Intent(this, LabelActivity.class);
//            int id = dataLabels.get(position).getId();
//            intent.putExtra("id", id);

            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}