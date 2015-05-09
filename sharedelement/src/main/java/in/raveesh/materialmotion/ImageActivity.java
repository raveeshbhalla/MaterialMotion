package in.raveesh.materialmotion;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import in.raveesh.materialmotion.resources.Movie;
import in.raveesh.materialmotion.resources.Resources;
import in.raveesh.materialmotion.sharedelement.R;


public class ImageActivity extends ActionBarActivity {

    public static void launch(Context context, ImageView poster, Movie movie){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, poster, movie.NAME);
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("movie", movie.NAME);
        context.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Movie movie = null;
        if (getIntent().getStringExtra("movie") != null){
            String movieName = getIntent().getStringExtra("movie");
            movie = new Movie(movieName, getResources().getDrawable(Resources.getPoster(this, movieName)));
        }

        if (movie != null){
            ImageView poster = (ImageView)findViewById(R.id.poster);
            poster.setTransitionName(movie.NAME);
            TextView title = (TextView)findViewById(R.id.title);

            poster.setImageDrawable(movie.POSTER);
            title.setText(movie.NAME);
        }
    }
}
