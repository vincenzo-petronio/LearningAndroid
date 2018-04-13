package it.localhost.app.mobile.learningandroid.helper.login;

import bolts.Task;

/**
 * CONCRETE DECORATOR <br>
 * Rimuove gli space dall'username
 *
 * @author vincenzo.petronio on 20/02/2018.
 */

public class EmptyDecorator extends LoginDecorator {

    public EmptyDecorator(ILoginStrategy loginStrategy) {
        super(loginStrategy);
    }

    @Override
    public Task login(String username, String psw) throws Exception {
        // nell'override modifico il comportamento
        String usernameNoEmptySpace = username.replace(" ", "");
        return super.login(usernameNoEmptySpace, psw);
    }
}
