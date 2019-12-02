package dev.threepebbles.datalabeler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("totalRewards")
    @Expose
    private double totalRewards;

    public User(int id, double totalRewards) {
        this.id = id;
        this.totalRewards = totalRewards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(double totalRewards) {
        this.totalRewards = totalRewards;
    }
}
