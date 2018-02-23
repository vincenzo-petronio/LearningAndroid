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

import it.localhost.app.mobile.learningandroid.R;

/**
 * @author vincenzo.petronio on 23/02/2018.
 */

public class EditBottomSheet extends BottomSheetDialogFragment implements IBottomSheet {

    private static final String TAG = EditBottomSheet.class.getSimpleName();
    private int mItemClickedPosition;
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
        return inflater.inflate(R.layout.layout_bottomsheet_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mItemClickedPosition = view.getId();
            if (mBundleCallback != null) {
                mBundleCallback.onItemClicked(mItemClickedPosition);
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
