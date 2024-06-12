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

public class PLayListFragment extends Fragment {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_lay_list, container, false);

        searchMusic = view.findViewById(R.id.searchMusicP);
        searchMusic.clearFocus();
        recyclerView = view.findViewById(R.id.recyclerP);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
        );

        Bundle args = getArguments();
        if (args != null) {
            String stringData = args.getString("key");
            if (stringData != null) {
                FirebaseServices firebaseServices = new FirebaseServices("Нац");
                firebaseServices.readFromFirebasePlaylist(getContext(), recyclerView, requireActivity().getSupportFragmentManager(), mediaPlayer, stringData, searchMusic, view);
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