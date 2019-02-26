package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class chazhao extends Activity {

	//定义一个ListView的变量
	private ListView mList;
	//实例化一个LIST集合
	private List<String> mReviewData = new ArrayList<String>();
	//定义操作数据的mydata类的变量：mDataHelper
	private Mydata mDataHelper;
	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chazhao);
   
      //找到ListView组建
        mList = (ListView)findViewById(R.id.list);
        //创建一个MovieRatingDataHelper类的实例
        this.mDataHelper = new Mydata(this);
        //调用MovieRatingDataHelper类的selectName()，把姓名放入list集合，并返回赋给本类定义的集合：mReviewData
        mReviewData.addAll(this.mDataHelper.selectName());
        List<String> ids = mDataHelper.selectId();//获取所有数据ID
        List<String> datas = new ArrayList<String>();
        if(ids !=null && ids .size()>0){
        	for(int i = 0;i<ids.size();i++){
        		datas.addAll(mDataHelper.selectById(ids.get(i)));
        	
        	 }
        }
      
        //定义一个适配器，指定布局和数据源，数据源为mReviewData
        ArrayAdapter<String> mReviewList = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datas);
        //设置ListActivity的数据源为适配器
        mList.setAdapter(mReviewList);
//        this.setListAdapter(mReviewList);
        //ListActivity中的项点击事件
        mList.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int 
                 arg2, long arg3) {
        		// 自动生成方法
        		//定义一个意向：到xinxi类
        		Intent mReviewListIntent = new Intent(chazhao.this,xinxi.class);
        		//定义一个Bundle
        		Bundle mBundle = new Bundle();
        		//把用户点击的项的ID压入Bundle key   
        		mBundle.putInt("id", arg2);
        		//把Bundle:mBundle压入意向
        		mReviewListIntent.putExtras(mBundle);
        		//启动跳转
        		startActivity(mReviewListIntent);
        	}
        });
        
        
    }

	public Object getReadableDatabase() {
		// TODO 自动生成的方法存根
		return null;
	}
}

