package in.raveesh.materialmotion.resources;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    public void setMovie(final Movie movie, final OnMovieClickListener onMovieClickListener){
        imageView.setImageDrawable(movie.POSTER);
        textView.setText(movie.NAME);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMovieClickListener.onClick(imageView, movie);
            }
        });
    }
}