package xqiu12.cs442.maptest;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public EditText input;
    public TextView textView_lat;
    public TextView textView_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView_lat = (TextView)findViewById(R.id.textView_lat);
        textView_lng = (TextView)findViewById(R.id.textView_lng);

    }

    public void onClick(View view){
        hideSoftKeyboard(view);

        input = (EditText)findViewById(R.id.input);
        String location = input.getText().toString();

        Geocoder gc = new Geocoder(this);
        try{
            List<Address> list = gc.getFromLocationName(location, 1);
            if(list.size()>0){
                Address add = list.get(0);

                double lat = add.getLatitude();
                double lng = add.getLongitude();

                textView_lat.setText(Double.toString(lat));
                textView_lng.setText(Double.toString(lng));

                gotoLocation(lat, lng);
            }
            else Toast.makeText(this, "FOUND NO PLACES", Toast.LENGTH_SHORT).show();
        }
        catch (IOException ioe){
            //do nothing
        }
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
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

        // Add a marker in Sydney and move the camera
        LatLng IIT = new LatLng(41.834874, -87.626993);
        mMap.addMarker(new MarkerOptions().position(IIT).title("Marker in IIT"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IIT, 15));
    }

    public void gotoLocation(double lat, double lng){
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
        mMap.moveCamera(update);
    }

}
