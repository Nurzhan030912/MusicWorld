package com.android.nurjan.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nurjan.R;
import com.android.nurjan.entity.AdminValuesModel;
import com.android.nurjan.service.OnItemRecyclerClickListener;
import com.android.nurjan.view.dialogPage.PlayListDialog;

import java.io.IOException;
import java.util.List;

public class PlayListFragmentAdapter extends RecyclerView.Adapter<PlayListFragmentAdapter.PlayListListViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<AdminValuesModel> content;

    private OnItemRecyclerClickListener mListener;
    private MediaPlayer mediaPlayer;
    private View view;

    public PlayListFragmentAdapter(Context context, List<AdminValuesModel> content, Fragment fragment, MediaPlayer mediaPlayer, View view) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
        this.mediaPlayer = mediaPlayer;
        this.view = view;
    }

    public void setOnItemClickListener(OnItemRecyclerClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public PlayListListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayListListViewHolder(LayoutInflater.from(context).inflate(R.layout.play_list_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListListViewHolder holder, int position) {
        holder.nameMusicP.setText(content.get(position).getSong_name());
        holder.songerP.setText(content.get(position).getAutor());


        holder.albumMusicP.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                holder.albumMusicP.setColorFilter(ContextCompat.getColor(context, R.color.green));
            } else {
                try {
                    holder.albumMusicP.setColorFilter(ContextCompat.getColor(context, R.color.red));
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(content.get(position).getMusic());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.shareOneMusicP.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, content.get(position).getSong_name());
            shareIntent.putExtra(Intent.EXTRA_TEXT, content.get(position).getMusic());
            context.startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        holder.menuMusicP.setOnClickListener(v -> {
            PlayListDialog recomendationAdapter = new PlayListDialog(context, content, position);
            recomendationAdapter.show();
        });

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public void setFilterList(List<AdminValuesModel> adminValuesModels) {
        this.content = adminValuesModels;
        notifyDataSetChanged();
    }


    class PlayListListViewHolder extends RecyclerView.ViewHolder {
        private TextView nameMusicP;
        private TextView songerP;
        private ImageView albumMusicP;

        private ImageView shareOneMusicP;
        private ImageView menuMusicP;

        public PlayListListViewHolder(@NonNull View view) {
            super(view);
            nameMusicP = view.findViewById(R.id.nameMusicP);
            songerP = view.findViewById(R.id.songerP);
            albumMusicP = view.findViewById(R.id.albumMusicP);

            shareOneMusicP = view.findViewById(R.id.shareOneMusicP);
            menuMusicP = view.findViewById(R.id.menuMusicsP);

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
