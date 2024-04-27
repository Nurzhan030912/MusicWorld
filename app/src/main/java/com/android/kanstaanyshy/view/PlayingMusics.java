package com.android.kanstaanyshy.view;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.service.FirebaseServices;
import com.android.kanstaanyshy.view.Adapter.LikeAdapter;
import com.android.kanstaanyshy.view.Adapter.LyricsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlayingMusics extends Fragment {

    private RecyclerView recyclerView;
    private TextView nameSong, performer, musicTime, lyricsMusic;
    private ImageView songsImage, menuSongLyrics, likeSongLyrics, previousSongLyrics, playSongLyrics, nextSongLyrics,
            replySongLyrics, listSongLyrics, shareSongLyrics;
    private FirebaseServices firebaseServices;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing_musics, container, false);
        nameSong = view.findViewById(R.id.nameSong);
        performer = view.findViewById(R.id.performer);
        musicTime = view.findViewById(R.id.musicTime);
        lyricsMusic = view.findViewById(R.id.lyricsMusic);

        songsImage = view.findViewById(R.id.songsImage);
        menuSongLyrics = view.findViewById(R.id.menuSongLyrics);
        likeSongLyrics = view.findViewById(R.id.likeSongLyrics);
        previousSongLyrics = view.findViewById(R.id.previousSongLyrics);
        playSongLyrics = view.findViewById(R.id.playSongLyrics);
        nextSongLyrics = view.findViewById(R.id.nextSongLyrics);
        replySongLyrics = view.findViewById(R.id.replySongLyrics);
        listSongLyrics = view.findViewById(R.id.listSongLyrics);
        shareSongLyrics = view.findViewById(R.id.shareSongLyrics);
        seekBar = view.findViewById(R.id.seekBar);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
        );


//        recyclerView = view.findViewById(R.id.recyclerLyrics);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(lyricsAdapter);

        Bundle args = getArguments();
        if (args != null) {
            String stringData = args.getString("key");
            if (stringData != null) {
                firebaseServices = new FirebaseServices("Нац");
                firebaseServices.findFromFirebase(stringData, nameSong, performer, musicTime, songsImage,
                        menuSongLyrics, likeSongLyrics, previousSongLyrics, playSongLyrics, nextSongLyrics,
                        replySongLyrics, listSongLyrics, shareSongLyrics, mediaPlayer, seekBar, lyricsMusic, getContext(),
                        getActivity().getSupportFragmentManager().beginTransaction())
                ;
            }
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}