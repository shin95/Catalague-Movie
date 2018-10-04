package com.ibra.moviecatalog.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ibra.moviecatalog.MainActivity;
import com.ibra.moviecatalog.R;
import com.ibra.moviecatalog.db.MovieHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.BaseColumns._ID;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.CONTENT_URI;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.POSTER;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.RELEASE_DATE;
import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.TITLE_MOVIE;

public class DetailMovie extends AppCompatActivity {


    public static String EXTRA_ID           = "extra_id";
    public static String EXTRA_TITLE        = "extra_title";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_OVERVIEW     = "extra_overview";
    public static String EXTRA_POSTER_DETAIL= "extra_poster_detail";

    private ImageView iv_poster_detail;
    private TextView tv_title_detail,tv_redate_detail,tv_overview_detail;
    private FloatingActionButton fabButton;
    MovieHelper movieHelper;

    private int id_movie;
    private Boolean isFavourite = false;

    String title,overview,poster,release_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        iv_poster_detail = (ImageView) findViewById(R.id.img_detail);
        tv_title_detail = (TextView) findViewById(R.id.tv_title_detail);
        tv_redate_detail = (TextView) findViewById(R.id.tv_release_date_detaill);
        tv_overview_detail = (TextView) findViewById(R.id.tv_overview_detaill);
        fabButton = (FloatingActionButton)findViewById(R.id.fabFavorite);


        setData();
        backButton();
        loadData();


        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavourite) {
                    movieHelper.open();
                    movieHelper.deleteProvider(String.valueOf(id_movie));
                    movieHelper.close();
                    Toast.makeText(DetailMovie.this, title + " Favorite sudah di hapus", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues contentValue = new ContentValues();

                    contentValue.put(_ID, id_movie);
                    contentValue.put(POSTER, poster);
                    contentValue.put(TITLE_MOVIE, title);
                    contentValue.put(RELEASE_DATE, release_date);
                    contentValue.put(OVERVIEW, overview);
                    getContentResolver().insert(CONTENT_URI, contentValue);
                    Toast.makeText(DetailMovie.this, title+ " Berhasil di tambahkan ke Favorite", Toast.LENGTH_SHORT).show();
                    isFavourite = true;
                }
            }
        });
    }

    private void loadData(){
        movieHelper = new MovieHelper(this);
        movieHelper.open();
        isFavourite = movieHelper.getMoviebyId(String.valueOf(id_movie));
        movieHelper.close();
    }

    private void setData(){
        id_movie             = getIntent().getIntExtra(EXTRA_ID,0);
        poster               = getIntent().getStringExtra(EXTRA_POSTER_DETAIL);
        title                = getIntent().getStringExtra(EXTRA_TITLE);
        release_date         = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        overview             = getIntent().getStringExtra(EXTRA_OVERVIEW);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date                       = dateFormat.parse(release_date);
            SimpleDateFormat newdateformat  = new SimpleDateFormat("yyyy-MM-dd");
            String date_of_release          = newdateformat.format(date);
            tv_redate_detail.setText(date_of_release);

        }catch (ParseException e){
            e.printStackTrace();
        }

        tv_title_detail.setText(title);
        tv_overview_detail.setText(overview);


        Glide.with(DetailMovie.this).load("http://image.tmdb.org/t/p/w500/" + poster)
                .override(350,350)
                .into(iv_poster_detail);
    }

    public void backButton(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}

