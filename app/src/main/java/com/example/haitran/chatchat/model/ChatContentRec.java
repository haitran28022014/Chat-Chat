package com.example.haitran.chatchat.model;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class ChatContentRec {

    private String contentChatRec;
    private String imageAvatarRec;
    private String timeRec;

    public ChatContentRec(String contentChat, String imageAvatar,String timeRec) {
        this.contentChatRec = contentChat;
        this.imageAvatarRec =imageAvatar;
        this.timeRec=timeRec;
    }

    public String getContentChatRec() {
        return contentChatRec;
    }

    public String getImageAvatarRec() {
        return imageAvatarRec;
    }

    public String getTimeRec() {
        return timeRec;
    }
}
