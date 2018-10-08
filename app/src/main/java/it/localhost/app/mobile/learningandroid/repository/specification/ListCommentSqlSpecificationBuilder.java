package it.localhost.app.mobile.learningandroid.repository.specification;

/**
 * Builder creato intorno alla Specification così da poter costruire la query secondo fluent
 * interface.
 *
 * @author vincenzo.petronio on 07/10/2018.
 */
public class ListCommentSqlSpecificationBuilder {

    private static final String SELECT_ALL = "SELECT * FROM Comment ";
    private static final String WHERE = "WHERE ";
    private static final String ORDERBY = "ORDER BY ";
    private static final String SORT = " ";

    private String where = "";
    private String orderBy = "";
    private String sort = "";


    private ListCommentSqlSpecificationBuilder() {

    }

    public static ListCommentSqlSpecificationBuilder newBuilder() {
        return new ListCommentSqlSpecificationBuilder();
    }

    /**
     * e.g. 'id=4'
     *
     * @param clause String
     * @return ListCommentSqlSpecificationBuilder
     */
    public ListCommentSqlSpecificationBuilder where(String clause) {
        this.where = WHERE.concat(clause);
        return this;
    }

    /**
     * @param clause String column name
     * @return ListCommentSqlSpecificationBuilder
     */
    public ListCommentSqlSpecificationBuilder orderBy(String clause) {
        this.orderBy = ORDERBY.concat(clause);
        return this;
    }

    /**
     * Ordinamento
     *
     * @param ascending boolean true per ASC, false DESC
     * @return ListCommentSqlSpecificationBuilder
     */
    public ListCommentSqlSpecificationBuilder sort(boolean ascending) {
        this.sort = SORT.concat(ascending ? "ASC" : "DESC");
        return this;
    }

    public ListCommentSqlSpecification build() {
        return new ListCommentSqlSpecification(SELECT_ALL.concat(where).concat(orderBy).concat(sort));
    }

    /**
     * Specification per effettuare una select all su db comment.
     */
    private class ListCommentSqlSpecification implements SqlSpecification {

        private String query;

        ListCommentSqlSpecification(String query) {
            this.query = query;
        }

        @Override
        public String sqlQuery() {
            return this.query;
        }
    }
}
