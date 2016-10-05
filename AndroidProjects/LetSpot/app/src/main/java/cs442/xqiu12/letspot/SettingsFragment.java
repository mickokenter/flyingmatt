package cs442.xqiu12.letspot;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends ActionBarActivity {

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.preferences);
        setContentView(R.layout.fragment_settings);
        getSupportActionBar().setTitle("Settings");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        StringBuilder builder = new StringBuilder();
        builder.append("\n" + sharedPrefs.getBoolean("perform_updates", false));
        builder.append("\n" + sharedPrefs.getString("updates_interval", "-1"));
        builder.append("\n" + sharedPrefs.getString("welcome_message", "NULL"));
        TextView settingsTextView = (TextView) findViewById(R.id.setting_text);
        settingsTextView.setText(builder.toString());

    }
}
