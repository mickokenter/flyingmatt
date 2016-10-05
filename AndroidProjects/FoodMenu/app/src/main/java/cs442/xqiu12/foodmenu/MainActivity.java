package cs442.xqiu12.foodmenu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    static double sumConfirmed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView myListView = (ListView)findViewById(R.id.myListView);
        final EditText mealName = (EditText)findViewById(R.id.mealName);
        final EditText mealPrice = (EditText)findViewById(R.id.mealPrice);
        final EditText spicy = (EditText)findViewById(R.id.spicy);
        final EditText ingredients = (EditText)findViewById(R.id.ingredients);
        final Button addMeal = (Button)findViewById(R.id.addMeal);
        final Button playOrder = (Button)findViewById(R.id.playOrder);
        final Button reset = (Button)findViewById(R.id.reset);
        final Button clearSum = (Button)findViewById(R.id.clearSum);
        final ArrayList<meal> mealList = new ArrayList<meal>();
        final ArrayList<String> mealString = new ArrayList<String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,mealString);
        final String sum= "";
        final Intent i = new Intent(this,ConfirmActivity.class);
        final Intent i2 = new Intent(this,OrderHistoryActivity.class);
        final TextView sumTV = (TextView)findViewById(R.id.sum);
        final Button orderHistory = (Button)findViewById(R.id.button_order_history);

        meal m1 = new meal("Chow Mein", 5.99, 0, "Broccoli, onion, meat, corn, noodles");
        meal m2 = new meal("Crab Roll", 9.99, 0, "Fried rolls wrapped w/ fresh crabmeat");
        meal m3 = new meal("Spicy Shrimp", 7.99, 2, "Shrimp, green onions, red peppers, garlic");
        meal m4 = new meal("Spicy Chicken", 6.99, 3, "Chicken, peanuts, red pepper");
        meal m5 = new meal("Moo Shu Pork", 8.99, 0, "Pork, vegetable, pancake, hoi sin sauce");
        mealList.add(m1);
        mealList.add(m2);
        mealList.add(m3);
        mealList.add(m4);
        mealList.add(m5);
        for(meal mTemp : mealList){
            mealString.add(mTemp.getName() + " $" + decimalFormat.format(mTemp.getPrice()) + " Spicy: " + mTemp.getSpicy());
        }

        myListView.setAdapter(aa);
        myListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        addMeal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(mealName.getText().toString().equals("") || mealPrice.getText().toString().equals("") || spicy.getText().toString().equals("") || ingredients.getText().toString().equals(""))) {
                    meal addNewMeal = new meal(mealName.getText().toString(), Double.parseDouble(mealPrice.getText().toString()), Integer.parseInt(spicy.getText().toString()), ingredients.getText().toString());
                    boolean check = false;
                    for (meal m : mealList) {
                        if (addNewMeal.equals(m)) {
                            check = true;
                            Toast.makeText(getApplicationContext(), mealName.getText().toString() + " is already added, Please add another one.", Toast.LENGTH_SHORT).show();
                            break;
                        } else check = false;
                    }
                    if (!check) {
                        mealList.add(addNewMeal);
                        mealString.add(addNewMeal.getName() + " $" + decimalFormat.format(addNewMeal.getPrice()) + " Spicy: " + addNewMeal.getSpicy());
                        mealName.setText("");
                        mealPrice.setText("");
                        spicy.setText("");
                        ingredients.setText("");
                        aa.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill all the items", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = myListView.getPositionForView(view);
                String str = mealList.get(pos).getName() + "\n$ " + mealList.get(pos).getPrice() + "\nSpicy Level: " + mealList.get(pos).getSpicy() + "\nIngredients: " + mealList.get(pos).getIngredients();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = myListView.getPositionForView(view);
                String str = mealList.get(pos).getName() + " has been removed from menu!";
                mealList.remove(pos);
                mealString.remove(pos);
                aa.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        playOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<meal> mealTempAL = checkedMealCount(myListView, mealList);
                if(!mealTempAL.isEmpty()){
                    i.putExtra("select", mealTempAL);
                    startActivityForResult(i, 1);
                }
                else{
                    Toast.makeText(getApplicationContext(), "You haven't selected any items. PLEASE select at least one.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myListView.clearChoices();
                myListView.requestLayout();
            }
        });

        clearSum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sumConfirmed = 0;
                sumTV.setText("$" + decimalFormat.format(sumConfirmed));
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(i2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            sumConfirmed = sumConfirmed + data.getDoubleExtra("sum", 0.0);
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            sumConfirmed = sumConfirmed;
        }
    }//onActivityResult

    @Override
    protected void onResume(){
        super.onResume();
        final TextView sumTV = (TextView)findViewById(R.id.sum);
        final ListView myListView = (ListView)findViewById(R.id.myListView);
        sumTV.setText("$"+decimalFormat.format(sumConfirmed));
        myListView.clearChoices();
        myListView.requestLayout();
    }

    //get the checked items
    public ArrayList<meal> checkedMealCount(ListView listView, ArrayList<meal> arylst){
        ArrayList<meal> checkedMeal = new ArrayList<meal>();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        for (int i = 0; i < listView.getCount(); i++) {
            if (checked.get(i)) {
                checkedMeal.add(arylst.get(i));
            }
        }
        return checkedMeal;
    }
}
