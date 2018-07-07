package dev.woody.ext.Bus.fragments;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import dev.woody.ext.Bus.adapter.ListAdapter;
import dev.woody.ext.Bus.listpage.ListPage;
import dev.woody.ext.incode2015.R;

/**
 * Created by kimsehyung on 2016-02-24.
 */
public class yd_frag extends Fragment {
    private ImageView bg, big, middle, small, refresh_btn, refresh_text, big_text;
    private ListView lv;
    private ListAdapter mAdapter;

    private SimpleDateFormat df;

    private int day = java.util.Calendar.getInstance().get(Calendar.DAY_OF_WEEK); //요일
    //1 : 일 , 2 : 월 ....

    public ArrayList<ListPage> listpage;
    public TextView ytcheck;
    public ImageView minafter, continuetime, finished, arrived;
    public String nowTime = "";

    private Timer timer;
    private TimerTask timerTask;

    private Animation refresh_ani;
    private Animation circle_rotate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.yd_frag_layout, container, false);
        bg = (ImageView)v.findViewById(R.id.yd_bg_img);
        big = (ImageView)v.findViewById(R.id.yd_bigcircle);
        middle = (ImageView)v.findViewById(R.id.yd_middle);
        small = (ImageView)v.findViewById(R.id.yd_smallcircle);
        minafter = (ImageView)v.findViewById(R.id.min_after_yd);
        continuetime = (ImageView)v.findViewById(R.id.continuetime_yd);
        finished = (ImageView)v.findViewById(R.id.finished_yd);
        arrived = (ImageView)v.findViewById(R.id.arrived_yd);
        refresh_btn = (ImageView)v.findViewById(R.id.refresh_btn_yd);
        refresh_text = (ImageView)v.findViewById(R.id.refresh_text_yd);
        big_text = (ImageView)v.findViewById(R.id.yd_big_text);

        ytcheck = (TextView)v.findViewById(R.id.ytcheck);
        ytcheck.setTypeface(Typeface.createFromAsset(v.getContext().getAssets(), "FUTURABI.otf"));


        lv = (ListView)v.findViewById(R.id.yd_lv);
        listpage = new ArrayList<ListPage>();

        Glide.with(this).load(R.drawable.dy_smallcircle).into(small);
        Glide.with(this).load(R.drawable.yd_bg2).into(bg);
        Glide.with(this).load(R.drawable.main_bigcir).into(big);
        Glide.with(this).load(R.drawable.yd_middlebox).into(middle);

        if(day == 7){
            Glide.with(this).load(R.drawable.yd_text_sat).into(big_text);
        }
        else{
            Glide.with(this).load(R.drawable.yd_text_big).into(big_text);
        }

        /////////////////
        df = new SimpleDateFormat("kk:mm");
        listpage = new ArrayList<ListPage>();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                nowTime = df.format(new Date(System.currentTimeMillis())); //현재시간
                new GetTableTask(nowTime, "ytod").execute();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 5000);

        refresh_ani = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_text_ani);
        refresh_text.setAnimation(refresh_ani);
        refresh_text.setVisibility(View.GONE);

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh_text.setVisibility(View.VISIBLE);
                nowTime = df.format(new Date(System.currentTimeMillis())); //현재시간
                new GetTableTask(nowTime, "ytod").execute();
                refresh_text.startAnimation(refresh_ani);
            }
        });

        circle_rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                big.startAnimation(circle_rotate);
            }
        }, 1000);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new ListAdapter(getActivity(), listpage);
        lv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public class GetTableTask extends AsyncTask<String, Void, String> {
        String url = "http://incoders.co.kr/incode/busTime.php";
        StringBuilder sb;
        InputStream is;
        OutputStream os;

        private String nowTime;
        private String where;

        public GetTableTask(String nowTime, String where) {
            this.nowTime = nowTime;
            this.where = where;
        }

        @Override
        protected String doInBackground(String... params) {
            sb = new StringBuilder();

            try {
                URL url = new URL(this.url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setConnectTimeout(5000);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "appication/json");

                JSONObject object = new JSONObject();
                object.put("nowtime", nowTime);
                object.put("where", where);
                String dbname;
                if(day != 7){
                    dbname = "bk_bus_time";
                }
                else{
                    dbname = "bk_bus_time_sat";
                }
                Log.i("dbname", dbname);
                object.put("dbname", dbname);

                os = conn.getOutputStream();
                os.write(object.toString().getBytes());
                os.flush();
                os.close();

                is = conn.getInputStream();
                int rq = conn.getResponseCode();
                Log.i("rq", rq + "");
                if (rq == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) break;
                        sb.append(line + "\n");
                    }
                    br.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listpage.clear();
            int rq = 0;
            try {
                JSONArray arry = new JSONArray(s);

                if(arry.length() == 0){ // 0이면 운행시간이 지남
                    ytcheck.setVisibility(View.GONE);
                    minafter.setVisibility(View.GONE);
                    continuetime.setVisibility(View.GONE);
                    finished.setVisibility(View.VISIBLE);
                    arrived.setVisibility(View.GONE);
                }
                else{
                    ytcheck.setVisibility(View.VISIBLE);
                    minafter.setVisibility(View.VISIBLE);
                    continuetime.setVisibility(View.GONE);
                    finished.setVisibility(View.GONE);
                    arrived.setVisibility(View.GONE);
                }

                for (int i = 0; i < arry.length(); i++) {
                    JSONObject object = arry.getJSONObject(i);
                    if(rq == 1) break;
                    if(i == 0){
                        if(day == 7) {
                            rq = setTimecheckerSat(object.getString(where));
                            continue;
                        }
                        else if(day == 1){
                            setTimecheckerSun();
                            break;
                        }
                        else {
                            rq = setTimechecker(object.getString(where));
                            continue;
                        }
                    }
                    if(day != 7) {
                        listTask(object.getString(where), i);
                    }
                    else if(day == 7){
                        listTaskSat(object.getString(where), i);
                    }
                    Log.i("time", object.getString(where));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mAdapter.notifyDataSetChanged();
        }
    }
    public void listTask(String time, int index) throws ParseException {

        Time_cal time_cal = new Time_cal(time);

        int duration = time_cal.getTime();
        int hour = duration / 60;
        int min = duration % 60;
        if(time.equals("10:30:00")){
            listpage.add(new ListPage(1, 0, 0, R.drawable.next_yd, R.drawable.contents1030));
            Log.i("contime", "10");
            return;
        }
        else if(time.equals("18:30:00")){
            Log.i("contime", "18");
            listpage.add(new ListPage(1, 0, 0, R.drawable.next_yd, R.drawable.contents1830));
            return;
        }
        if(time.equals("22:35:00")){
            listpage.add(new ListPage(0, min, hour, R.drawable.last_yd, R.drawable.text3));
            return;
        }
        listpage.add(new ListPage(0, min, hour,R.drawable.next_yd,R.drawable.text3));
        mAdapter.notifyDataSetChanged();
    }

    public void listTaskSat(String time, int index) throws ParseException {

        Time_cal time_cal = new Time_cal(time);

        int duration = time_cal.getTime();
        int hour = duration / 60;
        int min = duration % 60;
        if(time.equals("13:10:00")){
            listpage.add(new ListPage(0, min, hour, R.drawable.last_yd, R.drawable.text3));
            return;
        }
        listpage.add(new ListPage(0, min, hour,R.drawable.next_yd,R.drawable.text3));
        mAdapter.notifyDataSetChanged();
    }

    public int setTimechecker(String time) throws ParseException {
        Time_cal time_cal = new Time_cal(time);

        int duration = time_cal.getTime();
        Log.i("arrived", duration + "");
        int hour = duration / 60;
        int min = duration % 60;

        if(time.equals("08:15:00")){
            ytcheck.setVisibility(View.GONE);
            finished.setVisibility(View.VISIBLE);
            continuetime.setVisibility(View.GONE);
            minafter.setVisibility(View.GONE);
            arrived.setVisibility(View.GONE);
            if(duration < 60) {
                listpage.add(new ListPage(0, min, hour, R.drawable.first_yd, R.drawable.text3));
            }
            return 1;
        }

        if(time.equals("10:30:00")){
            ytcheck.setVisibility(View.GONE);
            finished.setVisibility(View.GONE);
            continuetime.setVisibility(View.VISIBLE);
            continuetime.setImageResource(R.drawable.susi_mor);
            minafter.setVisibility(View.GONE);
            arrived.setVisibility(View.GONE);
            listpage.add(new ListPage(1, 0, 0, R.drawable.next_yd, R.drawable.contents1030));
            return 0;
        }
        if(time.equals("18:30:00")){
            ytcheck.setVisibility(View.GONE);
            finished.setVisibility(View.GONE);
            continuetime.setVisibility(View.VISIBLE);
            continuetime.setImageResource(R.drawable.susi_aft);
            minafter.setVisibility(View.GONE);
            arrived.setVisibility(View.GONE);
            listpage.add(new ListPage(1, 0, 0, R.drawable.next_yd, R.drawable.contents1830));
            return 0;
        }
        if(duration == 0){
            ytcheck.setVisibility(View.GONE);
            finished.setVisibility(View.GONE);
            continuetime.setVisibility(View.GONE);
            minafter.setVisibility(View.GONE);
            arrived.setVisibility(View.VISIBLE);
            return 0;
        }
        ytcheck.setText(min + "");
        return 0;
    }

    public int setTimecheckerSat(String time) throws ParseException {
        Time_cal time_cal = new Time_cal(time);

        int duration = time_cal.getTime();
        Log.i("arrived", duration + "");
        int hour = duration / 60;
        int min = duration % 60;

        if(time.equals("08:40:00")){
            ytcheck.setVisibility(View.GONE);
            finished.setVisibility(View.VISIBLE);
            continuetime.setVisibility(View.GONE);
            minafter.setVisibility(View.GONE);
            arrived.setVisibility(View.GONE);
            if(duration < 60) {
                listpage.add(new ListPage(0, min, hour, R.drawable.first_yd, R.drawable.text3));
            }
            return 1;
        }
        if(duration == 0){
            ytcheck.setVisibility(View.GONE);
            finished.setVisibility(View.GONE);
            continuetime.setVisibility(View.GONE);
            minafter.setVisibility(View.GONE);
            arrived.setVisibility(View.VISIBLE);
            return 0;
        }
        ytcheck.setText(min+"");
        return 0;
    }
    public void setTimecheckerSun() throws ParseException {
        ytcheck.setVisibility(View.GONE);
        finished.setVisibility(View.VISIBLE);
        continuetime.setVisibility(View.GONE);
        minafter.setVisibility(View.GONE);
        arrived.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
