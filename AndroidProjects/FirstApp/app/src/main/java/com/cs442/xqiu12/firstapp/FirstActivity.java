package com.cs442.xqiu12.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.Switch;

public class FirstActivity extends Activity {
    public int counter = 0;
    private Switch switchA;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.content_first);
        ListView myListView = (ListView)findViewById(R.id.myListView);
        final EditText myEditText = (EditText)findViewById(R.id.myEditText);
        final ArrayList<String> todoItems = new ArrayList<String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoItems);
        myListView.setAdapter(aa);
        switchA = (Switch) findViewById(R.id.switch1);
        switchA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"Hi", Toast.LENGTH_SHORT).show();
            }
        });

        myEditText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        for (int i = 1; i <= 30; i++) {
                            counter++;
                            todoItems.add("[" + counter + "] " + 2*i);
                            myEditText.setText("");
                            aa.notifyDataSetChanged();
                        }
                        return true;
                    }
                return false;
            }
        });
        Toast.makeText(getApplicationContext(), "checkbox is on", Toast.LENGTH_SHORT).show();
    }

}
