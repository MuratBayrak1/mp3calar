package com.example.mp3calar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.example.mp3calar.R.id.ibNext;
import static com.example.mp3calar.R.id.ibPause;
import static com.example.mp3calar.R.id.ibPlay;
import static com.example.mp3calar.R.id.ibrew;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ibPlay, ibPause, ibNext, ibList,ibrew;
    MediaPlayer mediaPlayer;
    ArrayList<String> musicList;
    TextView mTitle;
    int currentMusic = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibPlay = findViewById(R.id.ibPlay);
        ibPause = findViewById(R.id.ibPause);
        ibNext = findViewById(R.id.ibNext);
        ibrew = findViewById(R.id.ibrew);
        ibList = findViewById(R.id.ibMusicList);
        mTitle = findViewById(R.id.title);
        musicList = new ArrayList<>();

        ListRaw();

        ibPlay.setOnClickListener(this);
        ibPause.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        ibrew.setOnClickListener(this);
        ibList.setOnClickListener(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.mustafa);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibPlay: {
                mediaPlayer.start();
                mTitle.setText(musicList.get(0));
                break;
            }
            case R.id.ibNext: {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                if (currentMusic < musicList.size() - 1) {
                    currentMusic += 1;
                } else {
                    currentMusic = 0;
                }
                String musicUri = "android.resource://" + getPackageName() + "/raw/" + musicList.get(currentMusic);
                mediaPlayer = MediaPlayer.create(this, Uri.parse(musicUri));
                mediaPlayer.start();
                mTitle.setText(musicList.get(currentMusic));

                break;
            }
            case R.id.ibrew: {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                if (currentMusic < musicList.size() + 1) {
                    currentMusic -= 1;
                }
                if (currentMusic==1){
                    mediaPlayer.start();
                }
                else {
                    currentMusic = 0;
                }

                String musicUri = "android.resource://" + getPackageName() + "/raw/" + musicList.get(currentMusic);
                mediaPlayer = MediaPlayer.create(this, Uri.parse(musicUri));
                mediaPlayer.start();
                mTitle.setText(musicList.get(currentMusic));
                break;
            }
            case R.id.ibPause: {
                mediaPlayer.pause();
                break;
            }
            case R.id.ibMusicList: {
                // # TODO Music list feature will be available in the future.
                Log.d("butonKesfet", "onClick: MusicList ");
                break;
            }
            default: {
                break;
            }
        }

    }

    public void ListRaw() {
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            musicList.add(fields[i].getName());
        }
    }
}