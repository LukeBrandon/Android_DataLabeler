package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataLabel implements Parcelable {
    private static final String TAG = "DataLabel";

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

    public DataLabel(JSONObject jsonObject){
        try{
            this.categoryName = jsonObject.getString("categoryName");
            this.description = jsonObject.getString("description");
            this.value = jsonObject.getDouble("value");

            // Unmarshal the questions from the JSON object
            ArrayList<Question> questionsFromJson = new ArrayList<>();
            JSONArray questionsArray = jsonObject.getJSONArray("questions");
            for(int i =0; i < questionsArray.length(); i++){
                questionsFromJson.add(new Question(questionsArray.getJSONObject(i)));
            }

            this.questions = questionsFromJson;
        } catch (JSONException e){
            Log.d(TAG, "DataLabel: JSON Object Creatiion Failure");
        }
        this.questions = questions;
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
