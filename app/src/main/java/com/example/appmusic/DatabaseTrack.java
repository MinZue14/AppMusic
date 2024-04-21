package com.example.appmusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseTrack extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Track_Database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Track";
    private static final String COLUMN_ID = "TrackID";
    private static final String COLUMN_NAME = "TrackName";
    private static final String COLUMN_GENRE = "TrackGenre";
    private static final String COLUMN_ARTIST = "TrackArtist";
    private static final String COLUMN_VIEW = "TrackView";
    private static final String COLUMN_RELEASE_DATE = "TrackReleaseDate";

    public DatabaseTrack(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_GENRE + " TEXT, " +
                COLUMN_ARTIST + " TEXT, " +
                COLUMN_VIEW + " TEXT, " +
                COLUMN_RELEASE_DATE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public boolean insert(String trackName, String trackGenre, String trackArtist, String trackView, String trackReleaseDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, trackName);
        values.put(COLUMN_GENRE, trackGenre);
        values.put(COLUMN_ARTIST, trackArtist);
        values.put(COLUMN_VIEW, trackView);
        values.put(COLUMN_RELEASE_DATE, trackReleaseDate);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1; // Trả về true nếu insert thành công, ngược lại trả về false
    }

}
