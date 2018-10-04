package com.ibra.moviecatalog.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
import java.util.ArrayList;
import java.util.Date;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {

    public final static String EXTRA_MOVIE = "movie";
    private ArrayList<MovieItem> mMovieItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public RecyAdapter(final Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItem> items){
        mMovieItems = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItem item){
        mMovieItems.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public MovieItem getItem(int position){
        return mMovieItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        if (mMovieItems == null)return 0;
        return mMovieItems.size();
    }

    public void clearData(){
        mMovieItems.clear();
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_title_list.setText(mMovieItems.get(position).getTitle());
        holder.tv_rdate_list.setText(mMovieItems.get(position).getReleasedate());
        holder.tv_overview_list.setText(mMovieItems.get(position).getOverview());

        Glide.with(context).load("http://image.tmdb.org/t/p/w154/" + mMovieItems.get(position)
                .getPoster()).into(holder.iv_poster_list);

        String datefound = mMovieItems.get(position).getReleasedate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(datefound);
            SimpleDateFormat new_dateformat = new SimpleDateFormat("EEEE, MM dd,yyyy");
            String release_date = new_dateformat.format(date);
            holder.tv_rdate_list.setText(release_date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovie.class);

                intent.putExtra(DetailMovie.EXTRA_ID, mMovieItems.get(position).getId());
                intent.putExtra(DetailMovie.EXTRA_TITLE, mMovieItems.get(position).getTitle());
                intent.putExtra(DetailMovie.EXTRA_OVERVIEW, mMovieItems.get(position).getOverview());
                intent.putExtra(DetailMovie.EXTRA_POSTER_DETAIL, mMovieItems.get(position).getPoster());
                intent.putExtra(DetailMovie.EXTRA_RELEASE_DATE, mMovieItems.get(position).getReleasedate());

                context.startActivity(intent);
            }
        });

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_poster_list;
        TextView tv_title_list;
        TextView tv_rdate_list;
        TextView tv_overview_list;
        Button btnDetail;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_poster_list   = (ImageView)itemView.findViewById(R.id.iv_poster_detail_rv);
            tv_title_list    = (TextView) itemView.findViewById(R.id.title_detail_rv);
            tv_rdate_list    = (TextView)itemView.findViewById(R.id.release_date_detail_rv);
            tv_overview_list = (TextView)itemView.findViewById(R.id.overview_rv);
            btnDetail       = (Button)itemView.findViewById(R.id.btn_Detail);

        }
    }
}
