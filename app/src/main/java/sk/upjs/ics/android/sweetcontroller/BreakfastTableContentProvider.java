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

public class BreakfastTableContentProvider extends ContentProvider {

    public static final String AUTHORITY = "sk.upjs.ics.android.sweetcontroller.BreakfastTableContentProvider";
    public static final Uri CONTENT_URI = new Uri.Builder()
            .scheme(SCHEME_CONTENT)
            .authority(AUTHORITY)
            .appendPath(BreakfastTable.TABLE_NAME)
            .build();
    private DatabaseOpenHelper databaseHelper;
    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int URI_MATCH_NOTES = 0;
    private static final int URI_MATCH_NOTE_BY_ID = 1;
    public BreakfastTableContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Cursor listNotes() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db.query(BreakfastTable.TABLE_NAME, Defaults.ALL_COLUMNS, Defaults.NO_SELECTION, Defaults.NO_SELECTION_ARGS, Defaults.NO_GROUP_BY, Defaults.NO_HAVING, Defaults.NO_SORT_ORDER);
    }

    private Cursor findById(long id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String selection = BreakfastTable._ID + "=" + id;
        return db.query(BreakfastTable.TABLE_NAME, Defaults.ALL_COLUMNS, selection, Defaults.NO_SELECTION_ARGS, Defaults.NO_GROUP_BY, Defaults.NO_HAVING, Defaults.NO_SORT_ORDER);
    }

    @Override
    public boolean onCreate() {

        this.databaseHelper = new DatabaseOpenHelper(getContext());
        uriMatcher.addURI(AUTHORITY, BreakfastTable.TABLE_NAME, URI_MATCH_NOTES);
        uriMatcher.addURI(AUTHORITY, BreakfastTable.TABLE_NAME + "/#", URI_MATCH_NOTE_BY_ID);
        System.out.println("som v databaze");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch(uriMatcher.match(uri)) {
            case URI_MATCH_NOTES:
                cursor = listNotes();
                return cursor;
            case URI_MATCH_NOTE_BY_ID:
                long id = ContentUris.parseId(uri);
                cursor = findById(id);
                return cursor;
            default:
                return Defaults.NO_CURSOR;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
