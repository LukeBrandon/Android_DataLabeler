package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DataLabel implements Parcelable {

    String categoryName;
    String description;
    Double value;
    ArrayList<Question> questions;

    public DataLabel(String category, ArrayList<Question> questions, String description, Double value){
        this.categoryName = category;
        this.questions = questions;
        this.description = description;
        this.value = value;
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
        this.description = in.readString();
        this.value = in.readDouble();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue(){
        return value;
    }

    /*
     * Method that is called when writing this class to a parcel
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.categoryName);
        parcel.writeTypedList(this.questions);
        parcel.writeString(this.description);
        parcel.writeDouble(this.value);
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
