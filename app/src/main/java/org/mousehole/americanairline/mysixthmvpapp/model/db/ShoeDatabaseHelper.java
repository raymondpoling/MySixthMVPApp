package org.mousehole.americanairline.mysixthmvpapp.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.mousehole.americanairline.mysixthmvpapp.R;
import org.mousehole.americanairline.mysixthmvpapp.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ShoeDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "shoe_db";
    private static int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "shoe_table";
    public static final String COLUMN_SHOE_SIZE = "shoe_size";
    public static final String COLUMN_MODEL = "shoe_model";
    public static final String COLUMN_PRICE = "shoe_price";
    public static final String COLUMN_SHOE_ID = "shoe_id";

    private final Resources resources;

    public ShoeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.resources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = String.format("CREATE TABLE %s ( " +
                " %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " %s TEXT, " +
                " %s TEXT, " +
                " %s INTEGER)", TABLE_NAME, COLUMN_SHOE_ID, COLUMN_PRICE, COLUMN_MODEL, COLUMN_SHOE_SIZE);
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String update = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(update);
        onCreate(sqLiteDatabase);

        DATABASE_VERSION = i1;
    }

    public List<Shoe> getAllShoes() {
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor shoeCursor = getReadableDatabase().rawQuery(query, null);

        List<Shoe> shoes = new ArrayList<>();

        shoeCursor.move(-1);

        while(shoeCursor.moveToNext()){
            String shoeModel = shoeCursor.getString(shoeCursor.getColumnIndex(COLUMN_MODEL));
            String shoePrice = shoeCursor.getString(shoeCursor.getColumnIndex(COLUMN_PRICE));

            int shoeSize = shoeCursor.getInt(shoeCursor.getColumnIndex(COLUMN_SHOE_SIZE));
            int shoeId = shoeCursor.getInt(shoeCursor.getColumnIndex(COLUMN_SHOE_ID));

            Shoe shoe = new Shoe(shoeSize,shoeModel,shoeId,Double.parseDouble(shoePrice));
            shoes.add(shoe);
        }

        return shoes;
    }

    public void insertNewShoe(Shoe shoe) {
        ContentValues shoeValue = new ContentValues();
        shoeValue.put(COLUMN_MODEL, shoe.getShoeModel());
        shoeValue.put(COLUMN_PRICE, shoe.getShoePrice() + "");
        shoeValue.put(COLUMN_SHOE_SIZE, shoe.getShoeSize());
        getWritableDatabase().insert(TABLE_NAME, null, shoeValue);
    }

    public void deleteShoeFromDatabase(Shoe shoe) {
        String deleteSql = resources.getString(R.string.delete_command, TABLE_NAME, COLUMN_SHOE_ID, shoe.getShoeId());
        getWritableDatabase().execSQL(deleteSql, null);
    }

    public void populateIfEmpty() {
        List<Shoe> items = getAllShoes();
        if(items.size() == 0) {
            insertNewShoe(new Shoe(10, "Jordan 1 Retro", 110.99));
            insertNewShoe(new Shoe(10, "James Active", 79.99));
            insertNewShoe(new Shoe(10, "Adam Hiking", 210.99));
            insertNewShoe(new Shoe(10, "George Sports", 69.99));
        }
    }

}
