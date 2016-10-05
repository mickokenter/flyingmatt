package cs442.xqiu12.letspot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends ActionBarActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in IIT_Chicago and move the camera
        LatLng plot1 = new LatLng(41.8830, -87.6763);
        LatLng plot2 = new LatLng(41.8856, -87.6215);
        LatLng plot3 = new LatLng(41.9004, -87.6226);
        LatLng plot4 = new LatLng(41.7995, -87.5881);
        LatLng plot5 = new LatLng(41.8785, -87.6464);
        LatLng plot6 = new LatLng(41.8772, -87.6422);
        LatLng plot7 = new LatLng(41.8898, -87.6229);
        LatLng plot8 = new LatLng(41.8899, -87.6323);
        LatLng plot9 = new LatLng(41.8537, -87.6316);
        LatLng plot10 = new LatLng(41.8758, -87.6319);


        mMap.addMarker(new MarkerOptions().position(plot1).title("Red Top Parking Inc").snippet("1959 W Washington Blvd, Chicago, IL 60612"));
        mMap.addMarker(new MarkerOptions().position(plot2).title("Standarad Parking").snippet("200 E Randolph St, Chicago, IL 60601"));
        mMap.addMarker(new MarkerOptions().position(plot3).title("Drake Tower Garage").snippet("174 E Walton Pl, Chicago, IL 60611"));
        mMap.addMarker(new MarkerOptions().position(plot4).title("Parking Space Inc.").snippet("1525 E 53rd St #420, Chicago, IL 60615"));
        mMap.addMarker(new MarkerOptions().position(plot5).title("Park One").snippet("762 W Jackson Blvd, Chicago, IL 60661"));
        mMap.addMarker(new MarkerOptions().position(plot6).title("People's Auto Parking").snippet("568 W Van Buren St, Chicago, IL 60607"));
        mMap.addMarker(new MarkerOptions().position(plot7).title("Downtown Parking").snippet("401 N Michigan Ave, Chicago, IL 60611"));
        mMap.addMarker(new MarkerOptions().position(plot8).title("InterParking").snippet("345 N LaSalle St, Chicago, IL 60622"));
        mMap.addMarker(new MarkerOptions().position(plot9).title("Central Parking").snippet("2155 S Wentworth Ave, Chicago, IL 60616"));
        mMap.addMarker(new MarkerOptions().position(plot10).title("impark").snippet("One Financial Place, 440 S La Salle St, Chicago, IL 60605"));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plot1, 12));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if (arg0.getTitle().equals("Red Top Parking Inc")) // if marker source is clicked
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_i = new Intent(getApplicationContext(), RedTopParking.class);
                    startActivity(intent_i);

                if (arg0.getTitle().equals("Standarad Parking")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), StandardParking.class);
                    startActivity(intent_A4);
                }
                //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                if (arg0.getTitle().equals("Drake Tower Garage")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), Drake_Tower_Garage.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("Parking Space Inc.")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), Parking_Space_Inc.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("Park One")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), ParkOne.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("People's Auto Parking")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), PeopleAuto.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("Downtown Parking")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), Downtown.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("InterParking")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), Interparking.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("Central Parking")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), CentralParking.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("impark")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    Intent intent_A4 = new Intent(getApplicationContext(), Impark.class);
                    startActivity(intent_A4);

                    //Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                return true;

            }

        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

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
