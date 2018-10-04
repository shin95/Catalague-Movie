package com.ibra.moviecatalog.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.POSTER;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.RELEASE_DATE;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.TITLE_MOVIE;
import static com.ibra.moviecatalog.db.DatabaseContract.getColumnInt;
import static com.ibra.moviecatalog.db.DatabaseContract.getColumnString;

public class MovieItem implements Parcelable {

    private int id;
    private String title;
    private String releasedate;
    private String overview;
    private String poster;
    private String rate;
    private String rate_count;
    private List<MovieItem> Smovie;

    public MovieItem (JSONObject object){
        try {

            this.id             = object.getInt("id");
            String title1       = object.getString("title");
            String rlsdate      = object.getString("release_date");
            String overview1    = object.getString("overview");
            String rating       = object.getString("vote_average");
            String rating_count = object.getString("vote_count");
            String poster1      = object.getString("poster_path");

            this.id             = id;
            this.title          = title1;
            this.releasedate    = rlsdate;
            this.overview       = overview1;
            this.rate           = rating;
            this.rate_count     = rating_count;
            this.poster         = poster1;


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate_count() {
        return rate_count;
    }

    public void setRate_count(String rate_count) {
        this.rate_count = rate_count;
    }

    public List<MovieItem> getSmovie() {
        return Smovie;
    }

    public void setSmovie(List<MovieItem> smovie) {
        Smovie = smovie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.releasedate);
        dest.writeString(this.overview);
        dest.writeString(this.poster);
        dest.writeString(this.rate);
        dest.writeString(this.rate_count);
        dest.writeList(this.Smovie);
    }

    public MovieItem() {
    }

    public MovieItem(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.poster = getColumnString(cursor, POSTER);
        this.title = getColumnString(cursor, TITLE_MOVIE);
        this.releasedate = getColumnString(cursor, RELEASE_DATE);
        this.overview = getColumnString(cursor, OVERVIEW);
    }

    public MovieItem(int id, String title, String poster, String overview, String releasedate){
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.releasedate = releasedate;
    }

    protected MovieItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.releasedate = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.rate = in.readString();
        this.rate_count = in.readString();
        this.Smovie = new ArrayList<MovieItem>();
        in.readList(this.Smovie, MovieItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}