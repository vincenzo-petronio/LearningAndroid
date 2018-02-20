package it.localhost.app.mobile.learningandroid.helper.login;

/**
 * @author vincenzo.petronio on 20/02/2018.
 */

public interface ILoginStrategy {

    /**
     * @param username String
     * @param psw      String
     */
    void login(String username, String psw) throws Exception;
}
