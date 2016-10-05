package cs442.xqiu12.letspot;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

   ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Intent intent1= new Intent(getApplicationContext(), GoogleLogin.class);
        startActivity(intent1);
    finish();

        lv = (ListView) findViewById(R.id.listView);
        itemListener();

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_map:
                Intent intent_m = new Intent(getApplicationContext(), MapsActivity.class);
                intent_m.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent_m);
                finish();
                break;
            case R.id.button_reservation:
                Intent intent_r = new Intent(getApplicationContext(), ReservationActivity.class);
                startActivity(intent_r);
                finish();
                break;
            case R.id.button_payment:
                Intent intent_p = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(intent_p);
                finish();
                break;
            case R.id.button_us:
                Intent intent_u = new Intent(getApplicationContext(), SpotActivity.class);
                startActivity(intent_u);
                finish();
                break;
        }
    }

    public void itemListener(){


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "hbn", Toast.LENGTH_LONG).show();
            }
        });



    }

}
