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
import com.android.nurjan.service.FirebaseServices;
import com.android.nurjan.service.OnItemRecyclerClickListener;
import com.android.nurjan.view.dialogPage.PlayListDialog;
import com.google.firebase.database.DataSnapshot;

import java.io.IOException;
import java.util.List;



public class RecomendationAdapter extends RecyclerView.Adapter<RecomendationAdapter.RecomendationAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<AdminValuesModel> content;
    private OnItemRecyclerClickListener mListener;
    private MediaPlayer mediaPlayer;
    private View view;

    public RecomendationAdapter(Context context, List<AdminValuesModel> content, Fragment fragment, MediaPlayer mediaPlayer, View view) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
        this.mediaPlayer = mediaPlayer;
        this.view = view;
    }

    public void setOnItemClickListener(OnItemRecyclerClickListener listener) {
        mListener = listener;
    }

    public void setFilterList (List<AdminValuesModel> adminValuesModels){
        this.content = adminValuesModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecomendationAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecomendationAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.recomendation_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendationAdapterViewHolder holder, int position) {
        holder.nameMusicR.setText(content.get(position).getSong_name());
        holder.songerR.setText(content.get(position).getAutor());

        if (content.get(position).getLiked()) {
            holder.likeMusicsR.setColorFilter(R.color.red);
        }

        holder.albumMusicR.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                holder.albumMusicR.setColorFilter(ContextCompat.getColor(context, R.color.green));
            } else {
                try {
                    holder.albumMusicR.setColorFilter(ContextCompat.getColor(context, R.color.red));
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(content.get(position).getMusic());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        holder.shareOneMusicR.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, content.get(position).getSong_name());
            shareIntent.putExtra(Intent.EXTRA_TEXT, content.get(position).getMusic());
            context.startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
        holder.menuMusicR.setOnClickListener(v -> {
            PlayListDialog recomendationAdapter = new PlayListDialog(context, content, position);
            recomendationAdapter.show();
        });
        holder.likeMusicsR.setOnClickListener(v -> {
            FirebaseServices firebaseServices = new FirebaseServices("Нац");
            if (content.get(position).getLiked()) {
                holder.likeMusicsR.setColorFilter(R.color.green);
                firebaseServices.updateLikes(content.get(position).getKey(), false, context);
            } else {
                holder.likeMusicsR.setColorFilter(R.color.red);
                firebaseServices.updateLikes(content.get(position).getKey(), true, context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class RecomendationAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView nameMusicR;
        private TextView songerR;
        private ImageView albumMusicR;

        private ImageView shareOneMusicR;
        private ImageView menuMusicR;
        private ImageView likeMusicsR;

        public RecomendationAdapterViewHolder(@NonNull View view) {
            super(view);
            nameMusicR = view.findViewById(R.id.nameMusicR);
            songerR = view.findViewById(R.id.songerR);
            albumMusicR = view.findViewById(R.id.albumMusicR);
            shareOneMusicR = view.findViewById(R.id.shareOneMusicR);
            menuMusicR = view.findViewById(R.id.menuMusicR);
            likeMusicsR = view.findViewById(R.id.likeMusicsR);

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
