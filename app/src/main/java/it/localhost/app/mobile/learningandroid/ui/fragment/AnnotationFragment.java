package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.localhost.app.mobile.learningandroid.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AnnotationFragment extends Fragment {

    public AnnotationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_annotation, container, false);
    }
}
