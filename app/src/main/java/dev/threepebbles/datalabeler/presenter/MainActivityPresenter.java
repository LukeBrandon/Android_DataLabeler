package dev.threepebbles.datalabeler.presenter;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.model.Question;
import dev.threepebbles.datalabeler.view.MainActivity;

public class MainActivityPresenter implements MainContract.Presenter{
    private Activity context;

    public MainActivityPresenter(Activity context) {
        this.context = context;
    }

    // This has fake data, should come from the server
    public List<DataLabel> getDataLabels() {
        List<DataLabel> dataLabels = new ArrayList<>();

        ArrayList<String> answersYesNo = new ArrayList<>();
        answersYesNo.add("Yes");
        answersYesNo.add("No");

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Is this an animal?", Question.Type.MULTIPLE_CHOICE, answersYesNo));

        ArrayList<Question> trafficQuestions = new ArrayList<>();
        trafficQuestions.add(new Question("Is this a traffic light?", Question.Type.MULTIPLE_CHOICE, answersYesNo));

        ArrayList<Question> whiteQuestions = new ArrayList<>();
        whiteQuestions.add(new Question("Is this guy white?", Question.Type.MULTIPLE_CHOICE, answersYesNo));

        ArrayList<Question> asianQuestions = new ArrayList<>();
        asianQuestions.add(new Question("Is this an asian man?", Question.Type.MULTIPLE_CHOICE, answersYesNo));



        dataLabels.add(new DataLabel("Animals", questions, "Sample Description", 69.69));
        dataLabels.add(new DataLabel("Traffic Signs", trafficQuestions, "Traffic signs description", 420.69));
        dataLabels.add(new DataLabel("White people", whiteQuestions, "White description", 420.00));
        dataLabels.add(new DataLabel("Asians", asianQuestions, "Asian description", 69.42));


        return dataLabels;
    }

}
