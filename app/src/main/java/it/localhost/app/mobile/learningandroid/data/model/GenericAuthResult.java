package it.localhost.app.mobile.learningandroid.data.model;

/**
 * @author vincenzo.petronio on 12/04/2018.
 */
public class GenericAuthResult<T> {

    private final T mGenericResult;

    private GenericAuthResult(T t) {
        this.mGenericResult = t;
    }

    public static <T2> GenericAuthResult<T2> newGenericAuthResult(T2 t2) {
        return new GenericAuthResult<T2>(t2);
    }

    public T getGenericResult() {
        return mGenericResult;
    }
}
