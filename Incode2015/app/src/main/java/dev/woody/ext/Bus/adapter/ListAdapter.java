package dev.woody.ext.Bus.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dev.woody.ext.Bus.listpage.ListPage;
import dev.woody.ext.incode2015.R;

/**
 * Created by kimsehyung on 2016-02-24.
 */
public class ListAdapter extends BaseAdapter {
    private LayoutInflater inflater = null;
    private ArrayList<ListPage> listPages = null;
    private Context mContext = null;

    public ListAdapter(Context c, ArrayList<ListPage> arry){
        mContext = c;
        listPages = arry;
        this.inflater = (LayoutInflater.from(c));
    }

    @Override
    public int getCount() {
        return listPages.size();
    }

    @Override
    public Object getItem(int position) {
        return listPages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return listPages.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        int res = 0;
        if(convertView == null){
            viewHolder = new ViewHolder();
            res = listPages.get(position).getType();
            switch (res){
                case 0: res = R.layout.list_item; break;
                case 1: res = R.layout.list_item2; break;
            }
            convertView = inflater.inflate(res, parent, false);

            res = listPages.get(position).getType();
            switch (res){
                case 0:
                    viewHolder.tv_min = (TextView)convertView.findViewById(R.id.lv_min);
                    viewHolder.tv_hour = (TextView)convertView.findViewById(R.id.lv_hour);
                    viewHolder.iv_imageView1 = (ImageView)convertView.findViewById(R.id.lv_img1);
                    viewHolder.iv_imageView_min = (ImageView)convertView.findViewById(R.id.lv_title_min);
                    viewHolder.Iv_imageView_hour = (ImageView)convertView.findViewById(R.id.lv_title_hour);
                    break;
                case 1:
                    viewHolder.iv_imageView3 = (ImageView)convertView.findViewById(R.id.lv_image3);
                    viewHolder.iv_imageView4 = (ImageView)convertView.findViewById(R.id.lv_image4);
            }

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        res = listPages.get(position).getType();
        Log.i("res", res + "");
        switch (res){
            case 0:
                viewHolder.tv_min.setTypeface(Typeface.createFromAsset(convertView.getContext().getAssets(), "FUTURABI.otf"));
                viewHolder.tv_hour.setTypeface(Typeface.createFromAsset(convertView.getContext().getAssets(), "FUTURABI.otf"));
                viewHolder.tv_hour.setText(listPages.get(position).getHour() + "");
                viewHolder.tv_min.setText(listPages.get(position).getMin() + "");

                Glide.with(convertView.getContext()).load(R.drawable.hour2).into(viewHolder.Iv_imageView_hour);
                Glide.with(convertView.getContext()).load(listPages.get(position).getImage1()).into(viewHolder.iv_imageView1);
                Glide.with(convertView.getContext()).load(listPages.get(position).getImage2()).into(viewHolder.iv_imageView_min);

                if(listPages.get(position).getHour() == 0){
                    viewHolder.tv_hour.setVisibility(View.INVISIBLE);
                    viewHolder.Iv_imageView_hour.setVisibility(View.INVISIBLE);
                }
                else{
                    viewHolder.tv_hour.setVisibility(View.VISIBLE);
                    viewHolder.Iv_imageView_hour.setVisibility(View.VISIBLE);
                }
                break;
            case 1:

                Glide.with(convertView.getContext()).load(listPages.get(position).getImage1()).into(viewHolder.iv_imageView3);
                Glide.with(convertView.getContext()).load(listPages.get(position).getImage2()).into(viewHolder.iv_imageView4);
                break;
        }


        return convertView;
    }

    class ViewHolder{
        public TextView tv_min = null;
        public TextView tv_hour = null;
        public ImageView Iv_imageView_hour = null;
        public ImageView iv_imageView1 = null;
        public ImageView iv_imageView_min = null;
        public ImageView iv_imageView3 = null;
        public ImageView iv_imageView4 = null;
    }
}
