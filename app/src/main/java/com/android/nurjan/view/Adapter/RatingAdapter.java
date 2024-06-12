package com.android.nurjan.view.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nurjan.R;
import com.android.nurjan.entity.RatingModel;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<RatingModel> content;

    public RatingAdapter(Context context, List<RatingModel> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    @NonNull
    @Override
    public RatingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatingAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.rating_list, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RatingAdapterViewHolder holder, int position) {
        holder.fio.setText(content.get(position).getName());
        holder.ball.setText(content.get(position).getPuan());
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class RatingAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView fio;
        private TextView ball;

        public RatingAdapterViewHolder(@NonNull View view) {
            super(view);

            fio = view.findViewById(R.id.textView12);
            ball = view.findViewById(R.id.textView13);
        }
    }
}
