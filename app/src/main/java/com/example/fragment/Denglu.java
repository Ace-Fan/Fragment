package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Denglu extends Activity {
    private Button bt1;
    private Button bt2;
    private MovieRatingDataHelper mDataHelper;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.denglu);
       //创建数据库
        mDataHelper=new MovieRatingDataHelper(this,"MovieRating.db",null,1);
        bt1=(Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtUserPhone;
                EditText edtPwd;
                edtUserPhone=(EditText)findViewById(R.id.userphone);
                edtPwd=(EditText)findViewById(R.id.userPwd);

                //获取控件的值
                String phone;
                String password;
                phone=edtUserPhone.getText().toString();
                password=edtPwd.getText().toString();

                if(login(phone,password))
                {
                    Toast.makeText(Denglu.this,"登录成功",Toast.LENGTH_LONG).show();
                    Intent it=new Intent(Denglu.this,GongNeng.class);
                    startActivity(it);
                }else
               {
                    Toast.makeText(Denglu.this,"账号或密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });

        bt2=(Button)findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Denglu.this,MovieRatingActivity.class);
                startActivity(it);
            }
        });
    }

    //判断登陆账号和密码
    public boolean login(String phone,String password){
        SQLiteDatabase sdb=mDataHelper.getWritableDatabase();
        String sql="select * from Review where phone=? and password=?";
        Cursor cursor=sdb.rawQuery(sql,new String[]{phone,password});
 if(cursor.moveToFirst()==true){
     cursor.close();
     return  true;
 }
 return false;
    }

}
