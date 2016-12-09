package com.example.haitran.chatchat.model;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class Friend {
    private String imageAvatar;
    private String userName;
    private String imageOnl;

    public Friend(String imageAvatar, String userName, String imageOnl) {
        this.imageAvatar = imageAvatar;
        this.userName = userName;
        this.imageOnl = imageOnl;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageOnl() {
        return imageOnl;
    }
}
