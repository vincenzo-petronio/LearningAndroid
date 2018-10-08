package it.localhost.app.mobile.learningandroid.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import it.localhost.app.mobile.learningandroid.data.model.CommentEntity;
import it.localhost.app.mobile.learningandroid.repository.specification.SqlQuery;

/**
 * @author vincenzo.petronio on 06/10/2018.
 */
@RequiresApi(Build.VERSION_CODES.N)
public class CommentRepository extends SqlLiteBaseRepository {

    public CommentRepository(@NonNull Context context) {
        super(LocalDbHelper.getHelper(context, SqlQuery.TABLE_COMMENT_CREATE),
                SqlQuery.TABLE_COMMENT,
                new CommentToContentValuesMapper(),
                new CursorToCommentMapper()
        );
    }

    /**
     * Mapper da entity a ContentValues
     */
    private static class CommentToContentValuesMapper implements Mapper<CommentEntity, ContentValues> {

        @Override
        public ContentValues map(CommentEntity comment) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SqlQuery.TABLE_COMMENT_COLUMNBODY, comment.getBody());
            return contentValues;
        }
    }

    /**
     * Mapper da Cursor a entity
     */
    private static class CursorToCommentMapper implements Mapper<Cursor, CommentEntity> {

        @Override
        public CommentEntity map(Cursor cursor) {
            CommentEntity comment = new CommentEntity();
            comment.setBody(cursor.getString(cursor.getColumnIndex(SqlQuery.TABLE_COMMENT_COLUMNBODY)));
            return comment;
        }
    }
}
