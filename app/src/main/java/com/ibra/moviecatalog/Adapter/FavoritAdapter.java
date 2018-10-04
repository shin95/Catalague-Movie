package com.ibra.moviecatalog.Adapter;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibra.moviecatalog.model.MovieItem;
import com.ibra.moviecatalog.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder>{

    private Cursor cursorMovie;
    private Activity activity;

    public FavoritAdapter(Activity activity){
        this.activity = activity;
    }

    public void setListMovie(Cursor cursorMovie){
        this.cursorMovie = cursorMovie;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieItem movieitem = getItem(position);

        Glide.with(activity).load("http://image.tmdb.org/t/p/w154/" + movieitem.getPoster()).into(holder.imageView);
        holder.tvTitle.setText(movieitem.getTitle());
        holder.tvOverview.setText(movieitem.getOverview());

        String retrieveDate = movieitem.getReleasedate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = date_format.parse(retrieveDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String release_date = new_date_format.format(date);
            holder.tvRelease.setText(release_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailMovie.class);
                intent.putExtra(DetailMovie.EXTRA_ID, movieitem.getId());
                intent.putExtra(DetailMovie.EXTRA_TITLE, movieitem.getTitle());
                intent.putExtra(DetailMovie.EXTRA_RELEASE_DATE, movieitem.getReleasedate());
                intent.putExtra(DetailMovie.EXTRA_OVERVIEW, movieitem.getOverview());
                intent.putExtra(DetailMovie.EXTRA_POSTER_DETAIL, movieitem.getPoster());
                activity.startActivity(intent);
            }
        });

    }

    public MovieItem getItem(int position){
        if (!cursorMovie.moveToPosition(position)){
            throw new IllegalStateException("Position invalid");
        }
        return new MovieItem(cursorMovie);
    }

    @Override
    public int getItemCount() {
        if (cursorMovie == null) return 0;
        return cursorMovie.getCount();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnDetail;
        private ImageView imageView;
        private TextView tvTitle, tvOverview, tvRelease;

        public ViewHolder(View itemView) {
            super(itemView);

            btnDetail = itemView.findViewById(R.id.btn_Detail);
            imageView = itemView.findViewById(R.id.iv_poster_detail_rv);
            tvTitle = itemView.findViewById(R.id.title_detail_rv);
            tvOverview = itemView.findViewById(R.id.overview_rv);
            tvRelease = itemView.findViewById(R.id.release_date_detail_rv);
        }
    }
}

