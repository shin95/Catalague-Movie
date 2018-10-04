package com.ibra.favlistmovie;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final String AUTHORITY = "com.ibra.moviecatalog";
    public static final String SCHEME = "content";
    public static String TABLE_NAME ="movie";

    private DatabaseContract(){

    }

    public static final class MovieColums implements BaseColumns {

        public static String POSTER = "poster";
        public static String TITLE_MOVIE = "title";
        public static String OVERVIEW   = "overview";
        public static String RELEASE_DATE = "release_date";


        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }


    public static String getColumnString(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName){
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}