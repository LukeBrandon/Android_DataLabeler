package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Question implements Parcelable {
    private static final String TAG = "Question";

    String title;
    Type type;
    ArrayList<String> answers;

    public Question(String title, Type type, ArrayList<String> answers) {
        this.title = title;
        this.type = type;
        this.answers = answers;
    }

    public Question(Parcel in) {
        this.title = in.readString();
        this.answers = new ArrayList<>();
        in.readStringList(this.answers);
    }

    public Question(JSONObject jsonObject) {
        try {
            this.title = jsonObject.getString("title");
            this.type = Type.valueOf(jsonObject.getString("type"));

            // Create the list of answers from the JSON object
            ArrayList<String> answersFromJson = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("answers");
            for( int i =0; i < jsonArray.length(); i++){
                answersFromJson.add(jsonArray.getString(i));
            }
            this.answers = answersFromJson;
        } catch(JSONException e){
            Log.d(TAG, "Question: JSON unmarshalling failure");
        }
    }

    public ArrayList<String> getAnswers () {
        return answers;
    }

    public String getTitle () {
        return title;
    }

    public void setAnswers (ArrayList < String > answers) {
        this.answers = answers;
    }

    public enum Type {
        MULTIPLE_CHOICE,
        SHORT_ANSWER,
        IMAGE_DRAW
    }

    /*
     * Method that is called when writing this class to a parcel
     */
    @Override
    public void writeToParcel (Parcel parcel,int i){

        parcel.writeString(this.title);
        parcel.writeStringList(this.answers);
        // If adding more data to this class, write it to the parcel here so that it can be passed
        //  between activities via an intent
    }

    /*
     * Parcel Stuff
     */
    @Override
    public int describeContents () {
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