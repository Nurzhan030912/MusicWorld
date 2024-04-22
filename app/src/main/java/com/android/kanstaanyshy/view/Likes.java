package com.android.kanstaanyshy.view;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.view.Adapter.LikeAdapter;
import com.android.kanstaanyshy.view.Adapter.PlayListAdapter;
import com.android.kanstaanyshy.view.Adapter.RecomendationAdapter;

import java.util.ArrayList;
import java.util.List;

public class Likes extends Fragment {
    private SearchView searchMusic;
    private RecyclerView recyclerView;
    private LikeAdapter likeAdapter;
    private List<String> content = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_likes, container, false);
        searchMusic = view.findViewById(R.id.searchMusicL);
        adding();
        likeAdapter = new LikeAdapter(getContext(), content, null);
        recyclerView = view.findViewById(R.id.recyclerL);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(likeAdapter);

        searchMusic.clearFocus();
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
            content.add("G" + i);
        }
    }
}