package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MovieRatingActivity extends Activity {
    private EditText zName;
    private EditText zPhone;
    private EditText zPassword;
    private EditText zAge;
    private EditText zSex;
    private Button zButton;
    private Button zButton2;

    //适配器
    //private ArrayAdapter<String> mAdapter;

    //定义一个数据库的操作类
    MovieRatingDataHelper mDataHelper;
    private SQLiteDatabase sdb;

    @Override
    //重写父类的oncreate（）方法
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定布局
        setContentView(R.layout.zhuce);

        mDataHelper = new MovieRatingDataHelper(this, "MovieRating.db", null, 1);  //使用helper创建数据库
        sdb=mDataHelper.getWritableDatabase();
        //找到布局中对应的控件
        zName = (EditText) findViewById(R.id.edit1);
        zPhone = (EditText) findViewById(R.id.edit2);
        zPassword = (EditText) findViewById(R.id.edit3);
        zAge = (EditText) findViewById(R.id.etage);
        zSex = (EditText) findViewById(R.id.esex);

        //找到布局中对应的控件
        zButton = (Button) findViewById(R.id.button3);
        zButton2=(Button)findViewById(R.id.button4);
        zButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MovieRatingActivity.this,Denglu.class);
                startActivity(it);
            }
        });
        //保存按钮的事件绑定
        zButton.setOnClickListener(new OnButtonClick());
        //创建数据库操作对象
        mDataHelper = new MovieRatingDataHelper(this,null,null,1);

        //导演组建的点击事件
        zSex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //设置一个意向，由当前对象跳转到DirectorName类
                Intent mAutoCompleteIntent = new Intent(MovieRatingActivity.this, DirectorName.class);
                //该意向是希望有返回值的，返回值的代号为0123456
                startActivityForResult(mAutoCompleteIntent, 0123456);
            }
        });
    }

    //保存按钮的点击事件
    public class OnButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //获取组建中用户输入的值
            String str1 = zName.getText().toString();
            String str2 = zPhone.getText().toString();
            String str3 = zPassword.getText().toString();
            String str4 = zAge.getText().toString();
            String str5 = zSex.getText().toString();

            if(str1.equalsIgnoreCase("") || str2.equalsIgnoreCase("")  || str3.equalsIgnoreCase("") ||str4.equalsIgnoreCase("") ||str5.equalsIgnoreCase("")){
                Toast.makeText(MovieRatingActivity.this,"Please fill all the fields.", Toast.LENGTH_SHORT).show();
            }
            else{
                //获取组建中用户输入的值
                String name = zName.getText().toString();
                String phone = zPhone.getText().toString();
                String password = zPassword.getText().toString();
                String age = zAge.getText().toString();
                String sex = zSex.getText().toString();

                //调用保存到数据库中的方法
                register(name,phone,password,age,sex);
                Toast.makeText(MovieRatingActivity.this,"注册成功！", Toast.LENGTH_LONG).show();

                String names = zName.getText().toString();
                String phones = zPhone.getText().toString();
                String passwords = zPassword.getText().toString();
                String ages = zAge.getText().toString();
                String sexs = zSex.getText().toString();
                //调用MovieRatingActivity类中的insert，完成数据的增加功能
                mDataHelper.insert(names, phones, passwords, ages, sexs);


                //定义一个意向，跳转到ReviewListActivity
                /*Intent mReviewListIntent = new Intent(MovieRatingActivity.this,
                        ReviewListActivity.class);
                //跳转
                startActivity(mReviewListIntent);*/


            }
        }
    }

    // 注册的方法
    public void register(String name,String phone,String password,String age,String sex){
        String sql="insert into Review(name,phone,password,age,sex) values(?,?,?,?,?)";
        Object obj[]={name,phone,password,age,sex};
        sdb.execSQL(sql, obj);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            //处理：返回值的代号为0123456的意向的返回值
            if ((requestCode == 0123456 ) && (resultCode == Activity.RESULT_OK)){
                //从返回的意向中取出Bundle对象
                Bundle myResults = data.getExtras();
                //从Bundle对象中取出键为key的值
                String vresult = myResults.getString("key");
                //把取出来的值赋给movieDirector导员这个组件
                zSex.setText(vresult);
            }
        }
        //异常处理
        catch (Exception e) {
            zSex.setText("Oops! - " + requestCode + " " + resultCode);
        }
    }
}
