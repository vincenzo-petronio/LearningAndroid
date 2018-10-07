package it.localhost.app.mobile.learningandroid.repository.specification;

/**
 * @author vincenzo.petronio on 07/10/2018.
 */
public class SqlQuery {


    public static final String TABLE_COLUMNID = "Id";
    public static final String TABLE_COMMENT = "Comment";
    public static final String TABLE_COMMENT_COLUMNBODY = "Body";
    public static final String TABLE_COMMENT_CREATE = "CREATE TABLE " + TABLE_COMMENT +
            " (" +
            TABLE_COLUMNID + " INTEGER PRIMARY KEY," +
            TABLE_COMMENT_COLUMNBODY + " TEXT" +
            " )";
    public static final String TABLE_COMMENT_SELECTALL = "SELECT * FROM " + TABLE_COMMENT;
}
