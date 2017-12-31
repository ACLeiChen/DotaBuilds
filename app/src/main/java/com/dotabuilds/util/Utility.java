package com.dotabuilds.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lei Chen on 2017/11/20.
 */

public class Utility {

    public static String readJSONFromResources(String fileName){
        String json = null;
        try {
            InputStream is = Utility.class.getClassLoader().getResourceAsStream(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String readJsonToStringFromFilesDir(String fileName, Context context){
        String json = null;
        try {
            InputStream is = new FileInputStream(context.getFileStreamPath(fileName));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static int getDrawableIdByName(Context context, String drawableName){
        int drawableId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());

        return drawableId;
    }

}
