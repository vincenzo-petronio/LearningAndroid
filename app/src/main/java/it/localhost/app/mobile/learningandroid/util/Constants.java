package it.localhost.app.mobile.learningandroid.util;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static it.localhost.app.mobile.learningandroid.util.Constants.AuthType.BASIC;
import static it.localhost.app.mobile.learningandroid.util.Constants.AuthType.JWT;
import static it.localhost.app.mobile.learningandroid.util.Constants.AuthType.OAUTH2;
import static it.localhost.app.mobile.learningandroid.util.Constants.CloudType.AWS;
import static it.localhost.app.mobile.learningandroid.util.Constants.CloudType.AZURE;
import static it.localhost.app.mobile.learningandroid.util.Constants.CloudType.GOOGLE;
import static it.localhost.app.mobile.learningandroid.util.Constants.Login.FB;
import static it.localhost.app.mobile.learningandroid.util.Constants.Login.GPLUS;
import static it.localhost.app.mobile.learningandroid.util.Constants.Login.LINKEDIN;
import static it.localhost.app.mobile.learningandroid.util.Constants.Login.TUMBLR;
import static it.localhost.app.mobile.learningandroid.util.Constants.Login.TWITTER;
import static it.localhost.app.mobile.learningandroid.util.Constants.Login.USER_PSW;

/**
 *
 */

public class Constants {

    public static final String URL_JSOUP = "http://www.italotreno.it/";
    public static final String PATH_JSON_TODOS = "todos.json";

    // API
    public static final String API_JSONPLACEHOLDER_URL_BASE = "http://jsonplaceholder.typicode.com/";

    // REALM
    public static final String DB_NAME = "my.realm";
    public static final long DB_SCHEMA_VERSION = 1;

    // EXTRA FOR BUNDLE
    public static final String EXTRA_USEDECORATOR = "EXTRA_USEDECORATOR";

    // SHAREDPREFERENCES
    public static final String SHARED_KEY_FIREBASE_TOKEN = "SHARED_KEY_FIREBASE_TOKEN";


    @IntDef({FB, GPLUS, LINKEDIN, TWITTER, TUMBLR, USER_PSW})
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

    @IntDef({BASIC, OAUTH2, JWT})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @interface AuthType {
        int BASIC = 1;
        int OAUTH2 = 2;
        int JWT = 3;
    }

    @IntDef({GOOGLE, AWS, AZURE})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    public @interface CloudType {
        int GOOGLE = 1;
        int AWS = 2;
        int AZURE = 3;
    }
}
