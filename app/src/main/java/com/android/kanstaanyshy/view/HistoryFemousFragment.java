package com.android.kanstaanyshy.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.service.FirebaseServices;

public class HistoryFemousFragment extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseServices firebaseServices;
    private SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_femous, container, false);
        searchView = view.findViewById(R.id.searchMusicH);
        recyclerView = view.findViewById(R.id.historyRecycler);
        firebaseServices = new FirebaseServices("Нац");
        firebaseServices.readHistory(getContext(), recyclerView, searchView);
        return view;
    }
}