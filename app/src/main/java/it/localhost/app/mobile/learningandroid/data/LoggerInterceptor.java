package it.localhost.app.mobile.learningandroid.data;

import android.util.Log;

import java.io.IOException;
import java.util.Locale;

import it.localhost.app.mobile.learningandroid.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 */

public class LoggerInterceptor implements Interceptor {

    private static final String TAG = LoggerInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        // Posso leggere il body solo una volta, altrimenti ottengo IllegalStateException.
        // https://github.com/square/retrofit/issues/1072
        String responseBodyCached = response.body().string();

        if (BuildConfig.DEBUG) {
            StringBuilder sQueryParams = new StringBuilder();
            for (String key : request.url().queryParameterNames()) {
                if (sQueryParams.length() >= 1) {
                    sQueryParams.append("&");
                }
                sQueryParams.append(key).append("=").append(request.url().queryParameterValues(key));
            }

            String sRequestFormatted = String.format(Locale.ITALY,
                    "[REQUEST]\n\tURL: %1$s\n\tHTTPS: %2$s\n\tQUERYPARAMS: %3$s",
                    request.url().url().toString(),
                    request.isHttps(),
                    sQueryParams.toString()
            );
            Log.v(TAG, sRequestFormatted);


            String sResponseFormatted = String.format(Locale.ITALY,
                    "[RESPONSE]\n\tHTTP_STATUS: %1$s\n\tREDIRECT: %2$s\n\tBODY: %3$s",
                    response.message(),
                    response.isRedirect(),
                    responseBodyCached
            ); // FIXME
            Log.v(TAG, sResponseFormatted);
        }

        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseBodyCached)).build();
    }
}
