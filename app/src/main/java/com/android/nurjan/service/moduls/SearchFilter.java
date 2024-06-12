package com.android.nurjan.service.moduls;

import android.content.Context;
import android.widget.Toast;

import com.android.nurjan.entity.AdminValuesModel;
import com.android.nurjan.view.Adapter.HistoryAdapter;
import com.android.nurjan.view.Adapter.LikeAdapter;
import com.android.nurjan.view.Adapter.PlayListFragmentAdapter;
import com.android.nurjan.view.Adapter.RecomendationAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter {
    public static void filterTextRecomendationAdapter(String newText, List<AdminValuesModel> adminValuesModelsParam, Context context, RecomendationAdapter recomendationAdapter) {
        List<AdminValuesModel> adminValuesModels = new ArrayList<>();
        for (int i = 0; i < adminValuesModelsParam.size(); i++) {
            if (adminValuesModelsParam.get(i).getAutor().contains(newText)) {
                adminValuesModels.add(adminValuesModelsParam.get(i));
            }
        }

        if (adminValuesModels.isEmpty()) {
            Toast.makeText(context, "Ыр табылбады", Toast.LENGTH_SHORT).show();
        } else {
            recomendationAdapter.setFilterList(adminValuesModels);
        }
    }

    public static void filterTextPlayList(String newText, List<AdminValuesModel> adminValuesModelsParam, Context context, PlayListFragmentAdapter recomendationAdapter) {
        List<AdminValuesModel> adminValuesModels = new ArrayList<>();
        for (int i = 0; i < adminValuesModelsParam.size(); i++) {
            if (adminValuesModelsParam.get(i).getAutor().contains(newText)) {
                adminValuesModels.add(adminValuesModelsParam.get(i));
            }
        }

        if (adminValuesModels.isEmpty()) {
            Toast.makeText(context, "Ыр табылбады", Toast.LENGTH_SHORT).show();
        } else {
            recomendationAdapter.setFilterList(adminValuesModels);
        }
    }

    public static void filterTextLikeAdapter(String newText, List<AdminValuesModel> adminValuesModelsParam, Context context, LikeAdapter recomendationAdapter) {
        List<AdminValuesModel> adminValuesModels = new ArrayList<>();
        for (int i = 0; i < adminValuesModelsParam.size(); i++) {
            if (adminValuesModelsParam.get(i).getAutor().contains(newText)) {
                adminValuesModels.add(adminValuesModelsParam.get(i));
            }
        }

        if (adminValuesModels.isEmpty()) {
            Toast.makeText(context, "Ыр табылбады", Toast.LENGTH_SHORT).show();
        } else {
            recomendationAdapter.setFilterList(adminValuesModels);
        }
    }

    public static void filterTextHistoryAdapter(String newText, List<AdminValuesModel> adminValuesModelsParam, Context context, HistoryAdapter recomendationAdapter) {
        List<AdminValuesModel> adminValuesModels = new ArrayList<>();
        for (int i = 0; i < adminValuesModelsParam.size(); i++) {
            if (adminValuesModelsParam.get(i).getAutor().contains(newText)) {
                adminValuesModels.add(adminValuesModelsParam.get(i));
            }
        }

        if (adminValuesModels.isEmpty()) {
            Toast.makeText(context, "Акын табылбады", Toast.LENGTH_SHORT).show();
        } else {
            recomendationAdapter.setFilterList(adminValuesModels);
        }
    }
}
