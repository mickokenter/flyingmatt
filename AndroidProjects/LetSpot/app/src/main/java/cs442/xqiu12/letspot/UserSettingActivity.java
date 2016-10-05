package cs442.xqiu12.letspot;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by ctsuser1 on 4/9/2016.
 */
public class UserSettingActivity extends PreferenceActivity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(Menu.NONE, 0, 0, "Show current settings");
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case 0:
//                startActivity(new Intent(this, ShowSettingsActivity.class));
//                return true;
//        }
//        return false;
//    }
    }
