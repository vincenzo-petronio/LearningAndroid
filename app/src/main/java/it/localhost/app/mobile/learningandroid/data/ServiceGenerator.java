package it.localhost.app.mobile.learningandroid.data;

import it.localhost.app.mobile.learningandroid.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create a new REST client with a given API base URL
 */
public class ServiceGenerator {

    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggerInterceptor());

    private static Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder()
            .baseUrl(Constants.API_JSONPLACEHOLDER_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    // N.B. Posso aggiungere più callAdapter in successione, Retrofit è in grado di selezionare
    // quello corretto a seconda del metodo chiamato (cioè del return type)


    /**
     * @param serviceClass Class<T>
     * @return <T>
     */
    public static <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = mRetrofitBuilder.client(mHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
