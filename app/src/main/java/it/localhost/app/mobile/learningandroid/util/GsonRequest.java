package it.localhost.app.mobile.learningandroid.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Custom GSON Request per gestire nuovi type in aggiunta a quelli predefiniti di Volley.
 */
public class GsonRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Response.Listener<T> successListener;
    private Map<String, String> params, header;

    /**
     * @param method          int GET/POST.
     * @param url             String URL da chiamare.
     * @param clazz           Class Oggetto su cui effettuare la reflection del JSON.
     * @param params          Map<String, String> Map con i parametri da passare in query string.
     * @param header          Map<String, String> Map con i parametri da passare nell'header http.
     * @param successListener Response.Listener Listener per la risposta in caso di successo .
     * @param errorListener   Response.ErrorListener Listener per la risposta in caso di errore.
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> params, Map<String, String> header, Response.Listener<T> successListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.params = params;
        this.header = header;
        this.successListener = successListener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.header;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.params;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.v(GsonRequest.class.getSimpleName(), json);
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException uee) {
            Log.e(GsonRequest.class.getSimpleName(), "UnsupportedEncodingException", uee);
            return Response.error(new ParseError(uee));
        } catch (JsonSyntaxException jse) {
            Log.e(GsonRequest.class.getSimpleName(), "JsonSyntaxException", jse);
            return Response.error(new ParseError(jse));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        successListener.onResponse(response);
    }
}
