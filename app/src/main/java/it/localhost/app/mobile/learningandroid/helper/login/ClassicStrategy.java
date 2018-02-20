package it.localhost.app.mobile.learningandroid.helper.login;

import android.util.Log;

/**
 * Accesso classico con username e password.
 *
 * @author vincenzo.petronio on 20/02/2018.
 */

public class ClassicStrategy implements ILoginStrategy {

    private static final String TAG = ClassicStrategy.class.getSimpleName();

    @Override
    public void login(String username, String psw) {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append("username=")
                .append(username)
                .append(",")
                .append("password=")
                .append(psw)
                .append("]");
        Log.i(TAG, sb.toString());
    }
}
