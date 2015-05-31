package sk.upjs.ics.android.sweetcontroller;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import static android.content.ContentResolver.SCHEME_CONTENT;
import static sk.upjs.ics.android.sweetcontroller.Provider.BreakfastTable;

/**
 * Created by Lenka on 27. 5. 2015.
 */
public class LunchDinnerContentProvider extends ContentProvider {

    public static final String AUTHORITY = "sk.upjs.ics.android.sweetcontroller.LunchDinnerContentProvider";
    public static final Uri CONTENT_URI_LUNCH = new Uri.Builder()
            .scheme(SCHEME_CONTENT)
            .authority(AUTHORITY)
            .appendPath(ProviderLunchDinner.LunchTable.TABLE_NAME)
            .build();
    public static final Uri CONTENT_URI_DINNER = new Uri.Builder()
            .scheme(SCHEME_CONTENT)
            .authority(AUTHORITY)
            .appendPath(ProviderLunchDinner.DinnerTable.TABLE_NAME)
            .build();
    private DatabaseOpenHelperLunchDinner databaseOpenHelperLunchDinner;
    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int URI_MATCH_LUNCH = 0;
    private static final int URI_MATCH_LUNCH_BY_ID = 1;
    private static final int URI_MATCH_DINNER = 2;
    private static final int URI_MATCH_DINNER_BY_ID = 3;

    public LunchDinnerContentProvider() {

    }
    @Override
    public boolean onCreate() {
        this.databaseOpenHelperLunchDinner = new DatabaseOpenHelperLunchDinner(getContext());
        uriMatcher.addURI(AUTHORITY, ProviderLunchDinner.LunchTable.TABLE_NAME, URI_MATCH_LUNCH);
        uriMatcher.addURI(AUTHORITY, ProviderLunchDinner.LunchTable.TABLE_NAME + "/#", URI_MATCH_LUNCH_BY_ID);
        uriMatcher.addURI(AUTHORITY, ProviderLunchDinner.DinnerTable.TABLE_NAME, URI_MATCH_DINNER);
        uriMatcher.addURI(AUTHORITY, ProviderLunchDinner.DinnerTable.TABLE_NAME + "/#", URI_MATCH_DINNER_BY_ID);
        System.out.println("som v databaze");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        Cursor cursor = null;
        long id;
        switch(uriMatcher.match(uri)) {
            case URI_MATCH_LUNCH:
                cursor = listNotes(ProviderLunchDinner.LunchTable.TABLE_NAME);
                return cursor;
            case URI_MATCH_LUNCH_BY_ID:
                 id = ContentUris.parseId(uri);
                cursor = findById(id, ProviderLunchDinner.LunchTable.TABLE_NAME);
                return cursor;
            case URI_MATCH_DINNER:
                cursor = listNotes(ProviderLunchDinner.DinnerTable.TABLE_NAME);
                return cursor;
            case URI_MATCH_DINNER_BY_ID:
                id = ContentUris.parseId(uri);
                cursor = findById(id, ProviderLunchDinner.DinnerTable.TABLE_NAME);
                return cursor;

            default:
                return Defaults.NO_CURSOR;
        }
    }

    private Cursor listNotes(String table) {
        SQLiteDatabase db = databaseOpenHelperLunchDinner.getReadableDatabase();
        return db.query(table, Defaults.ALL_COLUMNS, Defaults.NO_SELECTION, Defaults.NO_SELECTION_ARGS, Defaults.NO_GROUP_BY, Defaults.NO_HAVING, Defaults.NO_SORT_ORDER);
    }

    private Cursor findById(long id, String table) {
        SQLiteDatabase db = databaseOpenHelperLunchDinner.getReadableDatabase();
        String selection="";
        if (table.equals("LunchTable")) {
             selection = ProviderLunchDinner.LunchTable._ID + "=" + id;
        }
        if (table.equals("DinnerTable")) {
            selection = ProviderLunchDinner.DinnerTable._ID + "=" + id;
        }
        return db.query(table, Defaults.ALL_COLUMNS, selection, Defaults.NO_SELECTION_ARGS, Defaults.NO_GROUP_BY, Defaults.NO_HAVING, Defaults.NO_SORT_ORDER);
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
