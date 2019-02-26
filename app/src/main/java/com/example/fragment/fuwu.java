package com.example.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class fuwu extends Activity{
	
	private SQLiteDatabase db;
	private EditText name,tel,money;
	private RadioGroup sex;
	private RadioButton sexnan;
	private RadioButton sexnv;
	private Button but;
	private Context mContext;

	//适配器
	private ArrayAdapter<String> mAdapter;
		
	//定义一个数据库的操作类
	Mydata mDataHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuwu);

   
        name=(EditText) findViewById(R.id.name);
        

        tel=(EditText) findViewById(R.id.tel);
        money=(EditText) findViewById(R.id.money);
        sex=(RadioGroup) findViewById(R.id.sex);
        sexnan=(RadioButton) findViewById(R.id.nan);
        sexnv=(RadioButton) findViewById(R.id.nv);
  	    //保存按钮的事件绑定
  	    but = (Button) findViewById(R.id.pass);
  	    //创建数据库操作对象
  		mDataHelper =new Mydata(this);
  	    but.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				//获取组建中用户输入的值
				String names = name.getText().toString();
				String sex1s=  "";
				if(sexnan.isChecked()){
					sex1s = "男";
				}else{
					sex1s = "女";
				}
				
				String tels = tel.getText().toString();
				String moneys = money.getText().toString();
		        if(names.equals("") || tels.equals("")  || moneys.equals("") || sex1s.equals("")){
					Toast.makeText(fuwu.this,"请完善信息！", Toast.LENGTH_SHORT).show(); 		
				}else{
					if(isPhone()){
						mDataHelper.insert(names, sex1s, tels, moneys);
						//定义一个意向，跳转到ReviewListActivity
						Intent intent = new Intent(fuwu.this,chazhao.class);
						//跳转
						startActivity(intent);
						 // 显示提示信息
				         Toast.makeText(fuwu.this, "发布成功！", Toast.LENGTH_SHORT).show();
				         return ;
					}
					Toast.makeText(fuwu.this, "发布失败！！！", Toast.LENGTH_SHORT).show();
				}
				
			}
    });

   }
  

	//log
    public  boolean isPhone(){
    	EditText phone1=(EditText) findViewById(R.id.tel);
    	String num = phone1.getText().toString();
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (num.length() != 11) {
        	Toast.makeText(fuwu.this, "手机号码不满11位！", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher( num);
            boolean isMatch = m.matches();
            if (!isMatch) {
            	Toast.makeText(fuwu.this, "号码格式错误！", Toast.LENGTH_SHORT).show();
            }
            return isMatch;
        }
    }
  	    //end

	private void insertData(Object db, String name,
					String Double, String tel, String money) {
				// TODO 自动生成的方法存根
				db.equals(name);//("insert into use(name, sex, tel, money) values (?,?,?,?)",new String[]{name});	
			}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}