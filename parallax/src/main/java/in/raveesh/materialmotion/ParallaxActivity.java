package in.raveesh.materialmotion;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import in.raveesh.materialmotion.parallax.R;
import in.raveesh.materialmotion.resources.Movie;
import in.raveesh.materialmotion.resources.MovieAdapter;
import in.raveesh.materialmotion.resources.MovieHolder;
import in.raveesh.materialmotion.resources.OnMovieClickListener;
import in.raveesh.materialmotion.resources.ParallaxRecyclerAdapter;
import in.raveesh.materialmotion.resources.Resources;


public class ParallaxActivity extends ActionBarActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.primary_text_default_material_dark));
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();

        final Drawable toolbarBg = toolbar.getBackground();
        toolbarBg.setAlpha(0);

        final List<Movie> movies = Resources.getMovies(this);
        ParallaxRecyclerAdapter<Movie> adapter = new ParallaxRecyclerAdapter<>(movies);
        adapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter.RecyclerAdapterMethods() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
                ((MovieHolder)viewHolder).setMovie(movies.get(i), new OnMovieClickListener() {
                    @Override
                    public void onClick(ImageView poster, Movie movie) {
                        ImageActivity.launch(ParallaxActivity.this, movies.get(i));
                    }
                });
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new MovieHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_with_name, viewGroup, false));
            }

            @Override
            public int getItemCount() {
                return movies.size();
            }
        });
        View hero = LayoutInflater.from(this).inflate(R.layout.hero, recyclerView, false);
        ViewGroup.LayoutParams params = hero.getLayoutParams();
        params.height = (int)getResources().getDimension(R.dimen.hero_height);
        hero.setLayoutParams(params);
        hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.launch(ParallaxActivity.this, new Movie("Guardians of the Galaxy", getResources().getDrawable(R.drawable.guardians)));
            }
        });
        adapter.setParallaxHeader(hero, recyclerView);
        adapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                toolbarBg.setAlpha((int)(percentage*255));
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
