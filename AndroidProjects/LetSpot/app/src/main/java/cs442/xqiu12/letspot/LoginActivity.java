package cs442.xqiu12.letspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
Button Lbutton, Rbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        Lbutton = (Button) findViewById(R.id.button_Login);
        Rbutton = (Button) findViewById(R.id.button_Register);
        Lbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent2);
                finish();
                //Toast.makeText(getApplicationContext(), "tt", Toast.LENGTH_LONG).show();
            }

        });
        Rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent2);
                finish();
            }
        });


    }

    }


