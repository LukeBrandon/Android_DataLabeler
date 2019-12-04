package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

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
    private Type type;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("answers")
    @Expose
    private ArrayList<String> answers;

    public Question(String title, Type type, String imageUrl, ArrayList<String> answers) {
        this.title = title;
        this.type = type;
        this.imageUrl = imageUrl;
        this.answers = answers;
    }

    protected Question(Parcel in) {
        title = in.readString();
        imageUrl = in.readString();
        answers = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeStringList(answers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public void setTitle(String title) { this.title = title; }
    public String getTitle () {
        return title;
    }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public ArrayList<String> getAnswers () {
        return answers;
    }
    public void setAnswers (ArrayList < String > answers) {
        this.answers = answers;
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public enum Type {
        MULTIPLE_CHOICE,
        SHORT_ANSWER,
        IMAGE_DRAW
    }

}