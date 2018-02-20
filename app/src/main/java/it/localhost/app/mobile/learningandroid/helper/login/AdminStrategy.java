package it.localhost.app.mobile.learningandroid.helper.login;

import android.util.Log;

/**
 * Accesso con superuser administrator.
 *
 * @author vincenzo.petronio on 20/02/2018.
 */

public class AdminStrategy implements ILoginStrategy {

    private static final String TAG = AdminStrategy.class.getSimpleName();

    @Override
    public void login(String username, String psw) {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append("username=")
                .append("ADMIN")
                .append(",")
                .append("password=")
                .append("123456")
                .append("]");
        Log.i(TAG, sb.toString());
    }
}
