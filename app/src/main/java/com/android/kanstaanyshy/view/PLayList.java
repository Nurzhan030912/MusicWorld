package com.android.kanstaanyshy.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.view.Adapter.PlayListAdapter;
import com.android.kanstaanyshy.view.Adapter.RecomendationAdapter;

import java.util.ArrayList;
import java.util.List;

public class PLayList extends Fragment {
    private SearchView searchMusic;
    private RecyclerView recyclerView;
    private PlayListAdapter playListAdapter;
    private List<String> content = new ArrayList<>();

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

        adding();
        playListAdapter = new PlayListAdapter(getContext(), content, null);
        recyclerView = view.findViewById(R.id.recyclerP);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(playListAdapter);


        searchMusic.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                return false;
            }
        });
        return view;
    }

    private void adding() {
        for (int i = 0; i < 100; i++) {
            content.add("N" + i);
        }
    }
}