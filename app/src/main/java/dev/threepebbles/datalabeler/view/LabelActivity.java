package dev.threepebbles.datalabeler.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        this.questionTitle =findViewById(R.id.questionTitle);

        init();
    }

    private void init(){
        Intent intent = getIntent();
        data = intent.getParcelableExtra("DataLabel");
        this.questions = data.getQuestions();

        // Just for testing
        this.questionTitle.setText(this.questions.get(0).getTitle());
    }
}
