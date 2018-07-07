package dev.woody.ext.ViewPagerFragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import dev.woody.ext.incode2015.R;

/**
 * Created by kimsehyung on 2016-02-23.
 */
public class vFrg_3 extends Fragment {
    private ImageView fr3, exit_btn;
    private RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.vfr_3, container, false);
        fr3 = (ImageView)view.findViewById(R.id.vfr3);
        exit_btn = (ImageView)view.findViewById(R.id.exit_btn);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.fr_ly3);

        Glide.with(this).load(R.drawable.c3).into(fr3);

        Glide.with(this).load(R.drawable.intro_bg3).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    relativeLayout.setBackground(drawable);
                }
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
}
