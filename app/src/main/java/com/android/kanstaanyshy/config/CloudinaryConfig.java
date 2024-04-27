package com.android.kanstaanyshy.config;

import android.content.Context;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.utils.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfig {
    private final Cloudinary cloudinary;
    private final Map params = ObjectUtils.asMap(
            "folder", "NurjanKansTaanysh",
            "resource_type", "auto"
    );

    public CloudinaryConfig(Context context) {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpkxkjwmy",
                "api_key", "163832694684664",
                "api_secret", "YDYzDLzJaQIa4mghVhvX96tLXZk"));
    }
}
