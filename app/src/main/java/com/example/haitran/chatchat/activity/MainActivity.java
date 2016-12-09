package com.example.haitran.chatchat.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.haitran.chatchat.R;
import com.example.haitran.chatchat.adapter.PagerAdapter;
import com.example.haitran.chatchat.manager.StringManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private static final String TAG = "MainActivity";
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    /**
     * Đây là class MainActivity chính chứa 3 fragment ChatFragment, FriendFragment,MessageFragment.
     * E sẽ xây dựng ra khung giao diện khi vừa đăng nhập vào
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeComponent();
        initializeListener();
    }


    private void initializeComponent() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_friend));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_page));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        String phone = getIntent().getStringExtra(StringManager.PHONE);
        Log.d(TAG, phone);
        Bundle bundle = new Bundle();
        bundle.putString(StringManager.PHONE, phone);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.getItem(0).setArguments(bundle);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initializeListener() {
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        if (tab.getPosition() == 0) {
            tab.setIcon(R.drawable.ic_home_white);
        }
        if (tab.getPosition() == 1) {
            tab.setIcon(R.drawable.ic_friend_white);
        }

        if (tab.getPosition() == 2) {
            tab.setIcon(R.drawable.ic_page_white);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
            tab.setIcon(R.drawable.ic_home);
        }
        if (tab.getPosition() == 2) {
            tab.setIcon(R.drawable.ic_page);
        }
        if (tab.getPosition() == 1) {
            tab.setIcon(R.drawable.ic_friend);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
