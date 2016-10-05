package cs442.xqiu12.letspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SpotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_spot);
    }


    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_map:
                Intent intent_m = new Intent(getApplicationContext(), MainActivity.class);
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
}
