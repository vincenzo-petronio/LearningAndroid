package it.localhost.app.mobile.learningandroid.helper.login;

import android.os.Bundle;

/**
 * @author vincenzo.petronio on 23/02/2018.
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/IandI/nogrow.html">Evolving
 * Interfaces</a>
 */
public interface IBundleCallback extends ISimpleCallback {

    void onItemClicked(Bundle bundle);
}
