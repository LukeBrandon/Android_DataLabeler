package dev.threepebbles.datalabeler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final String TAG = "MainActivity";
    
    private MainActivityPresenter presenter;
    private RecyclerView recyclerView;
    public ItemAdapter adapter;
    public List<DataLabel> dataLabels;
    private ImageButton settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);

        settingButton = findViewById(R.id.settingsButton);
        settingButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        this.dataLabels = presenter.getDataLabels();
        initializeRecyclerView();

    }

    @Override
    protected void onResume(){
        super.onResume();
        // Get the data to be displayed
        //presenter.getDataLabels();
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.labelItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ItemAdapter(this, dataLabels, position -> {
            Intent intent = new Intent(this, LabelActivity.class);
            intent.putExtra("DataLabel", dataLabels.get(position));

            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }

     public void updateDataLabels(List<DataLabel> labels) {
        dataLabels = labels;
        adapter.notifyDataSetChanged();
    }

}
