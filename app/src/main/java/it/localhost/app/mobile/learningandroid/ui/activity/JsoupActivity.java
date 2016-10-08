package it.localhost.app.mobile.learningandroid.ui.activity;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.JsoupTask;
import it.localhost.app.mobile.learningandroid.util.Constants;

public class JsoupActivity extends AppCompatActivity implements JsoupTask.JsoupTaskCallback {

    private static final String TAG = JsoupActivity.class.getSimpleName();
    private Context mContex;
    private StringBuffer sb;

    @BindView(R.id.pb)
    ProgressBar mProgressBar;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        //VIEW
        setContentView(R.layout.activity_jsoup);
        mContex = this;

        initUi();
        initData();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    private void initUi() {
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(R.string.jsoup_actionbar_title);
    }

    private void initData() {
        new JsoupTask(this).execute(Constants.URL_JSOUP);
    }

    private StringBuffer parseData(Document document) {
        if (sb == null) {
            sb = new StringBuffer();
        }

        sb.append(document.baseUri());
        sb.append("\n");
        sb.append(document.title());
        sb.append("\n");

        // Ho 3 div con questa classe
        Elements divElements = document.getElementsByClass("destination-list");

        // Per ogni Element (div) trovato ...
        for (Element divElement : divElements) {

            // ... prendo il parente, cioè il div superiore, e ne estraggo il text
            String titolo = divElement.parent().select("div.section-title").text();
            sb.append("\n").append(titolo).append("\n");

            // Poi prendo tutti i figli con tag <a>
            Elements aElements = divElement.select("a");
            for (Element aElement : aElements) {
                sb.append(aElement.text() + "\n");
            }
        }

        return sb;
    }

    private void setData(StringBuffer stringBuffer) {
        tv.setText(stringBuffer.toString());
    }

    // CALLBACK

    @Override
    public void onSuccess(Document document) {
        Log.d(TAG, "onSuccess: " + document.title());

        setData(parseData(document));
    }

    @Override
    public void onError(Exception exception) {
        Log.e(TAG, "onError: " + exception);

        setData(new StringBuffer(exception.getMessage()));
    }

    @Override
    public void showProgress(boolean isVisible) {
        if (isVisible) {
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        }
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
