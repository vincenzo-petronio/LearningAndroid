package it.localhost.app.mobile.learningandroid.ui.activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.EventMessage;

/**
 * Publish-Subscribe Design Pattern
 */
public class PubSubActivity extends BaseActivity {

    private static final String TAG = PubSubActivity.class.getSimpleName();

    //    @BindView(R.id.fragTop)
//    protected Fragment mFragmentTop;
//    @BindView(R.id.fragBottom)
//    protected Fragment mFragmentBottom;
    @BindView(R.id.btSend)
    protected Button mBtSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_pubsub;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @OnClick(R.id.btSend)
    protected void onBtSendClickListener() {
        EventBus.getDefault().post(new EventMessage("This is a Message from " + TAG));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage eventMessage) {
        String s = TAG + " has received: ";
        Toast.makeText(this, s + eventMessage.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
