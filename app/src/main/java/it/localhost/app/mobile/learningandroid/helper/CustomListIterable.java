package it.localhost.app.mobile.learningandroid.helper;

import android.support.annotation.NonNull;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Custom List per fornire un custom Iterator.
 *
 * @author vincenzo.petronio
 */

public class CustomListIterable<E> extends AbstractList<E> implements Iterable<E> {

    private final E[] mCollection;

    public CustomListIterable(@NonNull E[] collection) {
        mCollection = collection;
    }

    @Override
    public E get(int index) {
        return mCollection[index];
    }

    @Override
    public int size() {
        return mCollection.length;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    // CUSTOM ITERATOR
    class CustomIterator implements Iterator<E> {

        int currentElement = 0;

        @Override
        public boolean hasNext() {
            // Considera true solo per i primi 5 elementi
            if (currentElement < mCollection.length && mCollection[currentElement] != null && currentElement < 5) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public E next() {
//            if (!hasNext()) {
//                throw new NoSuchElementException();
//            }
            return mCollection[currentElement++];
        }

        @Override
        public void remove() {
            // throw new UnsupportedOperationException("remove");
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
        }
    }
}
