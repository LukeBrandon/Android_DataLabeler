package dev.threepebbles.datalabeler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataLabelSubmission {

    @SerializedName("labelId")
    @Expose
    int labelId;

    @SerializedName("userId")
    @Expose
    int userId;

    @SerializedName("questionAnswers")
    @Expose
    List<Integer> questionAnswers;

    public DataLabelSubmission(int labelId, int userId, List<Integer> questionAnswers) {
        this.labelId = labelId;
        this.userId = userId;
        this.questionAnswers = questionAnswers;
    }

    // Getters and Setters
    public int getId() { return labelId; }

    public void setId(int labelId) { this.labelId = labelId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public List<Integer> getQuestionAnswers() { return questionAnswers; }

    public void setQuestionAnswers(List<Integer> questionAnswers) { this.questionAnswers = questionAnswers; }
}
