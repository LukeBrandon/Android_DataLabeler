package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable{

    String title;
    Type type;
    ArrayList<String> answers;

    public Question(String title, Type type){
        this.title = title;
        this.type = type;
        this.answers = new ArrayList<>();
        answers.add("Poopy 1");
        answers.add("Poopy 2");
        answers.add("Poopy 3");
    }

    public Question(Parcel in){
        this.title = in.readString();
        this.answers = new ArrayList<String>();
        in.readStringList(this.answers);
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getTitle(){
        return title;
    }


    public enum Type{
        MULTIPLE_CHOICE,
        SHORT_ANSWER,
        IMAGE_DRAW
    }

    /*
     * Method that is called when writing this class to a parcel
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.title);
        parcel.writeStringList(this.answers);
        // If adding more data to this class, write it to the parcel here so that it can be passed
        //  between activities via an intent
    }

    /*
     * Parcel Stuff
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

}
