package it.localhost.app.mobile.learningandroid.helper.login;

import android.support.v4.app.FragmentManager;

/**
 * @author vincenzo.petronio on 23/02/2018.
 */

public interface IBottomSheet {

    void showDialog(FragmentManager fragmentManager, String tag);

    void setCallback(IBundleCallback callback);

}
