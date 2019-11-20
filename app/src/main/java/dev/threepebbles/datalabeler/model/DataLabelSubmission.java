package dev.threepebbles.datalabeler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataLabelSubmission {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("questionAnswers")
    @Expose
    List<Integer> questionAnswers;

    public DataLabelSubmission(int id, List<Integer> questionAnswers) {
        this.id = id;
        this.questionAnswers = questionAnswers;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public List<Integer> getQuestionAnswers() { return questionAnswers; }

    public void setQuestionAnswers(List<Integer> questionAnswers) { this.questionAnswers = questionAnswers; }
}
