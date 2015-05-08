package in.raveesh.materialmotion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import in.raveesh.materialmotion.resources.Movie;
import in.raveesh.materialmotion.resources.MovieAdapter;
import in.raveesh.materialmotion.resources.OnMovieClickListener;
import in.raveesh.materialmotion.resources.Resources;


public class ParallaxActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.primary_text_default_material_dark));
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MovieAdapter adapter = new MovieAdapter(Resources.getMovies(this), new OnMovieClickListener() {
            @Override
            public void onClick(Movie movie) {
                ImageActivity.launch(ParallaxActivity.this, movie);
            }
        });
        recyclerView.setAdapter(adapter);

        ImageView hero = (ImageView)findViewById(R.id.hero);
        hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.launch(ParallaxActivity.this, new Movie("Guardians of the Galaxy", getResources().getDrawable(R.drawable.guardians)));
            }
        });
    }
}
