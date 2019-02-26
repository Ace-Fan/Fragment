package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

//定义一个类继承Activity
public class DirectorName extends Activity{
    private AutoCompleteTextView mDirectorName;
    private Button mFinishButton;
    //声明变量
    static final String[] mDirectorNameData = new String[] {
            "male","female"
    };
    @Override
    //重写父类onCreate方法
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //绑定布局
        setContentView(R.layout.directorname);
        mDirectorName = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        mFinishButton = (Button) findViewById(R.id.button1);
        //创建适配器对象，指定上下文this,布局，数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, mDirectorNameData);
        //导员组件的数据源指定为adapter
        mDirectorName.setAdapter(adapter);
        //设置一个点击事件
        mFinishButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // 设置一个意向，由当前对象跳转到MovieRatingActivity.class
                Intent mIntent = new Intent(DirectorName.this, MovieRatingActivity.class);
                //定义一个Bundle
                Bundle mBundle = new Bundle();
                //向Bundle压入键为"key"的值
                mBundle.putString("key", mDirectorName.getText().toString());
                //把Bundle压入意向
                mIntent.putExtras(mBundle);
                //根据意向的密码原路跳回
                setResult(Activity.RESULT_OK, mIntent);
                //关闭自己
                finish();
            }
        });
    }
}
