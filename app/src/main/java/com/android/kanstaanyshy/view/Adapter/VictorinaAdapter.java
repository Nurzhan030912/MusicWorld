package com.android.kanstaanyshy.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.entity.AdminValuesModel;

import java.util.List;

public class VictorinaAdapter extends RecyclerView.Adapter<VictorinaAdapter.VictorinaAdapterViewHolder> {
    private Context context;
    private Fragment fragment;
    private List<AdminValuesModel> content;

    public VictorinaAdapter(Context context, List<AdminValuesModel> content, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        this.content = content;
    }

    @NonNull
    @Override
    public VictorinaAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VictorinaAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.victorina_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VictorinaAdapterViewHolder holder, int position) {
        holder.test.setText("Бул?");
        holder.a.setText("a");
        holder.b.setText("b");
        holder.c.setText("c");
        holder.d.setText("d");
    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    class VictorinaAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView test;
        private Button a;
        private Button b;
        private Button c;
        private Button d;

        public VictorinaAdapterViewHolder(@NonNull View view) {
            super(view);
            test = view.findViewById(R.id.textView11);

            a = view.findViewById(R.id.button3);
            b = view.findViewById(R.id.button4);
            c = view.findViewById(R.id.button5);
            d = view.findViewById(R.id.button6);
        }
    }
}
