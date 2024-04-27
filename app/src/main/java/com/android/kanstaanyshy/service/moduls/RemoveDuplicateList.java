package com.android.kanstaanyshy.service.moduls;

import com.android.kanstaanyshy.entity.AdminValuesModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicateList {
    public static List<AdminValuesModel> removeDuplicates(List<AdminValuesModel> list) {
        Map<String, AdminValuesModel> map = new HashMap<>();

        for (AdminValuesModel admin : list) {
            map.put(admin.getPlayListName(), admin);
        }

        return new ArrayList<>(map.values());
    }
}
