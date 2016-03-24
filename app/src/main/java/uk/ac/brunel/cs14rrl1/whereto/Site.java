package uk.ac.brunel.cs14rrl1.whereto;

import android.graphics.Bitmap;

/**
 * Created by cs14rrl1 on 10/03/2016.
 */
public class Site {

    int id = 0;
    String name = null;
    Double lat = null;
    Double lng = null;
    int catId = 0;
    Bitmap image = null;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(Double latitude) {
        this.lat = latitude;
    }
    public Double getLong() {
        return lng;
    }
    public void setLong(Double longitude) {
        this.lng = longitude;
    }
    public int getCatId() {
        return catId;
    }
    public void setCatId(int category) {
        this.catId = category;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap imagestring) {
        this.image = imagestring;
    }
}
