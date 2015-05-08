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
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

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
        holder.setMovie(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private View root;

        public MovieHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.poster);
            textView = (TextView)itemView.findViewById(R.id.title);
            root = itemView;
        }

        public void setMovie(final Movie movie){
            imageView.setImageDrawable(movie.POSTER);
            textView.setText(movie.NAME);

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieClickListener.onClick(movie);
                }
            });
        }
    }
}
