package in.raveesh.materialmotion.resources;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raveesh on 08/05/15.
 */
public class Resources {

    public static int getPoster(Context context, String movie){
        String[] array = context.getResources().getStringArray(R.array.movies);
        if (movie.equals(array[0])){
            return R.drawable.hulk;
        }
        else if (movie.equals(array[1])){
            return R.drawable.ironman;
        }
        else if (movie.equals(array[2])){
            return R.drawable.ironman2;
        }
        else if (movie.equals(array[3])){
            return R.drawable.thor;
        }
        else if (movie.equals(array[4])){
            return R.drawable.captainamerica;
        }
        else if (movie.equals(array[5])){
            return R.drawable.avengers;
        }
        else if (movie.equals(array[6])){
            return R.drawable.ironman3;
        }
        else if (movie.equals(array[7])){
            return R.drawable.thor2;
        }
        else if (movie.equals(array[8])){
            return R.drawable.captainamerica2;
        }
        else if (movie.equals(array[9])){
            return R.drawable.avengers2;
        }
        else if (movie.equals("Guardians of the Galaxy")){
            return R.drawable.guardians;
        }
        else{
            return -1;
        }
    }

    public static List<Movie> getMovies(Context context){
        String[] array = context.getResources().getStringArray(R.array.movies);
        List<Movie> movies = new ArrayList<>();
        for (String title: array){
            movies.add(new Movie(title, context.getResources().getDrawable(getPoster(context, title))));
        }
        return movies;
    }
}
