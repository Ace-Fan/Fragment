package com.example.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment4 extends Fragment {
 private Button Bgy;
 //private Button Tc;
    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content4,container,false);
       // TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
       // txt_content.setText("第四个Fragment");
        Bgy=(Button)view.findViewById(R.id.bguanyu);
        Bgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(),GuanYu.class);
                startActivity(it);
            }
        });

       /* Tc=(Button)view.findViewById(R.id.tuichu);
        Tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent it=new Intent(GongNeng.this,Denglu.class);
                //startActivity(it);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("退出程序")
                        .setMessage("是否退出程序")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                  return;
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
        });*/
        Log.e("HEHE", "4日狗");
        return view;
    }
}
