package it.localhost.app.mobile.learningandroid.data;

import bolts.Task;

/**
 * @author vincenzo.petronio on 23/03/2018.
 */

public interface ResultCallback<T> {

    Task<T> onSuccess();
}
