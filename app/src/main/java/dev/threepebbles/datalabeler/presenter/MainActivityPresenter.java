package dev.threepebbles.datalabeler.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import dev.threepebbles.datalabeler.model.DataLabel;

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
