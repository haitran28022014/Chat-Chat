package com.example.haitran.chatchat.model;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class Message {
    private String imageLink;
    private String userName;
    private String content;

    public Message(String imageLink, String userName, String content) {
        this.imageLink = imageLink;
        this.userName = userName;
        this.content = content;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

}
