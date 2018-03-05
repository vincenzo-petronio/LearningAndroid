package it.localhost.app.mobile.learningandroid.data;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create a new REST client with a given API base URL
 */
public class ServiceGenerator {

    private static final long CONNECTION_TIMEOUT = 8;
    private final OkHttpClient.Builder mHttpClient;
    private final Retrofit.Builder mRetrofitBuilder;

    private ServiceGenerator(Builder builder) {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(builder.connectionTimeout != 0L ? builder.connectionTimeout : CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(builder.interceptor != null ? builder.interceptor : new LoggerInterceptor());
        if (builder.networkInterceptor != null) {
            mHttpClient.addInterceptor(builder.networkInterceptor);
        }

        mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(builder.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

//    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder()
//            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
//            .addInterceptor(new LoggerInterceptor());

//    private static Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder()
//            .baseUrl(Constants.API_JSONPLACEHOLDER_URL_BASE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    // N.B. Posso aggiungere più callAdapter in successione, Retrofit è in grado di selezionare
    // quello corretto a seconda del metodo chiamato (cioè del return type)

    /**
     * @param serviceClass Class<T>
     * @return <T>
     */
    public <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = mRetrofitBuilder.client(mHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }


    // BUILDER

    public static class Builder {

        // Required params
        private final String baseUrl;

        // Optional params
        private long connectionTimeout;
        private Interceptor interceptor;
        private Interceptor networkInterceptor;
        // ...

        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        /**
         * @param seconds long
         * @return Builder
         */
        public Builder connectionTimeout(long seconds) {
            this.connectionTimeout = seconds;
            return this;
        }

        /**
         * @param interceptor Interceptor
         * @return Builder
         */
        public Builder interceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        /**
         * @param networkInterceptor Interceptor
         * @return Builder
         */
        public Builder networkInterceptor(Interceptor networkInterceptor) {
            this.networkInterceptor = networkInterceptor;
            return this;
        }

        public ServiceGenerator build() {
            return new ServiceGenerator(this);
        }
    }
}
