package it.localhost.app.mobile.learningandroid.helper.login;

import bolts.Task;
import it.localhost.app.mobile.learningandroid.data.model.GenericAuthResult;

/**
 * @author vincenzo.petronio on 20/02/2018.
 */

public interface ILoginStrategy {

    /**
     * @param username String
     * @param psw      String
     */
    Task<GenericAuthResult> login(String username, String psw) throws Exception;
}
