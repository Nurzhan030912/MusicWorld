package com.android.nurjan.view.dialogPage;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.nurjan.R;
import com.android.nurjan.entity.AdminValuesModel;
import com.android.nurjan.service.FirebaseServices;

import java.util.List;


public class PlayListDialog {
    private Spinner spinner;
    private EditText editText;
    private Button button, button2;
    private FirebaseServices firebaseServices;
    private List<AdminValuesModel> key;
    private int position;
    private InputMethodManager imm;
    private Context context;

    public PlayListDialog(Context context, List<AdminValuesModel> key, int position) {
        this.context = context;
        this.key = key;
        this.position = position;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Ырлар");

        LayoutInflater inflater = LayoutInflater.from(context);
        View customLayout = inflater.inflate(R.layout.dialog_page_playlist, null);
        builder.setView(customLayout);

        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        spinner = customLayout.findViewById(R.id.spinner1);
        editText = customLayout.findViewById(R.id.editTextText);
        button = customLayout.findViewById(R.id.button);
        button2 = customLayout.findViewById(R.id.button2);

        AlertDialog dialog = builder.create();

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        });
        spinnerShows(editText);

        button.setOnClickListener(v -> {
            if (!editText.getText().toString().isEmpty()) {
                firebaseServices = new FirebaseServices("Нац");
                firebaseServices.updatePlayList(key.get(position).getKey(), editText.getText().toString(), context);
                dialog.dismiss();
            }

        });


        button2.setOnClickListener(v ->
        {
            firebaseServices = new FirebaseServices("Нац");
            firebaseServices.updatePlayList(key.get(position).getKey(), "", context);
            dialog.dismiss();
        });

        dialog.show();
    }


    private void spinnerShows( EditText editText) {
        firebaseServices = new FirebaseServices("Нац");
        firebaseServices.readFirebaseSpinner(spinner, context, editText);
    }
}

