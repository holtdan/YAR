package com.holtdan.yar;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {

    ImageButton btnPlay;
    SoundPool sndPool;
    int yardID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (ImageButton)findViewById(R.id.playButton);

        View.OnClickListener oclBtnPlay = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sndPool.play(yardID,1,1,1,0,1);
            }
        };

        // assign click listener to the OK button (btnOK)
        btnPlay.setOnClickListener(oclBtnPlay);

        sndPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        sndPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                sndPool.play(sampleId, 1, 1, 1, 0, 1);
            }
        });
        yardID = sndPool.load(this,R.raw.you_are_right,1);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        sndPool.release();
        sndPool = null;
    }
}
