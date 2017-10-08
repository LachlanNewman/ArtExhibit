package com.example.lachlannewman.api;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Lachlan Newman on 4/10/2017.
 */

@Entity
public class ExhibitApi {

    @Id
    Long Id;
    private String exhibition;
    private String date;
    private String time;
    private String location;
    private String administator;
    private String Description;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAdministator() {
        return administator;
    }

    public void setAdministator(String administator) {
        this.administator = administator;
    }

    public String getExibition() {
        return exhibition;
    }

    public void setExibition(String exibition) {
        this.exhibition = exibition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsafeKey() {
        return Key.create( ExhibitApi.class, Id).getString();
    }
}
