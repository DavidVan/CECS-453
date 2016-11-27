package net.davidvan.mapsandsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by David on 11/26/2016.
 */

public class LocationsDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "markersDatabase";
    private static final String TABLE_NAME = "Markers";
    private static final String ID_COLUMN = "_id";
    private static final String LATITUDE_COLUMN = "latitude";
    private static final String LONGITUDE_COLUMN = "longitude";
    private static final String ZOOM_LEVEL_COLUMN = "zoomLevel";

    public LocationsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                                ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                LATITUDE_COLUMN + " FLOAT, " +
                                LONGITUDE_COLUMN + " FLOAT, " +
                                ZOOM_LEVEL_COLUMN + " FLOAT" +
                             ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insertMarker(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, values); // Returned ID of row just inserted.
    }

    public Cursor getAllMarkers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[] {LATITUDE_COLUMN, LONGITUDE_COLUMN, ZOOM_LEVEL_COLUMN}, null, null, null, null, null);
    }

    public int deleteAllMarkers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }

}
