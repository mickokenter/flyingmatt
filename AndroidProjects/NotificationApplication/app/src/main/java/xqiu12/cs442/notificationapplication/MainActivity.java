package xqiu12.cs442.notificationapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    //initiate basic args
    int countingNumber = 0;
    EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_start:
                //start button to collect the input number and start the NotificationService
                String str = editText.getText().toString();
                if(isInteger(str)){
                    countingNumber = Integer.parseInt(str);
                }
                else countingNumber = 1;
                scheduleNotification();
                break;
            case R.id.button_stop:
                //stop button to stop the current running NotificationService
                cancelNotification();
                break;
        }
    }

    //Check if the input is integer
    public boolean isInteger(String s) {
        try{
            return s.matches("[-+]?\\d*\\d+");
        } catch(NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please type in an INTEGER", Toast.LENGTH_SHORT).show();
            return false;
        } catch(NullPointerException e) {
            Toast.makeText(getApplicationContext(), "Please type in an INTEGER", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //NotificationService scheduler
    public void scheduleNotification() {
        intent = new Intent(this, NotificationService.class);
        intent.putExtra("countingNumber", countingNumber);
        startService(intent);
    }

    //NotificationService stopper
    public void cancelNotification(){
        intent = new Intent(this, NotificationService.class);
        intent.putExtra("countingNumber", countingNumber);
        stopService(intent);
    }
}
