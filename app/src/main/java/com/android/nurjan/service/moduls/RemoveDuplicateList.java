package com.android.nurjan.service.moduls;

import com.android.nurjan.entity.AdminValuesModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveDuplicateList {
    public static List<AdminValuesModel> removeDuplicates(List<AdminValuesModel> list) {
        Map<String, AdminValuesModel> map = new HashMap<>();

        for (AdminValuesModel admin : list) {
            map.put(admin.getPlayListName(), admin);
        }

        return new ArrayList<>(map.values());
    }
}
