package cs442.xqiu12.l6practice;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import java.io.*;


public class MainActivity extends Activity {
    private static final String TEXTVIEW_STATE_KEY = "TEXTVIEW_STATE_KEY";
    public void onSaveInstanceState(Bundle saveInstanceState){
        TextView myTextView = (TextView)findViewById(R.id.enter);
        saveInstanceState.putString(TEXTVIEW_STATE_KEY, myTextView.getText().toString());
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to UI widgets
        ListView listView = (ListView)findViewById(R.id.listView);
        final EditText input = (EditText)findViewById(R.id.editText);

        // Create the Array List of to do items
        final ArrayList<String> todoItems = new ArrayList<String>();

        // Create the Array Adapter to bind the array to the List View
        final ArrayAdapter<String> aa;

        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                todoItems);

        // Bind the Array Adapter to the List View
        listView.setAdapter(aa);

        input.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        writeFileInternalStorage(input.getText().toString(), "output.txt");
                        input.setText("");
                        todoItems.add(readFileInternalStorage("output.txt"));
                        aa.notifyDataSetChanged();
                        return true;
                    }
                return false;
            }
        });
/***
        TextView myTextView = (TextView)findViewById(R.id.enter);
        String text = "";
        if(savedInstanceState != null && savedInstanceState.containsKey(TEXTVIEW_STATE_KEY))
            text = savedInstanceState.getString(TEXTVIEW_STATE_KEY);
        myTextView.setText(text);
***/
        SharedPreferences aP = getPreferences(Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = aP.edit();

        editor.putString("currentTextValue", input.getText().toString());
        editor.apply();

        String inputString = aP.getString("currentTextValue", input.getText().toString());
        input.setText((CharSequence)inputString);
    }
    public void writeFileInternalStorage(String strWrite,String fileName){
        try{
            if(isSdReadable()){
                FileOutputStream bw = openFileOutput(fileName, MODE_PRIVATE);
                byte [] bytes = strWrite.getBytes();

                bw.write(bytes);
                bw.close();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readFileInternalStorage(String fileName){
        String res = "";
        try{
            if(isSdReadable()){
                FileInputStream in = openFileInput(fileName);
                byte [] buffer = new byte[in.available()];
                in.read(buffer);
                res = new String(buffer, "UTF-8");
                in.close();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static boolean isSdReadable()
    {

        boolean mExternalStorageAvailable = false;
        try
        {
            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state))
            {
                // We can read and write the media
                mExternalStorageAvailable = true;
                Log.i("isSdReadable", "External storage card is readable.");
            }
            else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            {
                // We can only read the media
                Log.i("isSdReadable", "External storage card is readable.");
                mExternalStorageAvailable = true;
            }
            else
            {
                // Something else is wrong. It may be one of many other
                // states, but all we need to know is we can neither read nor
                // write
                mExternalStorageAvailable = false;
            }
        } catch (Exception ex)
        {

        }
        return mExternalStorageAvailable;
    }
}
