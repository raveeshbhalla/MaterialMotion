package in.raveesh.materialmotion.resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Raveesh on 06/02/15.
 */
public class ResizeAnimation extends Animation {
    View view;
    int startH;
    int endH;
    int diff;
    ViewGroup.LayoutParams params;

    public static void init(View v, int newHeight){
        init(v,newHeight,null);
    }

    public static void init(View v, int newHeight, AnimationListener listener){
        ResizeAnimation animation = new ResizeAnimation(v,newHeight);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(200);
        if (listener != null) {
            animation.setAnimationListener(listener);
        }
        v.startAnimation(animation);
    }

    public ResizeAnimation(View v, int newh) {
        view = v;
        params = v.getLayoutParams();
        startH = params.height;
        /**
         * If the current height of a view is 0, it won't resize. Hence increasing to 1
         * as a hack around this
         */
        if (startH == 0){
            startH = 1;
            params.height = 1;
            view.setLayoutParams(params);
        }
        endH = newh;
        diff = endH - startH;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight = startH + (int) (diff * interpolatedTime);
        params.height = newHeight;
        view.setLayoutParams(params);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}