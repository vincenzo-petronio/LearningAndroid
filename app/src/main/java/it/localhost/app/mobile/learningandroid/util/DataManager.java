package it.localhost.app.mobile.learningandroid.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 *
 */
public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    /**
     * Restituisce una String da un JSON presente in assets.
     *
     * @param fileName String filename del JSON
     * @param context  Context
     * @return String contenente un JSON
     * @throws IOException IOException
     */
    public static String getJSONFromAssets(String fileName, Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(fileName);
        int size = inputStream.available();
        byte[] bytesBuffer = new byte[size];
        inputStream.read(bytesBuffer);
        inputStream.close();
        return new String(bytesBuffer, Charset.forName("UTF-8"));
    }
}
