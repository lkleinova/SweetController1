package sk.upjs.ics.android.sweetcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenka on 27. 5. 2015.
 */
public class DatabaseOpenHelperLunchDinner extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DatabaseForLunchDinner";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelperLunchDinner(Context context) {

        super(context, DATABASE_NAME, Defaults.DEFAULT_CURSOR_FACTORY, DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableSqlLunch());
        db.execSQL(createTableSqlDinner());
        //naplnim tabulku lunch
        insertSampleEntry(db, "Tomato Soup", 10d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Chicken Meat", 300d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Potatoes", 25d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Rice", 33d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Dumpling", 50d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Pasta", 45d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Beef", 300d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Pork", 300d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Spaghetti", 30d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Spinach with Egg", 200d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Beans", 65d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Lentil", 65d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Vegetables", 200d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Juice", 10d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        insertSampleEntry(db, "Fish", 300d,ProviderLunchDinner.LunchTable.TABLE_NAME );
        //naplnim Dinner
        insertSampleEntry(db, "Sausages", 30d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Roll", 17d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Bread", 20d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Milk", 200d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Ham", 100d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Pineapple", 140d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Vegetables", 200d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Marmelade", 30d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Cheese", 200d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Beer", 15d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Carrot", 80d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Peas", 65d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Wine", 10d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Pear", 90d, ProviderLunchDinner.DinnerTable.TABLE_NAME);
        insertSampleEntry(db, "Chocolate", 15d, ProviderLunchDinner.DinnerTable.TABLE_NAME);


    }

    private void insertSampleEntry(SQLiteDatabase db, String meal, Double carbohydrates, String table) {
        ContentValues contentValues = new ContentValues();
        if (table.equals("LunchTable")) {
            contentValues.put(ProviderLunchDinner.LunchTable.MEAL, meal);
            contentValues.put(ProviderLunchDinner.LunchTable.CARBOHYDRATES, carbohydrates);
            contentValues.put(ProviderLunchDinner.LunchTable.NULL, " ");
            db.insert(table, Defaults.NO_NULL_COLUMN_HACK, contentValues);
        }
        if(table.equals("DinnerTable")) {
            contentValues.put(ProviderLunchDinner.DinnerTable.MEAL, meal);
            contentValues.put(ProviderLunchDinner.DinnerTable.CARBOHYDRATES, carbohydrates);
            contentValues.put(ProviderLunchDinner.DinnerTable.NULL, " ");
            db.insert(table, Defaults.NO_NULL_COLUMN_HACK, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    private String createTableSqlLunch() {
        String sqlTemplate = "CREATE TABLE %s ("
                + "%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "%s TEXT,"
                + "%s REAL,"
                + "%s TEXT"
                + ")";
        return String.format(sqlTemplate,
                ProviderLunchDinner.LunchTable.TABLE_NAME,
                ProviderLunchDinner.LunchTable._ID,
                ProviderLunchDinner.LunchTable.MEAL,
                ProviderLunchDinner.LunchTable.CARBOHYDRATES,
                ProviderLunchDinner.LunchTable.NULL
        );
    }

    private String createTableSqlDinner() {
        String sqlTemplate = "CREATE TABLE %s ("
                + "%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "%s TEXT,"
                + "%s REAL,"
                + "%s TEXT"
                + ")";
        return String.format(sqlTemplate,
                ProviderLunchDinner.DinnerTable.TABLE_NAME,
                ProviderLunchDinner.DinnerTable._ID,
                ProviderLunchDinner.DinnerTable.MEAL,
                ProviderLunchDinner.DinnerTable.CARBOHYDRATES,
                ProviderLunchDinner.DinnerTable.NULL
        );
    }
}
