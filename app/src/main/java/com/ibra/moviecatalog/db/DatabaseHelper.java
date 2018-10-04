package com.ibra.moviecatalog.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.POSTER;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.RELEASE_DATE;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.TITLE_MOVIE;
import static com.ibra.moviecatalog.db.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbmovie";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_NAME +
            "(" + _ID + " integer primary key," +
            TITLE_MOVIE + " text not null, " +
            RELEASE_DATE + " text not null, " +
            OVERVIEW + " text not null, " +
            POSTER + " text not null);";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
