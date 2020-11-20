package com.example.project_1;

import android.content.Context;

public class Utils {
    /* Return the image id by it's name from the drawable folder. */
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
