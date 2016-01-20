package com.example.oguzc.projectshop;

/**
 * Created by oguzc on 12/17/2015.
 */
public class Comment {

    private String commentString;
    private String ownerEmail;

    public Comment(String commentString, String ownerEmail) {
        this.commentString = commentString;
        this.ownerEmail = ownerEmail;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}