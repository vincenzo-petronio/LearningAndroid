package it.localhost.app.mobile.learningandroid.repository;

/**
 * @author vincenzo.petronio on 06/10/2018.
 */
public interface Mapper<From, To> {

    To map(From from);
}
