package it.localhost.app.mobile.learningandroid.helper.login;

import android.util.Log;

/**
 * Accesso con social, basato su access token.
 *
 * @author vincenzo.petronio on 20/02/2018.
 */

public class SocialStrategy implements ILoginStrategy {

    private static final String TAG = SocialStrategy.class.getSimpleName();
    private String mToken;

    public SocialStrategy(String token) {
        mToken = token;
    }

    @Override
    public void login(String email, String psw) {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append("email=")
                .append("email@example.com")
                .append(",")
                .append("token=")
                .append(mToken)
                .append("]");
        Log.i(TAG, sb.toString());
    }
}
