package com.ibra.moviecatalog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ibra.moviecatalog.model.MovieItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.POSTER;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.RELEASE_DATE;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.TITLE_MOVIE;
import static com.ibra.moviecatalog.db.DatabaseContract.TABLE_NAME;

public class MovieHelper {

    private static String DATABASE_TABLE = TABLE_NAME;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public MovieHelper(Context context){
        this.context = context;
    }

    public MovieHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<MovieItem> query(){
        ArrayList<MovieItem> arrayList = new ArrayList<MovieItem>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, _ID + " DESC"
                , null);
        cursor.moveToFirst();
        MovieItem movieList;
        if (cursor.getCount() > 0){
            do {
                movieList = new MovieItem();
                movieList.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movieList.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                movieList.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_MOVIE)));
                movieList.setReleasedate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                movieList.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                arrayList.add(movieList);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insert(MovieItem movieItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, movieItem.getId());
        contentValues.put(POSTER, movieItem.getPoster());
        contentValues.put(TITLE_MOVIE, movieItem.getTitle());
        contentValues.put(RELEASE_DATE, movieItem.getReleasedate());
        contentValues.put(OVERVIEW, movieItem.getOverview());
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int update(MovieItem movieItem){
        ContentValues initialValues = new ContentValues();
        initialValues.put(_ID, movieItem.getId());
        initialValues.put(POSTER, movieItem.getPoster());
        initialValues.put(TITLE_MOVIE, movieItem.getTitle());
        initialValues.put(RELEASE_DATE, movieItem.getReleasedate());
        initialValues.put(OVERVIEW, movieItem.getOverview());
        return database.update(DATABASE_TABLE, initialValues, _ID + " = '" + movieItem.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(TABLE_NAME, _ID + " ='" + id + "'", null);
    }

    public boolean getMoviebyId(String id){
        boolean result = false;
        Cursor cursor = database.query(DATABASE_TABLE,
                        null,
                        _ID + " = '"+id+"'",
                        null,
                        null,
                        null,
                        _ID + " ASC",
                        null);
        cursor.moveToFirst();
        ArrayList<MovieItem> arrayList =  new ArrayList<>();
        MovieItem movieItem;
        if (cursor.getCount() > 0){
            result = true;
        }
        cursor.close();
        return result;
    }

    public boolean getMoviebyName(String title){
        boolean result = false;
        Cursor cursor = database.query(DATABASE_TABLE,
                null,
                TITLE_MOVIE + " = '"+title+"'",
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<MovieItem> arrayList = new ArrayList<>();
        MovieItem movieItem;
        if (cursor.getCount() > 0) {
            result = true;
        }

        cursor.close();
        return result;
    }


    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE, null,
                _ID + "=?",
                 new String[]{id},
                null,
                null,
                null,
                null);
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC");
    }

    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }

    public int updateProvider(String id, ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }

}
