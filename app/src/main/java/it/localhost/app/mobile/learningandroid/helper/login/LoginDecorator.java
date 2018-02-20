package it.localhost.app.mobile.learningandroid.helper.login;

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
    public void login(String username, String psw) throws Exception {
        // forwarding dei metodi
        mLoginStrategy.login(username, psw);
    }
}
