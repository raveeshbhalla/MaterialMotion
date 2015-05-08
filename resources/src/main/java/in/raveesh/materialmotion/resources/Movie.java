package in.raveesh.materialmotion.resources;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by Raveesh on 08/05/15.
 */
public class Movie {
    public String NAME;
    public Drawable POSTER;

    public Movie(String name, Drawable poster){
        this.NAME = name;
        this.POSTER = poster;
    }
}
