package it.localhost.app.mobile.learningandroid.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bolts.Task;
import it.localhost.app.mobile.learningandroid.data.model.EntityBase;
import it.localhost.app.mobile.learningandroid.repository.specification.Specification;
import it.localhost.app.mobile.learningandroid.repository.specification.SqlSpecification;

/**
 * Repository per un db sqllite.
 *
 * @author vincenzo.petronio on 06/10/2018.
 * @since JKD 8
 */
@RequiresApi(Build.VERSION_CODES.N)
public class SqlLiteBaseRepository implements IRepository {

    private static final String TAG = SqlLiteBaseRepository.class.getSimpleName();
    private final SQLiteOpenHelper openHelper;
    private final String TABLE_NAME;
    private final Mapper toContentValuesMapper;
    private final Mapper fromCursorMapper;

    /**
     * Costruttore package-private non accessibile da UI.
     *
     * @param openHelper           SQLiteOpenHelper
     * @param tableName            String
     * @param toContentValueMapper Mapper
     * @param fromCursorMapper     Mapper
     */
    SqlLiteBaseRepository(SQLiteOpenHelper openHelper, String tableName, Mapper toContentValueMapper, Mapper fromCursorMapper) {
        this.openHelper = openHelper;
        this.TABLE_NAME = tableName;
        this.toContentValuesMapper = toContentValueMapper;
        this.fromCursorMapper = fromCursorMapper;
    }

    @Override
    public Task<Boolean> addAsync(EntityBase entity) {
        Log.v(TAG, "addAsync");
        return addAsync(Collections.singletonList(entity));
    }

    @Override
    public Task<Boolean> addAsync(Iterable entities) {
        Log.v(TAG, "addAsync");

        // try with resources .... from JDK 7
        try (SQLiteDatabase db = openHelper.getWritableDatabase()) {
            db.beginTransaction();
            entities.forEach(e -> db.insert(TABLE_NAME, null, (ContentValues) toContentValuesMapper.map(e)));
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
            return Task.forError(e);
        }
        return Task.forResult(true);
    }

    @Override
    public Task<Boolean> updateAsync(EntityBase entity) {
        Log.v(TAG, "updateAsync");

        // TODO
        return null;
    }

    @Override
    public Task<Boolean> removeAsync(EntityBase entity) {
        Log.v(TAG, "removeAsync");

        // TODO
        return null;
    }

    @Override
    public Task<Boolean> removeAsync(Specification specification) {
        Log.v(TAG, "removeAsync");

        // TODO
        return null;
    }

    @Override
    public Task<List> queryAsync(Specification specification) {
        Log.v(TAG, "queryAsync");

        final SqlSpecification sqlSpecification = (SqlSpecification) specification;

        try (final SQLiteDatabase db = openHelper.getReadableDatabase()) {
            final Cursor cursor = db.rawQuery(sqlSpecification.sqlQuery(), new String[]{}) ;

            List results = new ArrayList();

            for (int i = 0, size = cursor.getCount(); i < size; i++) {
                cursor.moveToPosition(i);
                results.add(fromCursorMapper.map(cursor));
            }

            cursor.close();
            return Task.forResult(results);
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
            return Task.forError(e);
        }
    }
}
