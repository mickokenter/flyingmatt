package cs442.xqiu12.letspot;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AbsListView;

import java.util.Random;

public class CentralParking extends ActionBarActivity {

    public AbsListView listView;
    public Button b1,b2;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a4_plot);
        navigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        navigationView.setItemIconTintList(null);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.ic_spot:
                        Intent intent1 = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intent1);
                        finish();
                        //getSupportActionBar().setTitle("Settings");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.ic_settings:
//                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.main_frame, new SettingsFragment());
//                        fragmentTransaction.commit();
//                        getSupportActionBar().setTitle("Settings");
//                        item.setChecked(true);
//                        drawerLayout.closeDrawers();
//                        break;
                        Intent intent2 = new Intent(getApplicationContext(), UserSettingActivity.class);
                        getSupportActionBar().setTitle("Settings");
                        startActivity(intent2);
                        finish();
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.ic_invite:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Have you seen the Let's Spot app? Try it and each can get $5.");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;

                    case R.id.ic_action_help:
                        Intent intent3 = new Intent(getApplicationContext(), SpotActivity.class);
                        startActivity(intent3);
                        finish();
                        //getSupportActionBar().setTitle("Settings");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
        TextView txtview = (TextView) findViewById(R.id.txtStatus);
        //txtview.setCompoundDrawablesWithIntrinsicBounds(
          //      R.drawable.centralparking, 0, 0, 0);


        TextView txtview1 = (TextView) findViewById(R.id.NoOfSpacesavail);
        Random rn = new Random();
        int i = rn.nextInt(40)+5;
        txtview1.setText("No of available spots: "+Integer.toString(i));

        TextView txtview2 = (TextView) findViewById(R.id.Details);
        String address = "Central Parking:\n" +
                "2155 S Wentworth Ave, Chicago, IL 60616\n" +
                "chicagoparking.spplus.com\n" +
                "(888) 578-7275\n"+
                "$21/hr";
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

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it return true, then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}