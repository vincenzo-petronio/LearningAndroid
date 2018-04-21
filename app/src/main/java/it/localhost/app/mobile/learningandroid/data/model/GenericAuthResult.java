package it.localhost.app.mobile.learningandroid.data.model;

/**
 * Generic Version of the GenericAuthResult class. <br>
 * A type variable T can be any non-primitive type: class type, interface type, array type, or even another type variable.
 *
 * @author vincenzo.petronio on 12/04/2018.
 */
public class GenericAuthResult<T> {

    private final T mGenericResult;

    // Item 4: Enforce noninstantiability with a private constructor
    private GenericAuthResult(T t) {
        this.mGenericResult = t;
    }

    // Item 1: consider static factory methods instead of constructors
    public static <T2> GenericAuthResult newGenericAuthResult(T2 t2) {
        return new GenericAuthResult<T2>(t2);
    }

    public T getGenericResult() {
        return mGenericResult;
    }
}
