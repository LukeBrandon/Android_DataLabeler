package dev.threepebbles.datalabeler.contact;

import java.util.List;

import dev.threepebbles.datalabeler.model.DataLabel;

public interface MainContract {

    interface View {
        void updateDataLabels(List<DataLabel> labels);


    }

    interface Presenter {
        void getDataLabelsForAccount();


    }
}
