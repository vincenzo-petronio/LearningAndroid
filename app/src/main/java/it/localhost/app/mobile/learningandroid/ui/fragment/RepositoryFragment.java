package it.localhost.app.mobile.learningandroid.ui.fragment;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.CommentEntity;
import it.localhost.app.mobile.learningandroid.repository.CommentRepository;

/**
 * Repository pattern
 */
public class RepositoryFragment extends BaseFragment {

    private static final String TAG = RepositoryFragment.class.getSimpleName();
    private CommentRepository mCommentRepository;

    @BindView(R.id.lv_items)
    ListView mLvItems;
    @BindView(R.id.bt_add)
    Button mBtAdd;
    @BindView(R.id.tv_text)
    EditText mEtText;

    public RepositoryFragment() {
        // Required empty public constructor
    }

    public static RepositoryFragment newInstance(CommentRepository repository) {
        RepositoryFragment fragment = new RepositoryFragment();
        fragment.mCommentRepository = repository;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, mView);

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
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_patternsrepository;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @OnClick(R.id.bt_add)
    void onBtAddClickListener() {
        CommentEntity comment = new CommentEntity();
        comment.setBody(mEtText.getText().toString());

        mCommentRepository.addAsync(comment);
    }

}
