package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Question implements Parcelable {
    private static final String TAG = "Question";

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("type")
    @Expose
    private QuestionType questionType;

    @SerializedName("answers")
    @Expose
    private ArrayList<String> answers;

    public Question(String title, QuestionType questionType, ArrayList<String> answers) {
        this.title = title;
        this.questionType = questionType;
        this.answers = answers;
    }

    public void setTitle(String title) { this.title = title; }

    public QuestionType getQuestionType() { return questionType; }

    public void setQuestionType(QuestionType questionType) { this.questionType = questionType; }

    public ArrayList<String> getAnswers () {
        return answers;
    }

    public String getTitle () {
        return title;
    }

    public void setAnswers (ArrayList < String > answers) {
        this.answers = answers;
    }

    public enum QuestionType {
        MULTIPLE_CHOICE,
        SHORT_ANSWER,
        IMAGE_DRAW
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.questionType == null ? -1 : this.questionType.ordinal());
        dest.writeStringList(this.answers);
    }

    protected Question(Parcel in) {
        this.title = in.readString();
        int tmpQuestionType = in.readInt();
        this.questionType = tmpQuestionType == -1 ? null : QuestionType.values()[tmpQuestionType];
        this.answers = in.createStringArrayList();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}