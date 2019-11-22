package dev.threepebbles.datalabeler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("loginSuccessful")
    @Expose
    private boolean loginSuccessful;

    @SerializedName("accountId")
    @Expose
    private int accountId;

    public void setLoginSucessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public boolean getLoginSuccessful() {
        return this.loginSuccessful;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
