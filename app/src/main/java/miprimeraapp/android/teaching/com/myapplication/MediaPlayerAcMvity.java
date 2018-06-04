package miprimeraapp.android.teaching.com.myapplication;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MediaPlayerAcMvity extends BaseActivity {
    private MediaPlayer MyMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_ac_mvity);

    }
    public void onClickPlay (View view) {
        MyMediaPlayer = MediaPlayer.create(this, R.raw.cancion);
        MyMediaPlayer.start();
    }
    public void onClickPause (View view) {
        MyMediaPlayer.pause();
    }
    @Override
    protected void onStart () {
        super.onStart();
    }

    @Override
    protected void onPause () {
        super.onPause();

    }


    @Override
    protected void onStop () {

        super.onStop();
        MyMediaPlayer.release();
        MyMediaPlayer = null;


    }
    @Override
    protected void onDestroy () {
        super.onDestroy();
    }
}
