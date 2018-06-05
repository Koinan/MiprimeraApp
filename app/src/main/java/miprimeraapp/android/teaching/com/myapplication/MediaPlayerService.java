package miprimeraapp.android.teaching.com.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MediaPlayerService extends Service {
    private MediaPlayer MyMediaPlayer;

    public MediaPlayerService() {
    }

    @Override
    public void onDestroy () {
        MyMediaPlayer.release();
        MyMediaPlayer = null;
        super.onDestroy();
    }
    @Override
    public void onCreate () {

        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (MyMediaPlayer != null) {


        } else {
            MyMediaPlayer = MediaPlayer.create(this, R.raw.explosion);
            MyMediaPlayer.start();
            MyMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSelf();
                    Log.d("servicioparado" , "El servicio se ha detenido");
                }
            });
        }

        return super.onStartCommand(intent, flags, startId);


    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
