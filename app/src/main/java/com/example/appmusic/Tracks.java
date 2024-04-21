package com.example.appmusic;

public class Tracks {
    String TrackID, TrackName, TrackGenre, TrackArtist, TrackView, TrackReleaseDate;

    public Tracks(String trackID, String trackName, String trackGenre, String trackArtist, String trackView, String trackReleaseDate) {
        this.TrackID = trackID;
        this.TrackName = trackName;
        this.TrackGenre = trackGenre;
        this.TrackArtist = trackArtist;
        this.TrackView = trackView;
        this.TrackReleaseDate = trackReleaseDate;
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

    public Tracks() {
    }

}
