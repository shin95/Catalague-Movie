package com.ibra.moviecatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie implements Parcelable {

    @SerializedName("item")
    private List<MovieResult> movieResults;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public List<MovieResult> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(List<MovieResult> movieResults) {
        this.movieResults = movieResults;
    }

    public Long getmPage() {
        return mPage;
    }

    public void setmPage(Long mPage) {
        this.mPage = mPage;
    }

    public Long getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(Long mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public Long getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(Long mTotalResults) {
        this.mTotalResults = mTotalResults;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.movieResults);
        dest.writeValue(this.mPage);
        dest.writeValue(this.mTotalPages);
        dest.writeValue(this.mTotalResults);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.movieResults = in.createTypedArrayList(MovieResult.CREATOR);
        this.mPage = (Long) in.readValue(Long.class.getClassLoader());
        this.mTotalPages = (Long) in.readValue(Long.class.getClassLoader());
        this.mTotalResults = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
