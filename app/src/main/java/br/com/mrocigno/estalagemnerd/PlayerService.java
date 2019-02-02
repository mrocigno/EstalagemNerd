package br.com.mrocigno.estalagemnerd;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.media.TimedMetaData;
import android.media.TimedText;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import br.com.mrocigno.estalagemnerd.helpers.MediaPlayerHelper;

public class PlayerService extends Service {

    private final IBinder playerBinder = new PlayerBinder();
    PlayerCallbacks callback;
    MediaPlayer mp;
    boolean paused = false;
    String title;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return playerBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        mp.stop();
        mp.release();
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = new MediaPlayer();
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC).build();
        mp.setAudioAttributes(attrs);
    }

    public void setCallbacks(final PlayerCallbacks callback){
        this.callback = callback;
        mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                callback.playerBuffer(percent);
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                callback.playerComplete();
            }
        });

        mp.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                callback.playerSeek();
            }
        });

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                callback.playerStart(title);
                timer();
            }
        });

        if(mp.isPlaying() || paused){
            callback.playerPrepare();
            callback.playerStart(title);
            callback.playerCurrent(mp.getCurrentPosition(), mp.getDuration());
            if(!paused) timer();
        }
    }

    private void timer(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    if(mp.isPlaying()) {
                        callback.playerCurrent(mp.getCurrentPosition(), mp.getDuration());
                        timer();
                    }
                }catch (Exception ignore){}
            }
        }, 1000);
    }

    public void prepareSong(final String url, String title){
        this.title = title;
        callback.playerPrepare();
        mp.reset();
            new Thread(){
                @Override
                public void run() {
                    try {
                        mp.setDataSource(url);
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
    }

    public void playerSeek(int mseg){
        mp.seekTo(mseg);
    }

    public void playSong(){
        paused = false;
        mp.start();
        timer();
        callback.playerPlay();
    }

    public void pauseSong(){
        paused = true;
        mp.pause();
        callback.playerPause();
    }

    public void stopSong(){
        paused = true;
        mp.stop();
        callback.playerStop();
    }

    public class PlayerBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    public interface PlayerCallbacks extends Serializable {
        void playerPrepare();
        void playerCurrent(int current, int duration);
        void playerBuffer(int percent);
        void playerComplete();
        void playerSeek();
        void playerStart(String title);
        void playerPause();
        void playerPlay();
        void playerStop();
    }

}
