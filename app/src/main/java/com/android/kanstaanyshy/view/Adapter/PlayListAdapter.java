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
import com.android.kanstaanyshy.entity.AdminValuesModel;
import com.android.kanstaanyshy.service.OnItemRecyclerClickListener;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListListViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<AdminValuesModel> content;

    private OnItemRecyclerClickListener mListener;

    public PlayListAdapter(Context context, List<AdminValuesModel> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    public void setOnItemClickListener(OnItemRecyclerClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public PlayListAdapter.PlayListListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayListAdapter.PlayListListViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_playlist_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListAdapter.PlayListListViewHolder holder, int position) {
        holder.textView6.setText(content.get(position).getPlayListName());
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class PlayListListViewHolder extends RecyclerView.ViewHolder {
        private TextView textView6;

        public PlayListListViewHolder(@NonNull View view) {
            super(view);
            textView6 = view.findViewById(R.id.textView6);

            itemView.setOnClickListener(view1 -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
