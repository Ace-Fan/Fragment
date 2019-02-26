package com.example.fragment;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class XinWenMainActivity extends TabActivity{
    TabHost tabhost;
    TabSpec spec1;
    TabSpec spec2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xinwen_main);
        tabhost=(TabHost)findViewById(android.R.id.tabhost);
        tabhost.setup();
        spec1=tabhost.newTabSpec("Tab 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("发布消息",getResources().getDrawable(R.drawable.ic_launcher));
        Intent in1 = new Intent(this,XinWenMrActivity.class);
        spec1.setContent(in1);
        spec2 = tabhost.newTabSpec("Tab 2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("查看消息",getResources().getDrawable(R.drawable.ic_launcher));
        Intent in2 = new Intent(this,XinWenReLiActivity.class);
        spec2.setContent(in2);
        tabhost.addTab(spec1);
        tabhost.addTab(spec2);
    }
}
