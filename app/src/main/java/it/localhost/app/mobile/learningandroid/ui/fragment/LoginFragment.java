package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.helper.login.AdminStrategy;
import it.localhost.app.mobile.learningandroid.helper.login.ClassicStrategy;
import it.localhost.app.mobile.learningandroid.helper.login.EmptyDecorator;
import it.localhost.app.mobile.learningandroid.helper.login.ILoginStrategy;
import it.localhost.app.mobile.learningandroid.helper.login.LengthEmptyDecorator;
import it.localhost.app.mobile.learningandroid.helper.login.SocialStrategy;
import it.localhost.app.mobile.learningandroid.util.Constants;

/**
 * @author vincenzo.petronio on 20/02/2018.
 */

public class LoginFragment extends BaseFragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private boolean mBundleUseDecorator;

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    @Override
    public void onAttach(Context context) {
        Log.v(TAG, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBundleUseDecorator = getArguments().getBoolean(Constants.EXTRA_USEDECORATOR, false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_patternstrategy;
    }

    @OnClick({
            R.id.bt_login,
            R.id.bt_login_admin,
            R.id.iv_login_fb,
            R.id.iv_login_gplus,
            R.id.iv_login_linkedin,
            R.id.iv_login_tumblr,
            R.id.iv_login_twitter
    })
    void onLoginClickListener(View view) {
        switch (view.getId()) {
            case R.id.iv_login_fb:
            case R.id.iv_login_gplus:
            case R.id.iv_login_linkedin:
            case R.id.iv_login_tumblr:
            case R.id.iv_login_twitter:
                login(new SocialStrategy("7h1sI5AnAcc3ssT0kenExamp13"));
                break;
            case R.id.bt_login:
                login(mBundleUseDecorator ? new LengthEmptyDecorator(new EmptyDecorator(new ClassicStrategy())) : new ClassicStrategy());
                break;
            case R.id.bt_login_admin:
                login(new AdminStrategy());
                break;
            default:
                break;

        }
    }

    private void login(ILoginStrategy loginStrategy) {
        try {
            loginStrategy.login(getUsername(), getPassword());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getLocalizedMessage());
        }
    }

    private String getUsername() {
        return mEtUsername.getText().toString().trim();
    }

    private String getPassword() {
        return mEtPassword.getText().toString().trim();
    }
}
