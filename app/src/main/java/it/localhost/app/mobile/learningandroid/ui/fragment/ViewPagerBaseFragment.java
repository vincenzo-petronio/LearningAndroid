package it.localhost.app.mobile.learningandroid.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import it.localhost.app.mobile.learningandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPagerBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPagerBaseFragment extends BaseFragment {

    private static final String TAG = ViewPagerBaseFragment.class.getSimpleName();
    private static final String ARG_TAB = "ARG_TAB";
    private int mTabNumber;

    @BindView(R.id.container)
    FrameLayout mContainer;

    public ViewPagerBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mTabNumber Index of Tab (0-based)
     * @return A new instance of fragment ViewPagerBaseFragment.
     */
    public static ViewPagerBaseFragment newInstance(int mTabNumber) {
        ViewPagerBaseFragment fragment = new ViewPagerBaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TAB, mTabNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTabNumber = getArguments().getInt(ARG_TAB, -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch (mTabNumber) {
            case 0:
                mContainer.setBackgroundColor(ContextCompat.getColor(ViewPagerBaseFragment.this.getContext(), android.R.color.holo_green_dark));
                break;
            case 1:
                mContainer.setBackgroundColor(ContextCompat.getColor(ViewPagerBaseFragment.this.getContext(), android.R.color.holo_red_dark));
                break;
            case 2:
                mContainer.setBackgroundColor(ContextCompat.getColor(ViewPagerBaseFragment.this.getContext(), android.R.color.holo_blue_dark));
                break;
            case 3:
                mContainer.setBackgroundColor(ContextCompat.getColor(ViewPagerBaseFragment.this.getContext(), android.R.color.holo_orange_dark));
                break;
            default:
                break;
        }
    }

    @Override
    public int getIdLayout() {
        return R.layout.framelayout_container;
    }

}
