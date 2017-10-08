package com.lachlannewman.artexhibit.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lachlan Newman on 4/10/2017.
 */

public class Artwork implements Parcelable{

    private String title;
    private String artist;
    private String artStyle;
    private String artPeriod;
    private int roomId;
    private boolean forSale;
    private double cost;
    private String imgUrl;

    public Artwork(String title, String artist, String artStyle, String artPeriod, int roomId, boolean forSale, double cost, String imgUrl) {
        this.title = title;
        this.artist = artist;
        this.artStyle = artStyle;
        this.artPeriod = artPeriod;
        this.roomId = roomId;
        this.forSale = forSale;
        this.cost = cost;
        this.imgUrl = imgUrl;
    }

    protected Artwork(Parcel in) {
        title = in.readString();
        artist = in.readString();
        artStyle = in.readString();
        artPeriod = in.readString();
        roomId = Integer.parseInt(in.readString());
        forSale = Boolean.parseBoolean(in.readString());
        cost = Double.parseDouble(in.readString());
        imgUrl = in.readString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtStyle() {
        return artStyle;
    }

    public void setArtStyle(String artStyle) {
        this.artStyle = artStyle;
    }

    public String getArtPeriod() {
        return artPeriod;
    }

    public void setArtPeriod(String artPeriod) {
        this.artPeriod = artPeriod;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(artist);
        parcel.writeString(artStyle);
        parcel.writeString(artPeriod);
        parcel.writeString("" + roomId);
        parcel.writeString("" + forSale);
        parcel.writeString("" + cost);
        parcel.writeString(imgUrl);

    }

    public static final Creator<Artwork> CREATOR = new Creator<Artwork>() {
        @Override
        public Artwork createFromParcel(Parcel in) {
            return new Artwork(in);
        }

        @Override
        public Artwork[] newArray(int size) {
            return new Artwork[size];
        }
    };

}
