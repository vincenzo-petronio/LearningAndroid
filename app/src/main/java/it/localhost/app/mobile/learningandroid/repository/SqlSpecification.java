package it.localhost.app.mobile.learningandroid.repository;

/**
 * @author vincenzo.petronio on 06/10/2018.
 */
public interface SqlSpecification extends Specification {

    /**
     * @return String SQL con la query
     */
    String sqlQuery();
}
