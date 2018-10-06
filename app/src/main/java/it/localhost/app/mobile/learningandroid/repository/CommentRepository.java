package it.localhost.app.mobile.learningandroid.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import it.localhost.app.mobile.learningandroid.data.model.CommentEntity;

/**
 * @author vincenzo.petronio on 06/10/2018.
 */
@RequiresApi(Build.VERSION_CODES.N)
public class CommentRepository extends SqlLiteBaseRepository {

    private static final String TABLE_COMMENT = "COMMENT";
    private static String SQL_CREATE_TABLE_COMMENT =
            "CREATE TABLE " + "COMMENT" +
                    " (" +
                    "_id INTEGER PRIMARY KEY," +
                    "body TEXT" +
                    ")";

    public CommentRepository(@NonNull Context context) {
        super(LocalDbHelper.getHelper(context, SQL_CREATE_TABLE_COMMENT),
                TABLE_COMMENT,
                new CommentToContentValuesMapper(),
                new CursorToCommentMapper()
        );
    }

    private static class CommentToContentValuesMapper implements Mapper<CommentEntity, ContentValues> {

        private final static String COLUMNNAME_BODY = "body";

        @Override
        public ContentValues map(CommentEntity comment) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNNAME_BODY, comment.getBody());
            return contentValues;
        }
    }

    private static class CursorToCommentMapper implements Mapper<Cursor, CommentEntity> {

        @Override
        public CommentEntity map(Cursor cursor) {
            CommentEntity comment = new CommentEntity();
            // TODO
            return comment;
        }
    }
}
