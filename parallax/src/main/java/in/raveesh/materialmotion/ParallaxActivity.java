package in.raveesh.materialmotion;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import in.raveesh.materialmotion.parallax.R;
import in.raveesh.materialmotion.resources.Movie;
import in.raveesh.materialmotion.resources.MovieAdapter;
import in.raveesh.materialmotion.resources.OnMovieClickListener;
import in.raveesh.materialmotion.resources.Resources;


public class ParallaxActivity extends ActionBarActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    ImageView hero;
    ViewGroup.LayoutParams heroParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.primary_text_default_material_dark));
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        hero = (ImageView) findViewById(R.id.hero);
        hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.launch(ParallaxActivity.this, new Movie("Guardians of the Galaxy", getResources().getDrawable(R.drawable.guardians)));
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();

        final Drawable toolbarBg = toolbar.getBackground();
        toolbarBg.setAlpha(0);

        MovieAdapter adapter = new MovieAdapter(Resources.getMovies(this), new OnMovieClickListener() {
            @Override
            public void onClick(Movie movie) {
                ImageActivity.launch(ParallaxActivity.this, movie);
            }
        });
        recyclerView.setAdapter(adapter);

        heroParams = hero.getLayoutParams();
        final int heroHeight = heroParams.height;

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int totalScroll = 0;
            int initialHeight = heroParams.height;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalScroll += dy;

                int alpha = totalScroll/heroHeight * 255;
                if (alpha > 255){
                    alpha = 255;
                }
                else if (alpha < 0){
                    alpha = 0;
                }

                toolbarBg.setAlpha(alpha);

                if (totalScroll < heroHeight){
                    heroParams.height = initialHeight - totalScroll;
                    hero.setLayoutParams(heroParams);
                }
                else{
                    super.onScrolled(recyclerView, dx, dy);
                }
            }
        });
    }
}
