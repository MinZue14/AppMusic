package com.example.appmusic;

public class Tracks {
    String TrackID, TrackName, TrackGenre, TrackArtist, TrackView, TrackReleaseDate, TrackCountry, TrackImg;
    public Tracks(String trackID, String trackName, String trackGenre, String trackArtist, String trackView, String trackImg, String trackReleaseDate, String trackCountry) {
        TrackID = trackID;
        TrackName = trackName;
        TrackGenre = trackGenre;
        TrackArtist = trackArtist;
        TrackView = trackView;
        TrackImg = trackImg;
        TrackReleaseDate = trackReleaseDate;
        TrackCountry = trackCountry;
    }


    public String getTrackID() {
        return TrackID;
    }

    public void setTrackID(String trackID) {
        TrackID = trackID;
    }

    public String getTrackName() {
        return TrackName;
    }

    public void setTrackName(String trackName) {
        TrackName = trackName;
    }

    public String getTrackGenre() {
        return TrackGenre;
    }

    public void setTrackGenre(String trackGenre) {
        TrackGenre = trackGenre;
    }

    public String getTrackArtist() {
        return TrackArtist;
    }

    public void setTrackArtist(String trackArtist) {
        TrackArtist = trackArtist;
    }

    public String getTrackView() {
        return TrackView;
    }

    public void setTrackView(String trackView) {
        TrackView = trackView;
    }

    public String getTrackReleaseDate() {
        return TrackReleaseDate;
    }

    public void setTrackReleaseDate(String trackReleaseDate) {
        TrackReleaseDate = trackReleaseDate;
    }

    public String getTrackCountry() {
        return TrackCountry;
    }

    public void setTrackCountry(String trackCountry) {
        TrackCountry = trackCountry;
    }

    public String getTrackImg() {
        return TrackImg;
    }

    public void setTrackImg(String trackImg) {
        TrackImg = trackImg;
    }
    public Tracks() {
    }

}
