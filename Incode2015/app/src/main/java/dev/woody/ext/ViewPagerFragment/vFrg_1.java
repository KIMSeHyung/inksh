package dev.woody.ext.ViewPagerFragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.Bidi;

import dev.woody.ext.incode2015.R;

/**
 * Created by kimsehyung on 2016-02-23.
 */
public class vFrg_1 extends Fragment{
    private ImageView fr1;
    private RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vfr_1, container, false);
        fr1 = (ImageView)view.findViewById(R.id.vfr1);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.fr_ly1);

        Glide.with(this).load(R.drawable.c1).into(fr1);
        Glide.with(this).load(R.drawable.intro_bg1).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    relativeLayout.setBackground(drawable);
                }
            }
        });

        return view;

    }
}
