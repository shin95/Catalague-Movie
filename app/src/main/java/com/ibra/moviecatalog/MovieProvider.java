package com.ibra.moviecatalog;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Switch;

import com.ibra.moviecatalog.db.MovieHelper;

import static com.ibra.moviecatalog.db.DatabaseContract.AUTHORITY;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.CONTENT_URI;
import static com.ibra.moviecatalog.db.DatabaseContract.TABLE_NAME;

public class MovieProvider extends ContentProvider {

    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;

    private static final UriMatcher mUriMacther = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        mUriMacther.addURI(AUTHORITY, TABLE_NAME, MOVIE);

        mUriMacther.addURI(AUTHORITY,
                TABLE_NAME + "/#", MOVIE_ID);
    }

    private MovieHelper movieHelper;

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        movieHelper.open();
        return true;
    }


    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
        Cursor cursor;
        switch (mUriMacther.match(uri)){
            case MOVIE:
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }


    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues contentValues) {
        long added;

        switch (mUriMacther.match(uri)){
            case MOVIE:
                added = movieHelper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }
        if (added > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added );
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        int deleted;
        switch (mUriMacther.match(uri)){
            case MOVIE_ID:
                deleted = movieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update( Uri uri,  ContentValues contentValues, String selection,  String[] selectionArgs) {
        int updated;
        switch (mUriMacther.match(uri)){
            case MOVIE_ID:
                updated = movieHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;
            default:
                updated = 0;
                break;
        }

        if (updated > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return updated;
    }
}
