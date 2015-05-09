package in.raveesh.materialmotion;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import in.raveesh.materialmotion.highlight.R;
import in.raveesh.materialmotion.resources.Movie;
import in.raveesh.materialmotion.resources.Resources;


public class ImageActivity extends ActionBarActivity {

    Movie movie;

    public static void launch(Context context, ImageView poster, Movie movie) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, poster, movie.NAME);
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("movie", movie.NAME);
        context.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        if (getIntent().getStringExtra("movie") != null) {
            String movieName = getIntent().getStringExtra("movie");
            movie = new Movie(movieName, getResources().getDrawable(Resources.getPoster(this, movieName)));
        }

        if (movie != null) {
            ImageView poster = (ImageView) findViewById(R.id.poster);
            poster.setTransitionName(movie.NAME);
            TextView title = (TextView) findViewById(R.id.title);

            poster.setImageDrawable(movie.POSTER);
            title.setText(movie.NAME);
        }
        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ImageButton trailer = (ImageButton) findViewById(R.id.trailer);
                trailer.setVisibility(View.INVISIBLE);
                trailer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String youtube = "https://www.youtube.com/results?search_query=" + movie.NAME + " official trailer";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(youtube));
                        ImageActivity.this.startActivity(intent);
                    }
                });

                int cx = (trailer.getLeft() + trailer.getRight()) / 2;
                int cy = (trailer.getTop() + trailer.getBottom()) / 2;

                int finalRadius = Math.max(trailer.getWidth(), trailer.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(trailer, cx, cy, 0, finalRadius);

                trailer.setVisibility(View.VISIBLE);
                anim.start();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
