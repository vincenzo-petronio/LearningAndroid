package it.localhost.app.mobile.learningandroid.helper.login;

import bolts.Task;

/**
 * CONCRETE DECORATOR <br>
 * Verifica la lunghezza minima della password e rimuove gli space dall'username
 *
 * @author vincenzo.petronio on 20/02/2018.
 */

public class LengthEmptyDecorator extends EmptyDecorator {

    public LengthEmptyDecorator(ILoginStrategy loginStrategy) {
        super(loginStrategy);
    }

    @Override
    public Task login(String username, String psw) throws Exception {
        if (psw.length() < 3) {
            throw new Exception("Password is too short!");
        } else {
            return super.login(username, psw);
        }
    }
}
