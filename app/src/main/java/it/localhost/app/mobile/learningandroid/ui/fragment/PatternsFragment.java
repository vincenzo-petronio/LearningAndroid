package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.activity.PatternsActivity;

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
        super.onViewCreated(view, savedInstanceState);

        initUI();
    }

    @Override
    public void onResume() {
        Log.v(TAG, "onDetach");
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

    @OnItemClick(R.id.lvItems)
    void OnItemClick(int position) {
        Log.d(TAG, "OnItemSelected: " + lvItems.getItemAtPosition(position));

        switch (lvItems.getItemAtPosition(position).toString()) {
            case "Strategy":
                mActivity.navigateTo(new PatternStrategyFragment());
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
