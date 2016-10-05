package cs442.xqiu12.foodmenu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderHistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        final Button button_back = (Button)findViewById(R.id.button_back);
        final Button button_clear = (Button)findViewById(R.id.button_clear);
        final ListView listView_history = (ListView)findViewById(R.id.listView_history);
        final ArrayList<String> historyString = new ArrayList<String>();
        final ArrayAdapter<String> haa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,historyString);
        final DatabaseHelper food_db = new DatabaseHelper(this);

        listView_history.setAdapter(haa);
        viewAll(food_db, historyString);
        haa.notifyDataSetChanged();

        button_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                food_db.removeAll();
                historyString.clear();
                haa.clear();
                haa.notifyDataSetChanged();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void viewAll(DatabaseHelper databaseHelper, ArrayList<String> als){
        Cursor cursor = databaseHelper.getAllData();
        while (cursor.moveToNext()) {
            als.add(cursor.getString(0) + ")" + cursor.getString(1) + "\n    " + cursor.getString(2) + "\n    " + cursor.getString(3));
        }
    }
}
