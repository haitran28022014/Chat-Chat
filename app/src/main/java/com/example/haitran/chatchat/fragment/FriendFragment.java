package com.example.haitran.chatchat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.chatchat.App;
import com.example.haitran.chatchat.R;
import com.example.haitran.chatchat.adapter.FriendAdapter;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class FriendFragment extends Fragment {
    private View view;
    private RecyclerView listFriend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friend, container, false);
        initializeComponent();
        return view;
    }

    private void initializeComponent() {
        listFriend = (RecyclerView) view.findViewById(R.id.list_friend);
        FriendAdapter friendAdapter = new FriendAdapter();
        listFriend.setLayoutManager(new LinearLayoutManager(App.getContext()));
        listFriend.setAdapter(friendAdapter);
    }



}
