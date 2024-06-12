package com.android.nurjan.service.moduls;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class SpinnerSetValues implements AdapterView.OnItemSelectedListener {
    private List<String> itemList;
    private Context context;
    private EditText editText;

    public SpinnerSetValues(List<String> itemList, Context context, EditText editText) {
        this.itemList = itemList;
        this.context = context;
        this.editText = editText;
    }

    public void show(Spinner spinner) {
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, itemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

//        String textToSelect = "Item 2";
//        int index = itemList.indexOf(textToSelect);
//
//        if (index != -1) {
//            spinner.setSelection(index);
//        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(itemList.get(position), TextView.BufferType.EDITABLE);
//        editText.setText("This sets the text.", TextView.BufferType.EDITABLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
