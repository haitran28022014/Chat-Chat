package com.example.haitran.chatchat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.haitran.chatchat.fragment.FriendFragment;
import com.example.haitran.chatchat.fragment.ChatFragment;
import com.example.haitran.chatchat.fragment.MessageFragment;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 9/26/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> pages;


    public PagerAdapter(FragmentManager fm) {
        super(fm);
        pages=new ArrayList<>();
        pages.add(new ChatFragment());
        pages.add(new FriendFragment());
        pages.add(new MessageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
