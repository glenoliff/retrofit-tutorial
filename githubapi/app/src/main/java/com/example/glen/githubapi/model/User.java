package com.example.glen.githubapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by glen on 8/28/17.
 */
public class User {

    @SerializedName("login")
    private String mLogin;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("name")
    private String mName;

    @SerializedName("followers")
    private String mFollowers;

    @SerializedName("following")
    private String mFollowing;

    @SerializedName("avatar_url")
    private String mAvatar;

    public User() {
        //Default constructor for serialization purposes
    }

    public User(String login, String email, String name, String followers, String following, String avatar) {
        mLogin = login;
        mEmail = email;
        mName = name;
        mFollowers = followers;
        mFollowing = following;
        mAvatar = avatar;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getName() {
        return mName;
    }

    public String getFollowers() {
        return mFollowers;
    }

    public String getFollowing() {
        return mFollowing;
    }

    public String getAvatar() {
        return mAvatar;
    }
}
