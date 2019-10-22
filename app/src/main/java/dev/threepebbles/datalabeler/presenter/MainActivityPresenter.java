package dev.threepebbles.datalabeler.presenter;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.view.MainActivity;

public class MainActivityPresenter {
    private Activity context;

    public MainActivityPresenter(Activity context) {
        this.context = context;
    }

    public List<DataLabel> getDataLabels() {
        List<DataLabel> dataLabels = new ArrayList<>();
        dataLabels.add(new DataLabel());
        dataLabels.add(new DataLabel());
        dataLabels.add(new DataLabel());
        dataLabels.add(new DataLabel());
        dataLabels.add(new DataLabel());
        dataLabels.add(new DataLabel());
        dataLabels.add(new DataLabel());

        return dataLabels;
    }
}
