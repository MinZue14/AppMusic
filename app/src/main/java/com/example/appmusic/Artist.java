package com.example.appmusic;

public class Artist {
    String artistID, artistName;

    public Artist(String artistID, String artistName) {
        this.artistID = artistID;
        this.artistName = artistName;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Artist() {
    }
}
