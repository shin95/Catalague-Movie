package com.ibra.moviecatalog.Fragment;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibra.moviecatalog.Adapter.FavoritAdapter;
import com.ibra.moviecatalog.R;

import static com.ibra.moviecatalog.db.DatabaseContract.MovieColums.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;

    private Cursor cursor;
    private FavoritAdapter favoritAdapter;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_favorite, container, false);
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = view.findViewById(R.id.rvFavorite);
        favoritAdapter = new FavoritAdapter(getActivity());
        favoritAdapter.notifyDataSetChanged();
        favoritAdapter.setListMovie(cursor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoritAdapter);
        new loadMovieAsyncTask().execute();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadMovieAsyncTask().execute();
    }

    private class loadMovieAsyncTask extends AsyncTask<Void, Void, Cursor>{

        @Override
        protected Cursor doInBackground(Void... voids) {
            return  getActivity().getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );

        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            cursor = cursor;
            favoritAdapter.setListMovie(cursor);
            favoritAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
