package it.localhost.app.mobile.learningandroid.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.AsyncTask;
import android.util.Log;

/**
 *
 */
public class JsoupTask extends AsyncTask<String, Void, Document> {

    private static final String TAG = JsoupTask.class.getSimpleName();
    private JsoupTaskCallback callback;

    public JsoupTask(JsoupTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        Log.v(TAG, "onPreExecute");
        super.onPreExecute();
    }

    @Override
    protected Document doInBackground(String[] url) {
        Log.v(TAG, "doInBackground");
        Document doc = null;

        try {
            //Uri uri = Uri.parse(url[0]);

            doc = Jsoup
                    .connect(url[0])
                    // forzo l'user agent di Chrome Desktop per richiedere la versione Desktop del sito
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36")
                    .get();
        } catch (NullPointerException npe) {
            callback.onError(new NullPointerException("URI NULL!!!"));
            cancel(true);
        } catch (Exception e) {
            callback.onError(e);
            cancel(true);
        }

        return doc;
    }

    @Override
    protected void onCancelled() {
        Log.v(TAG, "onCancelled");
        super.onCancelled();

        callback.showProgress(false);
    }

    @Override
    protected void onPostExecute(Document document) {
        Log.v(TAG, "onPostExecute");
        super.onPostExecute(document);

        callback.showProgress(false);
        callback.onSuccess(document);
    }

    /**
     * Callback tra AsyncTask e Activity
     */
    public interface JsoupTaskCallback {

        void onSuccess(Document document);

        void onError(Exception exception);

        void showProgress(boolean isVisible);
    }
}
