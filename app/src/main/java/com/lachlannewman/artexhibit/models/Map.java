package com.lachlannewman.artexhibit.models;

/**
 * Created by Lachlan Newman on 17/10/2017.
 */

public class Map {

    private int[][] MyLocation;
    private int[][] ArtLocation;

    private int[][] getMyLocation(){
        MyLocation = new int[1][1];
        //functinality required to get curent location
        return MyLocation;
    }

    private int[][] getArtLocation(int roomId){
        ArtLocation = new int[1][1];
        return ArtLocation;
    }

    public void getDirections(){
        //retunr directions
    }
}
