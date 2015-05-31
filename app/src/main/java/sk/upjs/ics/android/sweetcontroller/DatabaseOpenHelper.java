package sk.upjs.ics.android.sweetcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenka on 21. 5. 2015.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SweetController2";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {

        super(context, DATABASE_NAME, Defaults.DEFAULT_CURSOR_FACTORY, DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTableSql());
        insertSampleEntry(db, "apple1", 10d);//apple1
        insertSampleEntry(db, "banana1", 20d);
        insertSampleEntry(db, "grapes1", 30d);
        System.out.println("som v database open helper");
    }

    private void insertSampleEntry(SQLiteDatabase db, String meal, Double carbohydrates) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Provider.BreakfastTable.MEAL, meal);
        contentValues.put(Provider.BreakfastTable.CARBOHYDRATES, carbohydrates);
        contentValues.put(Provider.BreakfastTable.NULL, " ");
        db.insert(Provider.BreakfastTable.TABLE_NAME, Defaults.NO_NULL_COLUMN_HACK, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    private String createTableSql() {
        String sqlTemplate = "CREATE TABLE %s ("
                + "%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "%s TEXT,"
                + "%s REAL,"
                + "%s TEXT"
                + ")";
        return String.format(sqlTemplate,
                Provider.BreakfastTable.TABLE_NAME,
                Provider.BreakfastTable._ID,
                Provider.BreakfastTable.MEAL,
                Provider.BreakfastTable.CARBOHYDRATES,
                Provider.BreakfastTable.NULL
                );
    }
}
