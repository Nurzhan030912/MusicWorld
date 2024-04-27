package com.android.kanstaanyshy.view.dialogPage;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.kanstaanyshy.R;
import com.android.kanstaanyshy.entity.AdminValuesModel;
import com.android.kanstaanyshy.service.FirebaseServices;

import java.util.List;


public class PlayListDialog extends AlertDialog {
    private Spinner spinner;
    private EditText editText;
    private Button button, button2;
    private FirebaseServices firebaseServices;
    private List<AdminValuesModel> key;
    private int position;
    private InputMethodManager imm;

    public PlayListDialog(Context context, List<AdminValuesModel> key, int position) {
        super(context);
        this.key = key;
        this.position = position;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View contentView = inflater.inflate(R.layout.dialog_page_playlist, null);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.TOP;

        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        spinner = contentView.findViewById(R.id.spinner1);
        editText = contentView.findViewById(R.id.editTextText);
        button = contentView.findViewById(R.id.button);
        button2 = contentView.findViewById(R.id.button2);

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        spinnerShows(editText);

        button.setOnClickListener(v -> {
            if (!editText.getText().toString().isEmpty()) {
                firebaseServices = new FirebaseServices("Нац");
                firebaseServices.updatePlayList(key.get(position).getKey(), editText.getText().toString(), getContext());
            }
            dismiss();
        });


        button2.setOnClickListener(v ->

        {
            firebaseServices = new FirebaseServices("Нац");
            firebaseServices.updatePlayList(key.get(position).getKey(), "", getContext());
            dismiss();
        });


        setContentView(contentView);

    }

    private void spinnerShows( EditText editText) {
        firebaseServices = new FirebaseServices("Нац");
        firebaseServices.readFirebaseSpinner(spinner, getContext(), editText);
    }
}

