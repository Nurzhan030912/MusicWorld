package com.android.nurjan.service.moduls;

import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nurjan.R;
import com.android.nurjan.entity.AdminValuesModel;

import java.io.IOException;
import java.util.List;

public class MusicPlayer {
    public static void playerMusic(MediaPlayer mediaPlayer, List<AdminValuesModel> adminValuesModel, ImageView playSongLyrics, int position, String task,
                                   TextView nameSong, TextView performer, TextView lyricsMusic) {
        if (task.equals("n")) {
            position++;
            if (position >= adminValuesModel.size()) position = adminValuesModel.size() - 1;

            nameSong.setText(adminValuesModel.get(position).getSong_name());
            performer.setText(adminValuesModel.get(position).getAutor());
            lyricsMusic.setText(adminValuesModel.get(position).getLyrics());
            try {
                playSongLyrics.setImageResource(R.drawable.baseline_pause_circle_24);
                mediaPlayer.reset();
                mediaPlayer.setDataSource(adminValuesModel.get(position).getMusic());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (task.equals("p")) {
            position--;
            if (position < 0) position = 0;

            nameSong.setText(adminValuesModel.get(position).getSong_name());
            performer.setText(adminValuesModel.get(position).getAutor());
            lyricsMusic.setText(adminValuesModel.get(position).getLyrics());
            try {
                playSongLyrics.setImageResource(R.drawable.baseline_pause_circle_24);
                mediaPlayer.reset();
                mediaPlayer.setDataSource(adminValuesModel.get(position).getMusic());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                playSongLyrics.setImageResource(R.drawable.baseline_play_arrow_24);
            } else {
                try {
                    playSongLyrics.setImageResource(R.drawable.baseline_pause_circle_24);
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(adminValuesModel.get(position).getMusic());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
