package it.localhost.app.mobile.learningandroid.helper.login;

import bolts.Task;

/**
 * DECORATOR
 *
 * @author vincenzo.petronio on 20/02/2018.
 */

abstract class LoginDecorator implements ILoginStrategy {

    private ILoginStrategy mLoginStrategy;

    LoginDecorator(ILoginStrategy loginStrategy) {
        mLoginStrategy = loginStrategy;
    }

    @Override
    public Task login(String username, String psw) throws Exception {
        // forwarding dei metodi
        return mLoginStrategy.login(username, psw);
    }
}
