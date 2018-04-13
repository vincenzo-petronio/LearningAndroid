package it.localhost.app.mobile.learningandroid.helper.login;

import bolts.Task;

/**
 * @author vincenzo.petronio on 20/02/2018.
 */

public interface ILoginStrategy<T> {

    /**
     * @param username String
     * @param psw      String
     */
    Task<T> login(String username, String psw) throws Exception;
}
