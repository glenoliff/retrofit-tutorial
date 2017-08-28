package com.example.glen.githubapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by glen on 8/28/17.
 */
public class Repo {

    @SerializedName("name")
    private String mName;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("description")
    private String mDescription;

    public String getName() {
        return mName;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getDescription() {
        return mDescription;
    }
}
