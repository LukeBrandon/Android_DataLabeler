package dev.threepebbles.datalabeler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataLabel implements Parcelable {
    private static final String TAG = "DataLabel";

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("categoryName")
    @Expose
    String categoryName;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("value")
    @Expose
    Double value;

    @SerializedName("imageUrl")
    @Expose
    String imageUrl;

    @SerializedName("questions")
    @Expose
    List<Question> questions;

    public DataLabel(int id, String categoryName, String description, Double value, List<Question> questions, String imageUrl) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.value = value;
        this.imageUrl = imageUrl;
        this.questions = questions;
    }

    protected DataLabel(Parcel in) {
        id = in.readInt();
        categoryName = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        if (in.readByte() == 0) {
            value = null;
        } else {
            value = in.readDouble();
        }
        questions = in.createTypedArrayList(Question.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(categoryName);
        dest.writeString(description);
        dest.writeString(imageUrl);
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(value);
        }
        dest.writeTypedList(questions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataLabel> CREATOR = new Creator<DataLabel>() {
        @Override
        public DataLabel createFromParcel(Parcel in) {
            return new DataLabel(in);
        }

        @Override
        public DataLabel[] newArray(int size) {
            return new DataLabel[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Double getValue() { return value; }

    public void setValue(Double value) { this.value = value; }

    public List<Question> getQuestions() { return questions; }

    public void setQuestions(List<Question> questions) { this.questions = questions; }

}
