package cs442.xqiu12.foodmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Calendar;

public class ConfirmActivity extends Activity {
    double sumPrice = 0;
    String names = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        final Button confirmOrder = (Button)findViewById(R.id.confirmOrder);
        final Button cancel = (Button)findViewById(R.id.cancel);
        final ListView selectedList = (ListView)findViewById(R.id.selectedList);
        final ArrayList<String> selectedString = new ArrayList<String>();
        final ArrayAdapter<String> aat = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,selectedString);
        final DecimalFormat decimalFormat2 = new DecimalFormat("#,###.00");
        final TextView priceTV = (TextView)findViewById(R.id.totalPrice);
        final DatabaseHelper food_db = new DatabaseHelper(this);
        final Calendar c = Calendar.getInstance();

        ArrayList<meal> selectedMeal = new ArrayList<meal>();
        selectedList.setAdapter(aat);
        Intent itt = getIntent();
        selectedMeal = (ArrayList<meal>)itt.getSerializableExtra("select");

        for(meal mTemp : selectedMeal){
            selectedString.add(mTemp.getName() + " $" + decimalFormat2.format(mTemp.getPrice()) + " Spicy: " + mTemp.getSpicy());
            sumPrice = sumPrice + mTemp.getPrice();
            names = names + mTemp.getName() + ", ";
        }

        if(names.length()>0)
            names = names.substring(0, names.length()-2);

        priceTV.setText("$" + decimalFormat2.format(sumPrice));

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                setResult(Activity.RESULT_OK, returnIntent);
                returnIntent.putExtra("sum", sumPrice);
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                String time = hour + ":" + minute;
                String sumPriceFormatted = "$"+decimalFormat2.format(sumPrice);
                food_db.insertData(time, names, sumPriceFormatted);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }
}
