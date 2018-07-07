package dev.woody.ext.Bus.listpage;

/**
 * Created by kimsehyung on 2016-02-24.
 */
public class ListPage {
    private int Min = 0;
    private int Hour = 0;
    private int image1 = 0;
    private int image2 = 0;
    private int type = 0;

    public ListPage(int type, int min, int hour, int image1, int image2){
        this.Min = min;
        this.Hour = hour;
        this.image1 = image1;
        this.image2 = image2;
        this.type = type;
    }

    public int getMin(){
        return this.Min;
    }
    public int getHour(){
        return this.Hour;
    }
    public int getType(){
        return this.type;
    }

    public int getImage1(){
        return this.image1;
    }

    public  int getImage2(){
        return this.image2;
    }
}
