package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.model.DataLabelSubmission;
import dev.threepebbles.datalabeler.model.Question;
import dev.threepebbles.datalabeler.presenter.LabelActivityPresenter;
import dev.threepebbles.datalabeler.sharedPreferences.SharedPreferencesHandler;

public class LabelActivity extends AppCompatActivity {
    private static final String TAG = "LabelActivity";

    private LabelActivityPresenter presenter;

    private TextView questionTitle;
    private RadioGroup radioGroup;
    private Button submitButton;
    private DataLabel data;
    private List<Question> questions;
    private int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        this.presenter = new LabelActivityPresenter(this);

        this.questionIndex = 0;
        this.questionTitle = findViewById(R.id.questionTitle);
        this.radioGroup = findViewById(R.id.radioGroup);
        this.submitButton = findViewById(R.id.submitButton);
        this.submitButton.setOnClickListener(v -> { submitAnswer(); });

        getDataFromIntent();
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        data = intent.getParcelableExtra(HomeActivity.DATA_LABEL_INTENT_TAG);
        this.questions = data.getQuestions();

        updateUIForQuestionIndex();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    private void submitAnswer(){
        // Put together the list of answers
        List<Integer> answers = new ArrayList<>();

        // ID is set in the updateUIForQUestionIndex() function to be the index of the button (0 indexed)
        answers.add(radioGroup.getCheckedRadioButtonId());

        DataLabelSubmission submission = new DataLabelSubmission(data.getId(), SharedPreferencesHandler.getStoredAccountId(this), answers);
        presenter.postAnswer(submission);

        launchRewardActivity();
        // Finishing removes activity from back stack
        finish();
    }

    /*
     * Updates the questionTitle, and radio buttons based on questionIndex
     *      - If going to next question, should update the UI to show next question
     */
    private void updateUIForQuestionIndex(){
        Question questionToDisplay = this.questions.get(questionIndex);

        this.questionTitle.setText(questionToDisplay.getTitle());

        // Defines the margins that are going to be applied to all of the radio buttons
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);

        for(int i = 0; i < questionToDisplay.getAnswers().size(); i ++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setLayoutParams(params);
            radioButton.setText(questionToDisplay.getAnswers().get(i));

            this.radioGroup.addView(radioButton);
        }
    }

    private void launchRewardActivity(){
        Intent intent = new Intent(this, RewardActivity.class);
        startActivity(intent);
    }

    public void showSuccessMessage(){
        Toast.makeText(this, "Submitted Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void showInternetFailed(){
        Toast.makeText(getApplicationContext(), "We are having trouble reaching the Internet, please check your connection and try again!", Toast.LENGTH_SHORT).show();
    }


}
