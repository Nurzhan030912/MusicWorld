package com.android.kanstaanyshy.view.dialogPage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class ConfirmDialogPage {
    private Context context;

    public ConfirmDialogPage(Context context) {
        this.context = context;
    }

    public void showConfirmDialogPage(String title, String massage, String task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(massage);
        builder.setPositiveButton("Макул", (dialog, which) -> {
            Toast.makeText(context, "Ишке ашты", Toast.LENGTH_SHORT).show();
        });
//        builder.setNegativeButton("Каршымын", null);// Button to close dialog
        builder.show();
    }
}
