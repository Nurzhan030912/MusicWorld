package com.android.nurjan.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nurjan.R;
import com.android.nurjan.service.FirebaseServices;

public class RatingFragment extends Fragment {

    private RecyclerView recyclerView_rating;
    private FirebaseServices firebaseServices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        recyclerView_rating = view.findViewById(R.id.recyclerViewRating);

        FirebaseServices rating = new FirebaseServices("Auth");
        rating.ratingFirebase(getContext(), recyclerView_rating);
        return view;
    }
}