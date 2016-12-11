package it.localhost.app.mobile.learningandroid.data;

import it.localhost.app.mobile.learningandroid.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create a new REST client with a given API base URL
 */
public class ServiceGenerator {

    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder()
            .baseUrl(Constants.API_JSONPLACEHOLDER_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create());


    /**
     *
     * @param serviceClass Class<T>
     * @param <T>
     * @return <T>
     */
    public static <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = mRetrofitBuilder.client(mHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
