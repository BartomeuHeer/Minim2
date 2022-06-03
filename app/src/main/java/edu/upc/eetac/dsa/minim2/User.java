package edu.upc.eetac.dsa.minim2;


import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    private String userName;
    private String name;
    @SerializedName("avatar_url")
    private String avatarUrl;

    private String followers;
    private String following;

    public User(String userName, String followers, String following) {
        this.userName = userName;
        this.followers = followers;
        this.following = following;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }
}
