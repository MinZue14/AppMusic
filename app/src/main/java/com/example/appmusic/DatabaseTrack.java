package com.example.appmusic;

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
    private static final String COLUMN_ = "";
    private static final String COLUMN_ = "";
    private static final String COLUMN_ = "";
    private static final String COLUMN_ = "";

    public DatabaseTrack(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
