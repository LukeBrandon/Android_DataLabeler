package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.model.Question;

public class LabelActivity extends AppCompatActivity {
    private static final String TAG = "LabelActivity";

    TextView questionTitle;
    DataLabel data;
    ArrayList<Question> questions;
    int questionIndex;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        this.questionIndex = 0;
        this.questionTitle = findViewById(R.id.questionTitle);
        this.radioGroup = findViewById(R.id.radioGroup);

        init();
    }

    private void init(){
        Intent intent = getIntent();
        data = intent.getParcelableExtra("DataLabel");
        this.questions = data.getQuestions();


        updateUIForQuestionIndex();
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    /*
     * Updates the questionTitle, and radio buttons based on questionIndex
     *      - If going to next question, should update the UI to show next question
     */
    private void updateUIForQuestionIndex(){
        this.questionTitle.setText(this.questions.get(questionIndex).getTitle());

        // Defines the margins that are going to be applied to all of the radio buttons
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);

        for(int i = 0; i < this.questions.get(questionIndex).getAnswers().size(); i ++){

            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(View.generateViewId());
            radioButton.setLayoutParams(params);
            radioButton.setText(this.questions.get(questionIndex).getAnswers().get(i));

            this.radioGroup.addView(radioButton);
        }
    }
}
