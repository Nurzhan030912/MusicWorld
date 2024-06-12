package com.android.nurjan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nurjan.R;
import com.android.nurjan.service.FirebaseServices;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment {
    private TextInputEditText name_txt;
    private TextInputEditText email_txt;
    private TextInputEditText phone_txt;
    private TextInputEditText password_txt;
    private TextView register_btn;
    private FirebaseServices firebaseServices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        name_txt = view.findViewById(R.id.name_txt);
        email_txt = view.findViewById(R.id.email_txt);
        phone_txt = view.findViewById(R.id.phone_txt);
        password_txt = view.findViewById(R.id.password_txt);
        register_btn = view.findViewById(R.id.register_btn);

        register_btn.setOnClickListener(v -> {
            if (!name_txt.getText().toString().isEmpty() && !email_txt.getText().toString().isEmpty()
                    && !phone_txt.getText().toString().isEmpty()
                    && !password_txt.getText().toString().isEmpty()) {
                firebaseServices = new FirebaseServices("Auth");
                firebaseServices.saveAuth(getContext(), name_txt.getText().toString(), email_txt.getText().toString(), phone_txt.getText().toString(),
                        password_txt.getText().toString());
            } else {
                Toast.makeText(getContext(), "Бош талаа калбашы керек", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}