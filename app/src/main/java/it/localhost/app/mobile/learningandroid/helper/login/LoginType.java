package it.localhost.app.mobile.learningandroid.helper.login;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author vincenzo.petronio on 20/02/2018.
 */

public class LoginType {

    @IntDef({Login.FB, Login.GPLUS, Login.LINKEDIN, Login.TWITTER, Login.TUMBLR, Login.USER_PSW})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @interface Login {
        int FB = 1;
        int GPLUS = 2;
        int LINKEDIN = 3;
        int TWITTER = 4;
        int TUMBLR = 5;
        int USER_PSW = 6;
    }

    @IntDef({AuthType.BASIC, AuthType.OAUTH2, AuthType.JWT})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @interface AuthType {
        int BASIC = 1;
        int OAUTH2 = 2;
        int JWT = 3;
    }

    //    @Login
    private int mLogin;

    @Login
    public int getLogin() {
        return mLogin;
    }

    public void setLogin(@Login int login) {
        mLogin = login;
    }
}
