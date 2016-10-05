package cs442.xqiu12.letspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RedTopParking extends AppCompatActivity {

    public Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_top_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView txtview = (TextView) findViewById(R.id.txtStatus);
        txtview.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.red_top_parking, 0, 0, 0);


        TextView txtview1 = (TextView) findViewById(R.id.NoOfSpacesavail);
        Random rn = new Random();
        int i = rn.nextInt(60)+1;
        txtview1.setText("No of available spots: "+Integer.toString(i));

        TextView txtview2 = (TextView) findViewById(R.id.Details);
        String address = "Red Top Parking:\n" +
                "1959 W Washington Blvd, Chicago, IL 60612\n" +
                "(312) 421-4136\n"+"$18/hr";
        txtview2.setText(address);
        // Button b1 = (Button) findViewById(R.id.Bookmyspot);
        addButtonListener();
    }

    public void addButtonListener()
    {
        b1 = (Button) findViewById(R.id.Reservemyspot);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent browserIntent =
                        new Intent(getApplicationContext(), ReservationActivity.class);
                startActivity(browserIntent);

            }

        });

        b2 = (Button) findViewById(R.id.Cancelmyspot);

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent browserIntent =
                        new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(browserIntent);

            }

        });

    }


}
