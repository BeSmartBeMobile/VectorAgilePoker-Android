package com.vectormobile.agilepoker.utils.glide;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by daniel on 4/3/17.
 */

public class GlideWrapper {

    private Context context;

    public GlideWrapper(Context context) {
        this.context = context;
    }

    public void loadImage(ImageView view, String imageToLoad) {
        Glide.with(context)
                .load(imageToLoad)
                .into(view);
    }

    public void loadImage(ImageView view, int imageToLoad) {
        Glide.with(context)
                .load(imageToLoad)
                .into(view);
    }
}
