package dev.threepebbles.datalabeler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("loginSuccessful")
    @Expose
    private boolean loginSuccessful;

    public void setLoginSucessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public boolean getLoginSuccessful() {
        return this.loginSuccessful;
    }
}
