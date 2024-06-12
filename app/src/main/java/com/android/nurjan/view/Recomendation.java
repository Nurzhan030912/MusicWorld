package com.android.nurjan.view;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nurjan.R;
import com.android.nurjan.service.FirebaseServices;

public class Recomendation extends Fragment {
    private SearchView searchMusic;
    private RecyclerView recyclerView;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomendation, container, false);
        searchMusic = view.findViewById(R.id.searchMusicR);
        searchMusic.clearFocus();

        recyclerView = view.findViewById(R.id.recyclerR);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
        );

        FirebaseServices firebaseServices = new FirebaseServices("Нац");
        firebaseServices.readFromFirebaseRecomendation(getContext(), recyclerView, requireActivity().getSupportFragmentManager(), mediaPlayer, searchMusic, view);

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