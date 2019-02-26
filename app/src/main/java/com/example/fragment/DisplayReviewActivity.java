package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayReviewActivity extends Activity {
    //变量声明
    private TextView mName;
    private TextView mPhone;
    private TextView mPassword;
    private TextView mAge;
    private TextView mSex;

    private String bname;
    private String bphone;
    private String bpassword;
    private String bage;
    private String bsex;

    private List<String> mReviewData = new ArrayList<String>();
    //定义了一个数据库的操作类的变量
    private MovieRatingDataHelper mDataHelper;


    //重写父类onCreate方法
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定布局
        setContentView(R.layout.review);
        mName=(TextView)findViewById(R.id.textView2);
        mPhone=(TextView)findViewById(R.id.textView4);
        mPassword=(TextView)findViewById(R.id.textView6);
        mAge=(TextView)findViewById(R.id.textView8);
        mSex=(TextView)findViewById(R.id.textView10);


        //接收传过的意向
        Intent mIntent = getIntent();
        //取出Bundle对象
        Bundle mBundle = mIntent.getExtras();
        //从Bundle中取出ID的值
        int id = mBundle.getInt("id");

        //创建一个数据库操作类的实例
        this.mDataHelper = new MovieRatingDataHelper(this,null,null,1);
        //mDataHelper.selectById(id+1)：获取具有特定ID的电影的数据，返回LIST
        //把返回来的list加入到mReviewData集合中
        mReviewData.addAll(this.mDataHelper.selectById(id+1));

        //通过调用用户定义的函数breakString()
        breakString(mReviewData.toString());
        // 使用各种字段的值填充显示评论活动中的小部件
        mName.setText(bname);
        mPhone.setText(bphone);
        mPassword.setText(bpassword);
        mAge.setText(bage);
        mSex.setText(bsex);

    }

    //breakString方法：分割还原由；隔离的整个字符串，并赋给对应的变量

    public void breakString(String str){
        bname=str.substring(1,str.indexOf(";"));
        str= str.substring(bname.length()+2, str.length());
        bphone = str.substring(0,str.indexOf(";"));
        str= str.substring(bphone.length()+1, str.length());
        bpassword = str.substring(0,str.indexOf(";"));
        str= str.substring(bpassword.length()+1, str.length());
        bage = str.substring(0,str.indexOf(";"));
        str= str.substring(bage.length()+1, str.length()-1);
        bsex = str;

    }

}
