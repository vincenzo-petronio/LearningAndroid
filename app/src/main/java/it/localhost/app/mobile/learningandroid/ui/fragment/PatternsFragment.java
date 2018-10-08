package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.helper.login.BottomSheetFactory;
import it.localhost.app.mobile.learningandroid.helper.login.IBottomSheet;
import it.localhost.app.mobile.learningandroid.helper.login.IBundleCallback;
import it.localhost.app.mobile.learningandroid.repository.CommentRepository;
import it.localhost.app.mobile.learningandroid.ui.activity.PatternsActivity;
import it.localhost.app.mobile.learningandroid.util.Constants;

/**
 * Patterns home
 */
public class PatternsFragment extends BaseFragment {

    private static final String TAG = PatternsFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private PatternsActivity mActivity;

    @BindArray(R.array.patterns_items)
    String[] mStringsItems;
    @BindView(R.id.lvItems)
    ListView lvItems;

    @Override
    public void onAttach(Context context) {
        Log.v(TAG, "onAttach");
        super.onAttach(context);

        mActivity = (PatternsActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    public void onDetach() {
        Log.v(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_patterns;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @OnItemClick(R.id.lvItems)
    void OnItemClick(int position) {
        Log.d(TAG, "OnItemSelected: " + lvItems.getItemAtPosition(position));
        Bundle bundle;

        switch (lvItems.getItemAtPosition(position).toString().toUpperCase()) {
            case "STRATEGY":
                mActivity.navigateTo(new LoginFragment());
                break;
            case "DECORATOR":
                bundle = new Bundle();
                bundle.putBoolean(Constants.EXTRA_USEDECORATOR, true);
                BaseFragment fragment = new LoginFragment();
                fragment.setArguments(bundle);
                mActivity.navigateTo(fragment);
                break;
            case "FACTORY METHOD":
                IBottomSheet bottomSheet = BottomSheetFactory.getBottomSheet(new Random().nextInt(2 - 1 + 1) + 1);
                bottomSheet.setCallback(new IBundleCallback() {
                    @Override
                    public void onItemClicked(Bundle bundle) {
                        Log.i(TAG, bundle.toString());
                    }

                    @Override
                    public void onItemClicked(int position) {
                        Log.i(TAG, "position=" + "" + position);
                    }
                });
                bottomSheet.showDialog(getFragmentManager(), bottomSheet.getClass().getSimpleName());
                break;
            case "REPOSITORY":
                if (getContext() == null || !isAdded()) {
                    return;
                }
                mActivity.navigateTo(RepositoryFragment.newInstance(new CommentRepository(getContext())));
                break;
            default:
                break;
        }
    }

    private void initUI() {
        if (getActivity() == null) {
            return;
            //FIXME
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mStringsItems);
        lvItems.setAdapter(arrayAdapter);
    }
}
