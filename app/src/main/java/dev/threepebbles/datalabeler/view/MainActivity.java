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
    public static final String DATA_LABEL_INTENT_TAG = "DataLabel";
    
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

        initializeRecyclerView();
        presenter.getDataLabels();
        Log.d(TAG, "onCreate: set DataLabels to: " + dataLabels);

    }

    @Override
    protected void onResume(){
        // TODO: This does not get the newest stuff from the server
        super.onResume();
        // Get the data to be displayed
        presenter.getDataLabels();
        //this.adapter.notifyDataSetChanged();
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.labelItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    }

     public void updateDataLabels(List<DataLabel> labels) {
        //this.dataLabels.clear();
         Log.d(TAG, "updateDataLabels: setting datLabels to be: " + labels.toString());
         this.dataLabels = labels;

         // Reassigns new adapter for the new data
         this.adapter = new ItemAdapter(this, dataLabels, position -> {
             Intent intent = new Intent(this, LabelActivity.class);
             intent.putExtra(DATA_LABEL_INTENT_TAG, dataLabels.get(position));

             startActivity(intent);
         });
         recyclerView.setAdapter(adapter);

         this.adapter.notifyDataSetChanged();
    }

}
