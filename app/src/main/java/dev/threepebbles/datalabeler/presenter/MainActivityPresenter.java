package dev.threepebbles.datalabeler.presenter;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.model.Question;
import dev.threepebbles.datalabeler.view.MainActivity;

public class MainActivityPresenter {
    private Activity context;

    public MainActivityPresenter(Activity context) {
        this.context = context;
    }

    // This has fake data, should come from the server
    public List<DataLabel> getDataLabels() {
        List<DataLabel> dataLabels = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Can anyone hear me??", Question.Type.MULTIPLE_CHOICE));

        dataLabels.add(new DataLabel("Animals", questions, "Sample Description", 69.69));

        return dataLabels;
    }
}
