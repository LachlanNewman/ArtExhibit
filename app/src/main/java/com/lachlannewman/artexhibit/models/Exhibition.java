package com.lachlannewman.artexhibit.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lachlan Newman on 4/10/2017.
 */

public class Exhibition implements Parcelable {

    private String exhibit_id;
    private String title;
    private String location;
    private String date;
    private String time;

    public Exhibition(String exhibit_id, String title, String location, String date, String time) {
        this.exhibit_id = exhibit_id;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getExhibit_id() {
        return exhibit_id;
    }

    public void setExhibit_id(String exhibit_id) {
        this.exhibit_id = exhibit_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected Exhibition(Parcel in) {
        exhibit_id = in.readString();
        title = in.readString();
        location = in.readString();
        date = in.readString();
        time = in.readString();
    }

    public static final Creator<Exhibition> CREATOR = new Creator<Exhibition>() {
        @Override
        public Exhibition createFromParcel(Parcel in) {
            return new Exhibition(in);
        }

        @Override
        public Exhibition[] newArray(int size) {
            return new Exhibition[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(exhibit_id);
        parcel.writeString(title);
        parcel.writeString(location);
        parcel.writeString(date);
        parcel.writeString(time);
    }
}
