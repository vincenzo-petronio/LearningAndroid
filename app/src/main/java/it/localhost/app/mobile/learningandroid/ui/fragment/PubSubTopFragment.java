package it.localhost.app.mobile.learningandroid.ui.fragment;

import org.greenrobot.eventbus.EventBus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.ComplexEventMessage;
import it.localhost.app.mobile.learningandroid.data.model.EventMessage;
import it.localhost.app.mobile.learningandroid.data.model.Movie;

/**
 *
 */

public class PubSubTopFragment extends BaseFragment {

    private static final String TAG = PubSubTopFragment.class.getSimpleName();
    private Unbinder mUnbinder;

    @BindView(R.id.tvTop)
    protected TextView mTvTop;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mUnbinder = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_pubsub_top;
    }

    @OnClick(R.id.btSendTop)
    protected void onBtnSendTopClickListener() {
        EventBus.getDefault().post(new EventMessage("This is a Message from " + TAG));
    }

    @OnClick(R.id.btSendObjectTop)
    protected void onBtSendObjectClickListener() {
        // E' possibile inviare un qualsiasi oggetto, basta aggiungere un campo all'EventMessage
        // N.B. Il message non arriva nell'Activity, perché l'onMessageEvent ha come parametro
        // ComplexEventMessage e non EventMessage!

        Movie movie = new Movie();
        movie.setCountry("ITA");
        movie.setId("000");
        movie.setDirector("Sergio Leone");

        EventBus.getDefault().post(new ComplexEventMessage("This is a Message with Object from " + TAG, movie));
    }
}
