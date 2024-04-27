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

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<AdminValuesModel> content;

    public HistoryAdapter(Context context, List<AdminValuesModel> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    @NonNull
    @Override
    public HistoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.history_recycler_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapterViewHolder holder, int position) {
        holder.autor.setText(content.get(position).getAutor());
        holder.history.setText(content.get(position).getHistory());
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public void setFilterList(List<AdminValuesModel> adminValuesModels) {
        this.content = adminValuesModels;
        notifyDataSetChanged();
    }


    class HistoryAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView autor;
        private TextView history;

        public HistoryAdapterViewHolder(@NonNull View view) {
            super(view);

            autor = view.findViewById(R.id.textView7);
            history = view.findViewById(R.id.textView8);
        }
    }
}
