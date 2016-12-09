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
import it.localhost.app.mobile.learningandroid.data.model.EventMessage;

/**
 *
 */
public class PubSubBottomFragment extends BaseFragment {

    private static final String TAG = PubSubBottomFragment.class.getSimpleName();
    private Unbinder mUnbinder;

    @BindView(R.id.tvBottom)
    protected TextView mTvBottom;

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
        return R.layout.fragment_pubsub_bottom;
    }

    @OnClick(R.id.btSendBottom)
    protected void onBtnSendTopClickListener() {
        EventBus.getDefault().post(new EventMessage("This is a Message from " + TAG));
    }
}
