package com.sun.fragmenttabhosttest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.tab_content)
    FrameLayout mTabContent;
    @BindView(R.id.tab_host)
    FragmentTabHost mTabHost;

    private LayoutInflater mLayoutInflater;

    private Class fragmentArray[] = {
            IpFragment.class, MessageFragment.class, DiscoveryFragment.class, MineFragment.class
    };
    private String desc[] = {
            "作品", "消息", "发现", "我的"
    };
    private int images[] = {
            R.drawable.ip_selector, R.drawable.message_selector, R.drawable.discovery_selector, R.drawable.mine_selector
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupView();

    }

    private void setupView() {
        mLayoutInflater = LayoutInflater.from(this);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);
        for (int i = 0; i < images.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(desc[i]).setIndicator(setTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }

        TextView textview = mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.desc);
        textview.setTextColor(getResources().getColor(R.color.color_000000));

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                setTabHostTestColor(mTabHost);
            }
        });

    }

    private View setTabItemView(int index) {
        LinearLayout view = (LinearLayout) mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(images[index]);
        TextView textView = view.findViewById(R.id.desc);
        textView.setText(desc[index]);
        return view;
    }

    private void setTabHostTestColor(FragmentTabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView textview = tabHost.getTabWidget().getChildAt(i).findViewById(R.id.desc);
            if (tabHost.getCurrentTab() == i) {
                textview.setTextColor(getResources().getColor(R.color.color_000000));
            } else {
                textview.setTextColor(getResources().getColor(R.color.color_ffffff));
            }
        }
    }

}
