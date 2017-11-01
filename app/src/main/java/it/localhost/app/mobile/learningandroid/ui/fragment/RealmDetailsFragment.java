package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Task;
import it.localhost.app.mobile.learningandroid.data.model.UserStory;

/**
 * @author vincenzo.petronio on 31/10/2017.
 */
public class RealmDetailsFragment extends BaseFragment {

    private static final String TAG = RealmDetailsFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private UserStory mBundleUserStory;

    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;

    @Override
    public void onAttach(Context context) {
        Log.v(TAG, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // BUNDLE
        try {
            mBundleUserStory = getArguments().getParcelable("BUNDLE_USERSTORY");
            if (mBundleUserStory == null) {
                throw new Exception("Bundle NULL!!!");
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
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

        initUI();
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
        return R.layout.fragment_realmdetails;
    }

    private void initUI() {
        mLlContainer.removeAllViews();

        for (Task task : mBundleUserStory.getTaskRealmCollection()) {
            CheckedTextView checkedTextView = new CheckedTextView(getActivity());
            if (task.isNeeded()) {
                checkedTextView.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
            } else {
                checkedTextView.setCheckMarkDrawable(0);
            }
            checkedTextView.setText(
                    Integer.toString(task.getId()) + " " +
                            task.getTitle() + " " +
                            Long.toString(task.getTimestamp()));
            mLlContainer.addView(checkedTextView);
        }
    }
}
