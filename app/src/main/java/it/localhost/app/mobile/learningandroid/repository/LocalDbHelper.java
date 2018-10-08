package it.localhost.app.mobile.learningandroid.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * @author vincenzo.petronio on 06/10/2018.
 */
public class LocalDbHelper extends SQLiteOpenHelper {

    private static LocalDbHelper mInstance;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LocalDB.db";
    private String SQL_CREATE_TABLE;


    public static synchronized LocalDbHelper getHelper(@Nullable Context context, String sql_create_table) {
        if (mInstance == null) {
            mInstance = new LocalDbHelper(context, sql_create_table);
        }

        return mInstance;
    }

    private LocalDbHelper(@Nullable Context context, String sqlCreateTable) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.SQL_CREATE_TABLE = sqlCreateTable;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ " ");
        onCreate(sqLiteDatabase);
    }
}
