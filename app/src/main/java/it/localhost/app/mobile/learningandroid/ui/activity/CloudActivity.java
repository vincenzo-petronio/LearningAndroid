package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.helper.login.ClassicStrategy;
import it.localhost.app.mobile.learningandroid.helper.login.EmptyDecorator;
import it.localhost.app.mobile.learningandroid.helper.login.GoogleFirebaseStrategy;
import it.localhost.app.mobile.learningandroid.helper.login.ILoginStrategy;
import it.localhost.app.mobile.learningandroid.helper.login.LengthEmptyDecorator;
import it.localhost.app.mobile.learningandroid.util.Constants;

/**
 * @author vincenzo.petronio on 22/03/2018.
 */

public class CloudActivity extends BaseActivity {

    private static final String TAG = CloudActivity.class.getSimpleName();

    @Constants.CloudType
    private int mCloudType;

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.sp_clouds)
    Spinner mSpClouds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_cloud;
    }

    @Override
    public int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @OnItemSelected(R.id.sp_clouds)
    void onSpinnerCloudItemSelected(int position) {
        String id = (String) mSpClouds.getItemAtPosition(position);
        switch (id.toUpperCase(Locale.ITALY)) {
            case "":
                break;
            case "GOOGLE":
                setCloudType(Constants.CloudType.GOOGLE);
                break;
            case "AWS":
                setCloudType(Constants.CloudType.AWS);
                break;
            case "AZURE":
                setCloudType(Constants.CloudType.AZURE);
                break;
        }
    }

    @OnClick(R.id.bt_login)
    void onBtLoginClickListener() {
        switch (getCloudType()) {
            case Constants.CloudType.GOOGLE:
                login(new LengthEmptyDecorator(new EmptyDecorator(new GoogleFirebaseStrategy())));
                break;
            case Constants.CloudType.AWS:
                // TODO
                break;
            case Constants.CloudType.AZURE:
                // TODO
                break;
        }
    }

    @Constants.CloudType
    private int getCloudType() {
        return mCloudType;
    }

    private void setCloudType(@Constants.CloudType int cloudType) {
        mCloudType = cloudType;
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
