package com.ibra.moviecatalog.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.ibra.moviecatalog.BuildConfig;

public final class DatabaseContract {


    public static final String LANG = "en-US";
    public static final String LINK_IMAGE = "http://image.tmdb.org/t/p/w185/";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String EXTRA_MESSAGE_RECEIVE = "messageRelease";
    public static final String EXTRA_TYPE_RECEIVE = "typeRelease";
    public static final String AUTHORITY = "com.ibra.moviecatalog";
    public static final String SCHEME = "content";
    public final static String KEY_REMINDER_Daily = "DailyTime";
    public final static String KEY_REMINDER_MESSAGE_Release = "reminderMessageRelease";
    public final static String KEY_REMINDER_MESSAGE_Daily = "reminderMessageDaily";
    public final static String PREF_NAME = "reminderPreferences";
    public static final String KEY_HEADER_UPCOMING_REMINDER = "upcomingReminder";
    public static final String KEY_HEADER_DAILY_REMINDER = "dailyReminder";
    public static final String KEY_FIELD_UPCOMING_REMINDER = "checkedUpcoming";
    public static final String KEY_FIELD_DAILY_REMINDER = "checkedDaily";
    public static final String TYPE_REMINDER_PREF = "reminderAlarm";
    public static final String TYPE_REMINDER_RECIEVE = "reminderAlarmRelease";
    public final static int NOTIFICATION_ID = 501;
    public final static int NOTIFICATIONID = 502;
    public static final String EXTRA_MESSAGE_PREF = "message";
    public static String TABLE_NAME ="movie";


    private DatabaseContract(){

    }

    public static final class MovieColums implements BaseColumns{

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