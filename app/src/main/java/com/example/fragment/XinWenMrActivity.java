package com.example.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class XinWenMrActivity extends Activity {
    private EditText Title;
    private Spinner Style;
    private EditText Date;
    private EditText personName;
    private EditText Content;
    private Button ReleaseButton;

    //创建一个适配器
    private ArrayAdapter<String> mAdapter;
    //定义一个数据库的操作类
    XinWenMrDataHelper mDataHelper;
    //申明一个数组：发布类型
    private String[] mStyleData={"政治类","经济类","文化类","娱乐类"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xinwen_main);

        Title=(EditText)findViewById(R.id.editText1);
        Date=(EditText)findViewById(R.id.editText2);
        personName=(EditText)findViewById(R.id.editText3);
        Content=(EditText)findViewById(R.id.editText4);

        //找到发布控件
        ReleaseButton=(Button)findViewById(R.id.button1);
        ReleaseButton.setOnClickListener(new OnButtonClick());
        Style=(Spinner) findViewById(R.id.spinner1);

        mDataHelper=new XinWenMrDataHelper(this);

        //创建数据适配器对象，指定数据源为mStyleData
        mAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mStyleData);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Style.setAdapter(mAdapter);
    }

    public class OnButtonClick implements View.OnClickListener {
        //获取组件中用户输入的值
        public void onClick(View arg0) {
            String str1 = Title.getText().toString();
            String str2 = Date.getText().toString();
            String str3 = personName.getText().toString();
            String str4 = Content.getText().toString();

            //若果用户没有选择，提示要选择
            if (Style.getSelectedItem().toString().equalsIgnoreCase("")) {
                Toast.makeText(XinWenMrActivity.this, "请选择类型", Toast.LENGTH_LONG).show();
            } else if (str1.equalsIgnoreCase("") || str2.equalsIgnoreCase("") || str3.equalsIgnoreCase("") || str4.equalsIgnoreCase("")) {
                Toast.makeText(XinWenMrActivity.this, "请填写完整", Toast.LENGTH_LONG).show();
            } else {
                String Ftitle = Title.getText().toString();
                String Fstyle = Style.getSelectedItem().toString();
                String Fdate = Date.getText().toString();
                String FpersonName = personName.getText().toString();
                String Fcontent = Content.getText().toString();

                mDataHelper.insert(Ftitle, Fstyle, Fdate, FpersonName, Fcontent);
                //定义一个意向跳转到ReviewListActivity
                Toast.makeText(XinWenMrActivity.this,"发布成功",Toast.LENGTH_LONG).show();
                // Intent mReviewListIntent = new Intent(MovieRatingActivity.this, ReviewListActivity.class);
                // startActivity(mReviewListIntent);
            }
        }
    }
}
