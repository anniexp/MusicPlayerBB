package com.example.musicplayerbb;


import android.net.Uri;
import android.widget.Button;

public class ModelAudio {

    String audioTitle;
    String audioDuration;
    String audioArtist;
    Uri audioUri;
    Button details;


    public String getaudioTitle() {
        return audioTitle;
    }

    public void setaudioTitle(String audioTitle) {
        this.audioTitle = audioTitle;
    }

    public String getaudioDuration() {
        return audioDuration;
    }

    public void setaudioDuration(String audioDuration) {
        this.audioDuration = audioDuration;
    }

    public String getaudioArtist() {
        return audioArtist;
    }

    public void setaudioArtist(String audioArtist) {
        this.audioArtist = audioArtist;
    }

    public Uri getaudioUri() {
        return audioUri;
    }

    public void setaudioUri(Uri audioUri) {
        this.audioUri = audioUri;
    }

    public void setDetails(Button details){this.details = details;}
}