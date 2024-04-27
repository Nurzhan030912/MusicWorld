package com.android.kanstaanyshy.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.service.FirebaseServices;

public class Playlist extends Fragment {
   private RecyclerView recyclerView;
   private FirebaseServices firebaseServices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        recyclerView = view.findViewById(R.id.playlist_recycler);
        firebaseServices = new FirebaseServices("Нац");
        firebaseServices.readFromFirebasePlaylistFragment(getContext(),recyclerView, requireActivity().getSupportFragmentManager());
        return view;
    }
}