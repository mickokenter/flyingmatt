package com.paad.broadcastintents;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {
  /**
   * Listing 5-12: Registering and unregistering a Broadcast Receiver in code 
   */

  private IntentFilter filter = 
      new IntentFilter(LifeformDetectedReceiver.NEW_LIFEFORM);

  private LifeformDetectedReceiver receiver = 
    new LifeformDetectedReceiver();

  @Override
  public synchronized void onResume() {
    super.onResume();

    // Register the broadcast receiver.
    registerReceiver(receiver, filter); 
  }

  @Override
  public synchronized void onPause() {
    // Unregister the receiver
    unregisterReceiver(receiver);  

    super.onPause();
  }
  
  //
  private void detectedLifeform(String detectedLifeform, double currentLongitude, double currentLatitude) {
    Intent intent = new Intent(LifeformDetectedReceiver.NEW_LIFEFORM);
    intent.putExtra(LifeformDetectedReceiver.EXTRA_LIFEFORM_NAME,
                    detectedLifeform);
    intent.putExtra(LifeformDetectedReceiver.EXTRA_LONGITUDE,
                    currentLongitude);
    intent.putExtra(LifeformDetectedReceiver.EXTRA_LATITUDE,
            currentLatitude);

    sendBroadcast(intent);
  }
    public void onClick(View v){
        //Toast.makeText(this, "Toasted", Toast.LENGTH_LONG).show();
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("hello world");
        Intent intent = new Intent(LifeformDetectedReceiver.NEW_LIFEFORM);
        intent.putExtra(LifeformDetectedReceiver.EXTRA_LIFEFORM_NAME,
                "");
        intent.putExtra(LifeformDetectedReceiver.EXTRA_LONGITUDE,
                "");
        intent.putExtra(LifeformDetectedReceiver.EXTRA_LATITUDE,
                "");
        sendBroadcast(intent);
        //receiver.onReceive(this, intent);
    }

    public void onClick2(View v){
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("www.google.com is the google webaddress; xqiu12@hawk.iit.edu is my email; 3120000000 is my phone number; 3241 S STATE ST, CHICAGO, IL 60616")
        ;
        //receiver.onReceive(this, );
    }
  //
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
  }

}