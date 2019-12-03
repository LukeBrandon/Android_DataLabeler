package dev.threepebbles.datalabeler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.presenter.HomeActivityPresenter;

public class HomeActivity extends AppCompatActivity implements MainContract.View {
    private static final String TAG = "MainActivity";

    public static final String DATA_LABEL_INTENT_TAG = "DataLabel";

    private HomeActivityPresenter presenter;
    private RecyclerView recyclerView;
    public ItemAdapter adapter;
    public List<DataLabel> dataLabels;
    private ImageButton settingButton;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        presenter = new HomeActivityPresenter(this);

        settingButton = findViewById(R.id.settingsButton);
        settingButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });


        mySwipeRefreshLayout = findViewById(R.id.swiperefresh);
        /*
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
//                        myUpdateOperation();
                    }
                }
        );

        initializeRecyclerView();
        presenter.getDataLabelsForAccount();
        Log.d(TAG, "onCreate: set DataLabels to: " + dataLabels);
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.getDataLabelsForAccount();
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.labelItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    public void updateDataLabels(List<DataLabel> labels) {
        if(labels != null) {
            Log.d(TAG, "updateDataLabels: setting dataLabels to be: " + labels.toString());
            this.dataLabels = labels;

            // Reassigns new adapter for the new data
            this.adapter = new ItemAdapter(this, dataLabels, position -> {
                Intent intent = new Intent(this, LabelActivity.class);
                intent.putExtra(DATA_LABEL_INTENT_TAG, dataLabels.get(position));

                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);

            this.adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Nothing for you to label right now, check back later", Toast.LENGTH_LONG);
        }
    }

    public void showInternetFailed(){
        Toast.makeText(getApplicationContext(), "We are having trouble reaching the Internet, please check your connection and try again!", Toast.LENGTH_SHORT).show();
    }

}
