package com.android.nurjan.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nurjan.R;

import java.util.List;

public class LyricsAdapter extends RecyclerView.Adapter<LyricsAdapter.LyricsAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<String> content;

    public LyricsAdapter(Context context, List<String> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    @NonNull
    @Override
    public LyricsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LyricsAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.lyrics_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LyricsAdapterViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class LyricsAdapterViewHolder extends RecyclerView.ViewHolder {


        public LyricsAdapterViewHolder(@NonNull View view) {
            super(view);
        }
    }
}
