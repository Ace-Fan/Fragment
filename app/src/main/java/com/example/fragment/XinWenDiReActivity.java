package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class XinWenDiReActivity extends Activity {
    private TextView Ttile;
    private TextView Tstyle;
    private TextView Tdate;
    private TextView Tpersonname;
    private TextView Tcontent;


    private String Ctitle;
    private String Cstyle;
    private String Cdate;
    private String Cpersonname;
    private String Ccontent;
    //定义一个list集合
    private List<String> mReviewData=new ArrayList<String>();
    //定义一个数据库的操作类的变量
    private XinWenMrDataHelper mDataHelper;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //绑定布局
        setContentView(R.layout.xianshi);
        Ttile = (TextView) findViewById(R.id.textView2);
        Tstyle = (TextView) findViewById(R.id.textView4);
        Tdate = (TextView) findViewById(R.id.textView6);
        Tpersonname = (TextView) findViewById(R.id.textView8);
        Tcontent = (TextView) findViewById(R.id.textView10);

//接收传过来的意向
        Intent mIntent = getIntent();
        //取出bundle对象
        Bundle mBundle = mIntent.getExtras();
        //从bundle中取出id的值
        int id = mBundle.getInt("id");

        //创建一个数据库操作类的实例
        this.mDataHelper = new XinWenMrDataHelper(this);
        mReviewData.addAll(this.mDataHelper.selectById(id + 1));

        //通过调用用户定义的函数breakString（）
        breakString(mReviewData.toString());
        //使用各种字段的值填充显示评论活动的小部件
        Ttile.setText(Ctitle);
        Tstyle.setText(Cstyle);
        Tdate.setText(Cdate);
        Tpersonname.setText(Cpersonname);
        Tcontent.setText(Ccontent);
    }

    //breakString:分割还原由
    public void breakString(String str) {
        Ctitle = str.substring(1, str.indexOf(";"));
        str = str.substring(Ctitle.length() + 2, str.length());
        Cstyle = str.substring(0, str.indexOf(";"));
        str = str.substring(Cstyle.length() + 1, str.length());
        Cdate = str.substring(0, str.indexOf(";"));
        str = str.substring(Cdate.length() + 1, str.length());
        Cpersonname = str.substring(0, str.indexOf(";"));
        str = str.substring(Cpersonname.length() + 1, str.length() - 1);
        Ccontent = str;
    }
}
