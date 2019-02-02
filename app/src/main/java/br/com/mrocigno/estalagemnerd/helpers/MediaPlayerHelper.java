package br.com.mrocigno.estalagemnerd.helpers;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaPlayerHelper {

    public static MediaPlayer prepareSong(String url){
        MediaPlayer mp = new MediaPlayer();
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC).build();
        mp.setAudioAttributes(attrs);

        try {
            mp.setDataSource(url);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mp;
    }

}
