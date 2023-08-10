package com.example.testapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import androidx.fragment.app.FragmentActivity;
public class MainActivity extends FragmentActivity {
    private boolean isVideoPlaying = false;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        videoView= findViewById(R.id.videoView);
        Uri uri = Uri.parse("https://students.mimuw.edu.pl/~gc401929/.topsale/vid.mp4");
        videoView.setVideoURI(uri);
        isVideoPlaying = true;

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

        videoView.start();

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (isVideoPlaying) {
            videoView.pause();
            isVideoPlaying = false;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!isVideoPlaying) {
            videoView.start();
            isVideoPlaying = true;
        }
    }
}