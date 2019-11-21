package dev.threepebbles.datalabeler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleResponse {

    @SerializedName("success")
    @Expose
    boolean success;

    public SimpleResponse(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() { return success; }

    public void setSuccess(boolean success) { this.success = success; }
}
