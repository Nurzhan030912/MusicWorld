package com.android.kanstaanyshy.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.entity.AdminValuesModel;
import com.android.kanstaanyshy.service.FirebaseServices;
import com.android.kanstaanyshy.service.OnItemRecyclerClickListener;
import com.android.kanstaanyshy.view.dialogPage.PlayListDialog;

import java.io.IOException;
import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<AdminValuesModel> content;
    private OnItemRecyclerClickListener mListener;
    private MediaPlayer mediaPlayer;

    public LikeAdapter(Context context, List<AdminValuesModel> content, Fragment fragmentб,MediaPlayer mediaPlayer) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
        this.mediaPlayer = mediaPlayer;
    }

    public void setOnItemClickListener(OnItemRecyclerClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public LikeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LikeAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.like_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LikeAdapterViewHolder holder, int position) {
        holder.nameMusicL.setText(content.get(position).getSong_name());
        holder.songerL.setText(content.get(position).getAutor());

        if (content.get(position).getLiked()) {
            holder.likeMusicsL.setColorFilter(R.color.red);
        }


        holder.albumMusicL.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                holder.albumMusicL.setColorFilter(ContextCompat.getColor(context, R.color.green));
            } else {
                try {
                    holder.albumMusicL.setColorFilter(ContextCompat.getColor(context, R.color.red));
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(content.get(position).getMusic());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.likeMusicsL.setOnClickListener(v -> {
            FirebaseServices firebaseServices = new FirebaseServices("Нац");
            if (content.get(position).getLiked()) {
                holder.likeMusicsL.setColorFilter(R.color.green);
                firebaseServices.updateLikes(content.get(position).getKey(), false, context);
            } else {
                holder.likeMusicsL.setColorFilter(R.color.red);
                firebaseServices.updateLikes(content.get(position).getKey(), true, context);
            }
        });

        holder.shareOneMusicL.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, content.get(position).getSong_name());
            shareIntent.putExtra(Intent.EXTRA_TEXT, content.get(position).getMusic());
            context.startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        holder.menuMusicL.setOnClickListener(v->{
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


    class LikeAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView nameMusicL;
        private TextView songerL;
        private ImageView albumMusicL;

        private ImageView shareOneMusicL;
        private ImageView menuMusicL;
        private ImageView likeMusicsL;

        public LikeAdapterViewHolder(@NonNull View view) {
            super(view);
            nameMusicL = view.findViewById(R.id.nameMusicL);
            songerL = view.findViewById(R.id.songerL);
            albumMusicL = view.findViewById(R.id.albumMusicL);

            shareOneMusicL = view.findViewById(R.id.shareOneMusicL);
            menuMusicL = view.findViewById(R.id.menuMusicL);
            likeMusicsL = view.findViewById(R.id.likeMusicsL);

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
