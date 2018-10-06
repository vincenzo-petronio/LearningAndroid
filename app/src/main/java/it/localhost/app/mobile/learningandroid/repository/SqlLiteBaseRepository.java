package it.localhost.app.mobile.learningandroid.repository;

import com.google.android.gms.common.api.Api;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import bolts.Task;
import it.localhost.app.mobile.learningandroid.data.model.EntityBase;

/**
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

    public SqlLiteBaseRepository(SQLiteOpenHelper openHelper, String tableName, Mapper toContentValueMapper, Mapper fromCursorMapper) {
        this.openHelper = openHelper;
        this.TABLE_NAME = tableName;
        this.toContentValuesMapper = toContentValueMapper;
        this.fromCursorMapper = fromCursorMapper;
    }

    @Override
    public Task<Boolean> addAsync(EntityBase entity) {
        return addAsync(Collections.singletonList(entity));
    }

    @Override
    public Task<Boolean> addAsync(Iterable entities) {

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
        // TODO
        return null;
    }

    @Override
    public Task<Boolean> removeAsync(EntityBase entity) {
        // TODO
        return null;
    }

    @Override
    public Task<Boolean> removeAsync(Specification specification) {
        // TODO
        return null;
    }

    @Override
    public Task<List> queryAsync(Specification specification) {
        // TODO
        return null;
    }
}
