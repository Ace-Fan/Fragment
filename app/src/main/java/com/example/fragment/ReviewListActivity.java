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

public class ReviewListActivity extends ListActivity {
    //定义一个ListView的变量
    private ListView mRatingList;
    //实例化一个LIST集合
    private List<String> mReviewData = new ArrayList<String>();
    //定义操作数据的MovieRatingDataHelper类的变量：mDataHelper
    private MovieRatingDataHelper mDataHelper;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//绑定布局：reviewlist
        setContentView(R.layout.reviewlist);
//找到ListView组建
        mRatingList = (ListView)findViewById(android.R.id.list);
//创建一个MovieRatingDataHelper类的实例
        this.mDataHelper = new MovieRatingDataHelper(this,null,null,1);
//调用MovieRatingDataHelper类的selectName()，把姓名放入list集合，并返回赋给本类定义的集合：mReviewData
        mReviewData.addAll(this.mDataHelper.selectName());

//定义一个适配器，指定布局和数据源，数据源为mReviewData
        ArrayAdapter<String> mReviewList = new
                ArrayAdapter<String>(this,R.layout.listentry, mReviewData);
//设置ListActivity的数据源为适配器
        this.setListAdapter(mReviewList);

//ListActivity中的项点击事件
        mRatingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int
                    arg2, long arg3) {
                // 自动生成方法
                //定义一个意向：到DisplayReviewActivity类
                Intent mReviewListIntent = new
                        Intent(ReviewListActivity.this,
                        DisplayReviewActivity.class);
                //定义一个Bundle
                Bundle mBundle = new Bundle();
                //把用户点击的项的ID压入Bundle
                mBundle.putInt("id", arg2);
                //把Bundle:mBundle压入意向
                mReviewListIntent.putExtras(mBundle);
                //启动跳转
                startActivity(mReviewListIntent);
            }
        });
    }
}
