package com.example.lachlannewman.api;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

/**
 * Created by Lachlan Newman on 4/10/2017.
 */
@Entity
public class ArtworkApi {

    @Id
    Long Id;
    private String title;
    private String artist;
    private String artStyle;
    private String artPeriod;
    private int roomId;
    private boolean forSale;
    private double cost;
    private String imgUrl;

    public Key<ExhibitApi> getExhibitApiKey() {
        return mExhibitApiKey;
    }

    @Parent Key<ExhibitApi> mExhibitApiKey;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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

}
