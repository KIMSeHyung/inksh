package dev.woody.ext.Bus.mains;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import dev.woody.ext.incode2015.R;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Map_popup extends Activity {
    private ImageView mapview;
    private ImageView xbtn;
    private PhotoViewAttacher photoView;
    private RelativeLayout popupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_popup);
        mapview = (ImageView)findViewById(R.id.mapview);
        xbtn = (ImageView)findViewById(R.id.xbtn);

        if(MainActivity.flag == 0){
            Glide.with(this).load(R.drawable.deyeonmap).into(mapview);
        }
        else{
            Glide.with(this).load(R.drawable.yongdangap).into(mapview);
        }
        photoView = new PhotoViewAttacher(mapview);
        xbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        theme.applyStyle(android.R.style.Theme_Panel, true);
    }
}
