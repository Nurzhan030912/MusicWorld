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

public class RecomendationAdapter extends RecyclerView.Adapter<RecomendationAdapter.RecomendationAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<String> content;

    public RecomendationAdapter(Context context, List<String> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    @NonNull
    @Override
    public RecomendationAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecomendationAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.recomendation_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendationAdapterViewHolder holder, int position) {
        holder.nameMusicR.setText(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class RecomendationAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView nameMusicR;
        public RecomendationAdapterViewHolder(@NonNull View view) {
            super(view);
            nameMusicR = view.findViewById(R.id.nameMusicR);
        }
    }
}
