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

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<String> content;

    public LikeAdapter(Context context, List<String> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    @NonNull
    @Override
    public LikeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LikeAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.like_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LikeAdapterViewHolder holder, int position) {
        holder.nameMusicL.setText(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class LikeAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView nameMusicL;

        public LikeAdapterViewHolder(@NonNull View view) {
            super(view);
            nameMusicL = view.findViewById(R.id.nameMusicL);
        }
    }
}
