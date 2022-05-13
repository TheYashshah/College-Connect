package com.example.collegeconnect.Model;

public class Post {
    String description,imageadd,postid,userID;

    public Post() {
    }

    public Post(String description, String imageadd, String postid,String userID) {
        this.description = description;
        this.imageadd = imageadd;
        this.postid = postid;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageadd() {
        return imageadd;
    }

    public void setImageadd(String imageadd) {
        this.imageadd = imageadd;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
