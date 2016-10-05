package cs442.xqiu12.foodmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kente on 2016/2/28.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "food.db";
    public static final String TABLE_NAME = "order_history";
    public static final String ID = "ID";
    public static final String ORDER_TIME = "ORDER_TIME";
    public static final String ORDER_NAMES = "NAMES";
    public static final String TOTAL_PRICE = "TOTAL_PRICE";
    public static final String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ORDER_TIME + " TEXT," + ORDER_NAMES + " TEXT," + TOTAL_PRICE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String time, String name, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_TIME, time);
        contentValues.put(ORDER_NAMES, name);
        contentValues.put(TOTAL_PRICE, price);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public void removeAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.execSQL("DELETE FROM sqlite_sequence where name='"+TABLE_NAME+"'");
    }
}
