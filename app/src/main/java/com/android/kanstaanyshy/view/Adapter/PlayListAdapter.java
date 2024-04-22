package com.android.kanstaanyshy.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kanstaanyshy.R;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListListViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<String> content;

    public PlayListAdapter(Context context, List<String> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    @NonNull
    @Override
    public PlayListListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayListListViewHolder(LayoutInflater.from(context).inflate(R.layout.play_list_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListListViewHolder holder, int position) {
        holder.nameMusicP.setText(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class PlayListListViewHolder extends RecyclerView.ViewHolder {
        private TextView nameMusicP;
        public PlayListListViewHolder(@NonNull View view) {
            super(view);
            nameMusicP = view.findViewById(R.id.nameMusicP);
        }
    }
}
