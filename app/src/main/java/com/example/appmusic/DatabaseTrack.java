package com.example.appmusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseTrack extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Track_Database";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "Track";
    private static final String COLUMN_ID = "TrackID";
    private static final String COLUMN_NAME = "TrackName";
    private static final String COLUMN_GENRE = "TrackGenre";
    private static final String COLUMN_ARTIST = "TrackArtist";
    private static final String COLUMN_VIEW = "TrackView";
    private static final String COLUMN_IMG = "TrackImg";
    private static final String COLUMN_RELEASE_DATE = "TrackReleaseDate";
    private static final String COLUMN_COUNTRY = "TrackCountry";

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
                COLUMN_COUNTRY + " TEXT, " +
                COLUMN_IMG + " TEXT, " +
                COLUMN_RELEASE_DATE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public boolean insert(String trackName, String trackGenre, String trackArtist, String trackView, String trackCountry, String trackImg, String trackReleaseDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, trackName);
        values.put(COLUMN_GENRE, trackGenre);
        values.put(COLUMN_ARTIST, trackArtist);
        values.put(COLUMN_VIEW, trackView);
        values.put(COLUMN_COUNTRY, trackCountry);
        values.put(COLUMN_IMG, trackImg);
        values.put(COLUMN_RELEASE_DATE, trackReleaseDate);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1; // Trả về true nếu insert thành công, ngược lại trả về false
    }

    public ArrayList<Tracks> getAllTracks() {
        ArrayList<Tracks> trackList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String trackID = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String trackName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String trackGenre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENRE));
                String trackArtist = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTIST));
                String trackView = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VIEW));
                String trackCountry = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY));
                String trackImg = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMG)); // Sử dụng getBlob để lấy dữ liệu từ cột BLOB
                String trackReleaseDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RELEASE_DATE));

                // Tạo đối tượng Tracks từ dữ liệu trong cơ sở dữ liệu và thêm vào danh sách
                Tracks track = new Tracks(trackID, trackName, trackGenre, trackArtist, trackView, trackImg, trackReleaseDate, trackCountry);
                trackList.add(track);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trackList;
    }

    public boolean deleteTrack(Tracks track) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(track.getTrackID())};
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();

        return deletedRows > 0;
    }

}
