package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class ResultActivitys extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        ListView listView = (ListView) findViewById(R.id.show);
        Intent intent = getIntent();
        // 获取该intent所携带的数据
        Bundle data = intent.getExtras();
        // 从Bundle数据包中取出数据
        @SuppressWarnings("unchecked")
        List<Map<String, String>> list = (List<Map<String, String>>)
                data.getSerializable("data");
        // 将List封装成SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(ResultActivitys.this
                , list,
                R.layout.linet, new String[] { "word", "detail" }
                , new int[] {R.id.word, R.id.detail });
        // 填充ListView
        listView.setAdapter(adapter);
    }
}
