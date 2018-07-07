package dev.woody.ext.ViewPagerFragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import dev.woody.ext.incode2015.R;

/**
 * Created by kimsehyung on 2016-02-23.
 */
public class vFrg_2 extends Fragment {
    private ImageView fr2;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vfr_2, container, false);
        fr2 = (ImageView)view.findViewById(R.id.vfr2);
        Glide.with(this).load(R.drawable.c2).into(fr2);
        linearLayout = (LinearLayout)view.findViewById(R.id.fr_ly2);

        Glide.with(this).load(R.drawable.intro_bg2).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    linearLayout.setBackground(drawable);
                }
            }
        });
        return view;

    }
}
