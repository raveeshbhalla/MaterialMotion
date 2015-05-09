package in.raveesh.materialmotion.resources;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Raveesh on 08/05/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieHolder>{

    private List<Movie> mList;
    private OnMovieClickListener onMovieClickListener;

    public MovieAdapter(List<Movie> movieList, OnMovieClickListener listener){
        mList = movieList;
        onMovieClickListener = listener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_with_name, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.setMovie(mList.get(position), onMovieClickListener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
