package com.example.appmusic;

public class Album {
    String AlbumID, AlbumName, AlbumArtist, AlbumView, AlbumYear;

    public Album(String albumID, String albumName, String albumArtist, String albumView, String albumYear) {
        AlbumID = albumID;
        AlbumName = albumName;
        AlbumArtist = albumArtist;
        AlbumView = albumView;
        AlbumYear = albumYear;
    }

    public String getAlbumID() {
        return AlbumID;
    }

    public void setAlbumID(String albumID) {
        AlbumID = albumID;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public String getAlbumArtist() {
        return AlbumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        AlbumArtist = albumArtist;
    }

    public String getAlbumView() {
        return AlbumView;
    }

    public void setAlbumView(String albumView) {
        AlbumView = albumView;
    }

    public String getAlbumYear() {
        return AlbumYear;
    }

    public void setAlbumYear(String albumYear) {
        AlbumYear = albumYear;
    }

    public Album() {
    }
}
