package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class xinxi extends Activity {

	//变量声明
	private TextView sname;
	private TextView stel;
	private TextView smoney;
	private TextView ssex;
	private String name;
	private String tel;
	private String sex;
	private String money;
	private String add;
	//定义了一个LIST集合
	private List<String> mReviewData;
	//定义了一个数据库的操作类的变量
	private Mydata mDataHelper;

	//重写父类onCreate方法
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xinxi);

		//获取组件
		sname = (TextView) findViewById(R.id.name);
	    ssex=(TextView)findViewById(R.id.sex);
	    stel = (TextView) findViewById(R.id.tel);
	    smoney = (TextView) findViewById(R.id.money);
	    Button bt1 = (Button)findViewById(R.id.button1);
	    Button bt2 = (Button)findViewById(R.id.button2);
	    //接收传过的意向
		Intent mIntent = getIntent();
		//取出Bundle对象
    	Bundle mBundle = mIntent.getExtras();
    	//从Bundle中取出name的值 key  value
		int id = mBundle.getInt("id");

	    //创建一个数据库操作类的实例
    	this.mDataHelper = new Mydata(this);
    	//mDataHelper.selectById(id+1)：获取具有特定ID的电影的数据，返回LIST
    	//把返回来的list加入到mReviewData集合中
    	mReviewData = this.mDataHelper.selectById((id+1)+"");
    	if(mReviewData!=null && mReviewData.size()>0){
    		String str = mReviewData.get(0);
    		String[] strs =  str.split(";");
    		if(strs.length>0){
    			sname.setText(strs[0]);
    		}
    		if(strs.length>1){
    			ssex.setText(strs[1]);
    		}
    		if(strs.length>2){
    			stel.setText(strs[2]);
    		}
    		if(strs.length>3){
    			smoney.setText(strs[3]);
    		}
    	}
	     
	 //通过调用用户定义的函数breakString()
		 breakString(mReviewData.toString());
		 bt1.setOnClickListener(new OnClickListener(){
		Button bt1 = (Button)findViewById(R.id.button1);
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			 if(bt1.isClickable()){
		      add = stel.getText().toString();
		      Toast.makeText(xinxi.this, "复制成功", Toast.LENGTH_SHORT).show();
		 }
			}});
		 
		 bt2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent mIntent = new Intent(xinxi.this,chazhao.class);
				startActivity(mIntent);
			}});
	   
	}


	private void breakString(String str) {
		// TODO 自动生成的方法存根
				
	}
}
