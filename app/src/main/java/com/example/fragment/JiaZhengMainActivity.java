package com.example.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class JiaZhengMainActivity extends Activity {

    private Button but1;
    private Button but2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiazheng_main);
        but1 = (Button)findViewById(R.id.xuqiu);
        but2 = (Button)findViewById(R.id.fuwu);

        but1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO �Զ����ɵķ������
                Intent inter = new Intent(JiaZhengMainActivity.this,chazhao.class);
                startActivity(inter);
            }});

        but2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO �Զ����ɵķ������
                Intent inter = new Intent(JiaZhengMainActivity.this,fuwu.class);
                startActivity(inter);
            }});
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
