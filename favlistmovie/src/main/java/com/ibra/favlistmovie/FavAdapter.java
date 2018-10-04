package com.ibra.favlistmovie;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ibra.favlistmovie.DatabaseContract.MovieColums.OVERVIEW;
import static com.ibra.favlistmovie.DatabaseContract.MovieColums.POSTER;
import static com.ibra.favlistmovie.DatabaseContract.MovieColums.RELEASE_DATE;
import static com.ibra.favlistmovie.DatabaseContract.MovieColums.TITLE_MOVIE;
import static com.ibra.favlistmovie.DatabaseContract.getColumnString;

public class FavAdapter extends CursorAdapter {


    public FavAdapter(Context context, Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_favorit, viewGroup, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        if (cursor != null) {

            ImageView poster_list;
            TextView tvTitle,tvOverview,tvrdate;
            CardView cardView;

            poster_list = view.findViewById(R.id.poster_list);
            tvTitle = view.findViewById(R.id.tv_list_title);
            tvOverview = view.findViewById(R.id.tv_list_overview);
            tvrdate = view.findViewById(R.id.tv_list_release_date);
            cardView = view.findViewById(R.id.cardview);

            tvTitle.setText(getColumnString(cursor, TITLE_MOVIE));
            tvOverview.setText(getColumnString(cursor, OVERVIEW));
            tvrdate.setText(getColumnString(cursor, RELEASE_DATE));
            final String poster = "http://image.tmdb.org/t/p/w185" + getColumnString(cursor, POSTER);
            Glide.with(context)
                    .load(poster)
                    .into(poster_list);

            String retrieveDate = getColumnString(cursor, RELEASE_DATE);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date = dateFormat.parse(retrieveDate);
                SimpleDateFormat newDateFormat = new SimpleDateFormat("EEE, dd MMMM yyyy");
                String releaseDate = newDateFormat.format(date);
                tvrdate.setText(releaseDate);
            }catch (ParseException e){
                e.printStackTrace();
            }

        }

    }
}
