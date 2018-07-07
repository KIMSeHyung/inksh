package dev.woody.ext.Bus.mains;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dev.woody.ext.Bus.fragments.toolbar_frag;
import dev.woody.ext.incode2015.R;
import uk.co.senab.photoview.PhotoViewAttacher;

public class TimeTable extends AppCompatActivity {
    private ImageView table;
    private FragmentManager fm;
    private PhotoViewAttacher phtoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        Fragment toolbar = new toolbar_frag();
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.toolbar_place_timetable, toolbar).commit();

        table = (ImageView)findViewById(R.id.timetable);
        Glide.with(this).load(R.drawable.timetable).override(800,3300).into(table);
        phtoview = new PhotoViewAttacher(table);
    }
}
