package in.raveesh.materialmotion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import in.raveesh.materialmotion.parallax.R;
import in.raveesh.materialmotion.resources.Movie;
import in.raveesh.materialmotion.resources.Resources;


public class ImageActivity extends ActionBarActivity {

    public static void launch(Context context, Movie movie){
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("movie", movie.NAME);
        context.startActivity(intent);
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
            TextView title = (TextView)findViewById(R.id.title);

            poster.setImageDrawable(movie.POSTER);
            title.setText(movie.NAME);
        }
    }
}
