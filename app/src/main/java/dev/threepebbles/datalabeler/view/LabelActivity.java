package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.model.DataLabelSubmission;
import dev.threepebbles.datalabeler.model.Question;
import dev.threepebbles.datalabeler.presenter.LabelActivityPresenter;
import dev.threepebbles.datalabeler.sharedPreferences.SharedPreferencesHandler;

public class LabelActivity extends AppCompatActivity {
    private static final String TAG = "LabelActivity";
    public static final String VALUE_INTENT_TAG = "Value";

    private DataLabel dataLabel;
    private Iterator<Question> questionIterator;
    private Question.QuestionType currentQuestionType;
    private List<String> answers;

    private TextView questionTitle;
    private EditText shortAnswer;
    private RadioGroup radioGroup;
    private Button submitButton;
    private ProgressBar progressBar;
    private LabelActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        this.questionTitle = findViewById(R.id.questionTitle);
        this.shortAnswer = findViewById(R.id.labelShortAnswer);
        this.radioGroup = findViewById(R.id.radioGroup);
        this.submitButton = findViewById(R.id.submitButton);
        this.progressBar = findViewById(R.id.progressBar);
        this.answers = new ArrayList<>();

        submitButton.setOnClickListener(v -> {
            handleSubmit(this.currentQuestionType);
        });

        initializeDataFromIntent(getIntent());
        displayQuestionView(questionIterator.next());

        this.presenter = new LabelActivityPresenter(this);
    }

    private void initializeDataFromIntent(Intent intent) {
        dataLabel = intent.getParcelableExtra(HomeActivity.DATA_LABEL_INTENT_TAG);

        this.questionIterator = dataLabel.getQuestions().iterator();
        this.progressBar.setMax(dataLabel.getQuestions().size());
        this.progressBar.setProgress(0);
    }

    private void displayQuestionView(Question question) {
        this.questionTitle.setText(question.getTitle());
        this.currentQuestionType = question.getQuestionType();

        switch (currentQuestionType) {
            case MULTIPLE_CHOICE: {
                displayRadioButtons(question.getAnswers());
                break;
            }
            case SHORT_ANSWER:
                displayEditText();
                break;
            case IMAGE_DRAW:
                break;
            default:
                break;
        }
    }

    private void displayRadioButtons(List<String> answers) {
        this.shortAnswer.setVisibility(View.INVISIBLE);
        this.radioGroup.setVisibility(View.VISIBLE);
        this.radioGroup.removeAllViews();
        this.radioGroup.clearCheck();

        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);

        for (int i = 0; i < answers.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setLayoutParams(params);
            radioButton.setText(answers.get(i));

            this.radioGroup.addView(radioButton);
        }
    }

    private void displayEditText() {
        this.shortAnswer.setVisibility(View.VISIBLE);
        this.radioGroup.setVisibility(View.INVISIBLE);
    }

    private void handleSubmit(Question.QuestionType questionType) {
        boolean canContinue = false;

        switch (questionType) {
            case MULTIPLE_CHOICE:
                canContinue = submitMultipleChoiceAnswer();
                break;
            case SHORT_ANSWER:
                canContinue = submitShortAnswer();
                break;
            case IMAGE_DRAW:
                break;
            default:
                break;
        }

        if (questionIterator.hasNext() && canContinue) {
            displayQuestionView(questionIterator.next());
        } else if (!questionIterator.hasNext() && canContinue) {
            submitAllAnswers();
        }
    }

    private boolean submitMultipleChoiceAnswer() {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);

        try {
            answers.add(radioButton.getText().toString());

            this.progressBar.setProgress(this.progressBar.getProgress() + 1, true);

            return true;
        } catch (Exception err) {
            err.printStackTrace();
        }

        return false;
    }

    private boolean submitShortAnswer() {
        String answer = shortAnswer.getText().toString();

        if (!answer.isEmpty()) {
            answers.add(answer);

            return true;
        }

        return false;
    }

    private void submitAllAnswers() {
        DataLabelSubmission dataLabelSubmission = new DataLabelSubmission(
                this.dataLabel.getId(),
                SharedPreferencesHandler.getStoredAccountId(this),
                answers);

        presenter.postAnswer(dataLabelSubmission);
    }

    public void showSuccessMessage(){
        Toast.makeText(this, "Submitted Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void showInternetFailed(){
        Toast.makeText(getApplicationContext(), "We are having trouble reaching the Internet, please check your connection and try again!", Toast.LENGTH_SHORT).show();
    }
}
