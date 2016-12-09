package com.example.haitran.chatchat.model;

/**
 * Created by Hai Tran on 9/28/2016.
 */

public class ChatContentSend {
    private String contentChatSend;
    private String imageAvatarSend;
    private String timeSend;

    public ChatContentSend(String contentChat, String imageAvatar,String timeSend) {
        this.contentChatSend = contentChat;
        this.imageAvatarSend =imageAvatar;
        this.timeSend=timeSend;
    }

    public String getContentChatSend() {
        return contentChatSend;
    }

    public String getImageAvatarSend() {
        return imageAvatarSend;
    }

    public String getTimeSend() {
        return timeSend;
    }
}
