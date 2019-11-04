package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DataLabel implements Parcelable {

    String categoryName;
    ArrayList<Question> questions;

    public DataLabel(String category, ArrayList<Question> questions){
        this.categoryName = category;
        this.questions = questions;
    }

    /*
     * Parcel constructor, used to convert from parcel back to DataLabel object when passing
     *      object via intent
     */
    protected DataLabel(Parcel in){
        this.categoryName = in.readString();
        //in.readTypedList(this.questions, Question.CREATOR);
        this.questions = new ArrayList<>();
        this.questions = in.createTypedArrayList(Question.CREATOR);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }


    /*
     * Method that is called when writing this class to a parcel
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.categoryName);
        parcel.writeTypedList(this.questions);
        // If adding more data to this class, write it to the parcel here so that it can be passed
        //  between activities via an intent
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<DataLabel> CREATOR = new Parcelable.Creator<DataLabel>() {
        @Override
        public DataLabel createFromParcel(Parcel in) {
            return new DataLabel(in);
        }

        @Override
        public DataLabel[] newArray(int size) {
            return new DataLabel[size];
        }
    };
}
