package com.example.haitran.chatchat.manager;

import com.example.haitran.chatchat.model.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class ChatManager {
    private static ChatManager instance;
    private List<Object> items;
    private ArrayList<Friend> arrFriend;

    public ChatManager() {
        items = new ArrayList<>();
        arrFriend = new ArrayList<>();
        arrFriend.add(new Friend("bg_girl", "Huong Nguyen", "chat_online"));
        arrFriend.add(new Friend("hai", "Hai Tran", "chat_online"));
        arrFriend.add(new Friend("bg_hoang", "Hoa Tran", "chat_online"));
        arrFriend.add(new Friend("bg_jun", "Hang Tran", "chat_offline"));
        arrFriend.add(new Friend("bg_salim", "Tu Tran", "chat_offline"));
        arrFriend.add(new Friend("bg_sun", "Hanh Tran", "chat_online"));
        arrFriend.add(new Friend("may", "Duc Tran", "chat_online"));
        arrFriend.add(new Friend("bg_jun", "Luong Ngoc", "chat_offline"));
        arrFriend.add(new Friend("bg_salim", "Tuong Ta", "chat_offline"));
    }

    public static ChatManager getInstance() {
        if (instance == null) {
            instance = new ChatManager();
        }
        return instance;
    }

    public ArrayList<Friend> getArrFriend() {
        return arrFriend;
    }

    public int countFriend() {
        return arrFriend.size();
    }

    public List<Object> getItems() {
        return items;
    }
}
