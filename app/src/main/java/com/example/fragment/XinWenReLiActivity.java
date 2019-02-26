package com.example.fragment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class XinWenReLiActivity extends ListActivity {
    //定义一个LlistView的变量
    private ListView mRatingList;
    //实例化一个list集合
    private List<String> mReviewData = new ArrayList<String>();
    //定义操作数据的MovieRatingDtahelper类的变量
    private XinWenMrDataHelper mDataHelper;
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.xinwenreviewlist);
        mRatingList = (ListView) findViewById(android.R.id.list);
        this.mDataHelper = new XinWenMrDataHelper(this);
//调用MovieRatingDataHelper类的selectName()，把姓名放入list集合，并返回赋给本类定义的集合：mReviewData
        mReviewData.addAll(this.mDataHelper.selectName());
        //定义一个适配器，指定布局和数据源，数据源为mReviewData
        ArrayAdapter<String> mReviewList = new
                ArrayAdapter<String>(this,R.layout.xinwenlistentry, mReviewData);
//设置ListActivity的数据源为适配器
        this.setListAdapter(mReviewList);

        //ListActivity中的项点击事件
        mRatingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int
                    arg2, long arg3) {
                //定义一个yixiang
                Intent mReViewListIntent = new Intent(XinWenReLiActivity.this, XinWenDiReActivity.class);
                //定义一个bundle
                Bundle mBundle = new Bundle();
                //把用户点击的项的id压入bundle
                mBundle.putInt("id", arg2);
                //把bundle:mbundle压入意向
                mReViewListIntent.putExtras(mBundle);
                //启动跳转
                startActivity(mReViewListIntent);
            }
        });
    }
}
