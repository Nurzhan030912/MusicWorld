package com.android.kanstaanyshy.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kanstaanyshy.MainActivity;
import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.service.FirebaseServices;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {
    private TextView login_btn;
    private TextInputEditText email_txt;
    private TextInputEditText password_txt;
    private FirebaseServices firebaseServices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login_btn = view.findViewById(R.id.login_btn);
        email_txt = view.findViewById(R.id.email_txt);
        password_txt = view.findViewById(R.id.password_txt);

        login_btn.setOnClickListener(v -> {
            if (!email_txt.getText().toString().isEmpty() && !password_txt.getText().toString().isEmpty()) {
                firebaseServices = new FirebaseServices("Auth");
                firebaseServices.readFromFirebaseAuth(getActivity(), getContext(), email_txt.getText().toString(), password_txt.getText().toString());
            } else {
                Toast.makeText(getContext(), "Бош талаа калбашы керек", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}