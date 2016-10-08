package it.localhost.app.mobile.learningandroid.util;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 * Volley Singleton
 */
public class VolleySingleton {

    private static final String TAG = VolleySingleton.class.getSimpleName();
    private static VolleySingleton ourInstance;
    private static Context context;
    private RequestQueue requestQueue;

    /**
     * @param ctx Context
     */
    private VolleySingleton(Context ctx) {
        context = ctx;
    }

    public static synchronized VolleySingleton getInstance(Context ctx) {
        if (ourInstance == null) {
            ourInstance = new VolleySingleton(ctx);
        }
        return ourInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        Log.i(TAG, "URL: " + req.getUrl());
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
