package com.example.tryoutpas_23_35;

import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName("strTeam")
    private String name;

    @SerializedName("strBadge")
    private String badge;

    public String getName() {
        return name;
    }

    public String getBadge() {
        return badge;
    }
}
