package it.localhost.app.mobile.learningandroid.helper.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import it.localhost.app.mobile.learningandroid.R;

/**
 * @author vincenzo.petronio on 23/02/2018.
 */

public class ShareBottomSheet extends BottomSheetDialogFragment implements IBottomSheet {

    private static final String TAG = ShareBottomSheet.class.getSimpleName();
    private String mItemClicked;
    private IBundleCallback mBundleCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_bottomsheet_share, container, false);

        ImageView ivTwitter = view.findViewById(R.id.iv_twitter);
        ivTwitter.setOnClickListener(mOnClickListener);
        ImageView ivFacebook = view.findViewById(R.id.iv_facebook);
        ivFacebook.setOnClickListener(mOnClickListener);
        ImageView ivGplus = view.findViewById(R.id.iv_gplus);
        ivGplus.setOnClickListener(mOnClickListener);
        ImageView ivLinkedin = view.findViewById(R.id.iv_linkedin);
        ivLinkedin.setOnClickListener(mOnClickListener);
        ImageView ivTumblr = view.findViewById(R.id.iv_tumblr);
        ivTumblr.setOnClickListener(mOnClickListener);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mItemClicked = "" + view.getId();
            if (mBundleCallback != null) {
                Bundle bundle = new Bundle();
                bundle.putString("ITEM", mItemClicked);
                mBundleCallback.onItemClicked(bundle);
            }
        }
    };


    // IMPLEMENTS

    @Override
    public void showDialog(FragmentManager fragmentManager, String tag) {
        show(fragmentManager, tag);
    }

    @Override
    public void setCallback(IBundleCallback callback) {
        mBundleCallback = callback;
    }
}
