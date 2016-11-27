package net.davidvan.mapsandsqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by David on 11/26/2016.
 */

public class LocationsContentProvider extends ContentProvider {

    private static final String PROVIDER_NAME = "net.davidvan.mapsandsqlite.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/markers");
    private static final int MARKERS = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PROVIDER_NAME, "markers", MARKERS);
    }

    private LocationsDB database;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        database = new LocationsDB(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri) == MARKERS) {
            return database.getAllMarkers();
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = database.insertMarker(values);
        Uri anUri = null;
        if (row > 0) {
            anUri = ContentUris.withAppendedId(CONTENT_URI, row);
        }
        return anUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.deleteAllMarkers();
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

}
