package it.localhost.app.mobile.learningandroid.repository.specification;

/**
 *
 * @author vincenzo.petronio on 07/10/2018.
 */
public class ListCommentSqlSpecification implements SqlSpecification {
    @Override
    public String sqlQuery() {
        return SqlQuery.TABLE_COMMENT_SELECTALL;
    }
}
