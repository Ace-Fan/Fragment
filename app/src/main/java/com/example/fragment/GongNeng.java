package com.example.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GongNeng extends Activity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

protected void onCreate(Bundle saveInstanceState){
    super.onCreate(saveInstanceState);
    setContentView(R.layout.zhujiemian);
     initEvent();
   btn2=(Button)findViewById(R.id.but2);
   btn2.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent it=new Intent(GongNeng.this,QingJia.class);
           startActivity(it);
       }
   });

   btn1=(Button)findViewById(R.id.but3);
   btn1.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent it=new Intent(GongNeng.this,DisplayReviewActivity.class);
           startActivity(it);
       }
   });

    btn3=(Button)findViewById(R.id.but4);
    btn3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent it=new Intent(GongNeng.this,Denglu.class);
            //startActivity(it);
            AlertDialog alertDialog = new AlertDialog.Builder(GongNeng.this)
                    .setTitle("退出程序")
                    .setMessage("是否退出程序")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    }).create();
            alertDialog.show();
        }
    });

    btn4=(Button)findViewById(R.id.buta);
    btn4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it=new Intent(GongNeng.this,TouSu.class);
            startActivity(it);
        }
    });

    btn5=(Button)findViewById(R.id.but3);
    btn5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it=new Intent(GongNeng.this,ReviewListActivity.class);
            startActivity(it);
        }
    });
}
private void initEvent(){
    findViewById(R.id.but1).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    });
}
private void showDialog(){
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setTitle("温馨提示");
    builder.setMessage("亲，打卡成功");
    builder.setPositiveButton("我知道了",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {

                }
            });
    AlertDialog dialog=builder.create();
    dialog.show();
}
}
