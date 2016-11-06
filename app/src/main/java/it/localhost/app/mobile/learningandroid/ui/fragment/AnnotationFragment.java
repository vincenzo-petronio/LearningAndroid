package it.localhost.app.mobile.learningandroid.ui.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.OnItemLongClick;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.activity.BaseActivity;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment
public class AnnotationFragment extends BaseFragment {

    private static final String TAG = AnnotationFragment.class.getSimpleName();

    // resources injection
    @ViewById
    ListView lvItems;
    @StringArrayRes(R.array.fake_person_name)
    String[] mNames;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    // equivalente di onViewCreated
    @AfterViews
    void initUI() {
        Log.v(TAG, "AfterViews");
        // TOOLBAR
        getActivity().setTitle(getString(R.string.annotationfragment_actionbar_title));

        // FAB
        ((BaseActivity) getActivity()).getFab().hide();

        // LISTVIEW
        List<String> items = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            items.add(mNames[new Random().nextInt(mNames.length)]);
        }
        lvItems.setAdapter(new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();
    }


    // EXTENDS

    @Override
    public int getIdLayout() {
        return R.layout.fragment_annotation;
    }

    // AndroidAnnotation
    @ItemClick
    void lvItemsItemClicked(String s) {
        Toast.makeText(getContext(), "My name is " + s, Toast.LENGTH_SHORT).show();
    }

    // ButterKnife
    @OnItemLongClick(R.id.lvItems)
    boolean lvItemsItemLongClicked(int position) {
        Toast.makeText(getContext(), "My name is at position " + "" + position, Toast.LENGTH_SHORT).show();
        return true;
    }
}
