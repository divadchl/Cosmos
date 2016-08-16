package mx.com.serviciosinformaticosintegrales.nasa.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SqliteHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "cosmos";
    private final static int DATABASE_VERSION = 1;
    public static final String TABLE_NAME     = "favorities";
    public static final String COLUMN_ID      = BaseColumns._ID;
    public static final String COLUMN_TITULO  = "titulo";
    public static final String COLUMN_FOTO    = "foto";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TITULO + " TEXT NOT NULL," +
            COLUMN_FOTO + " TEXT NOT NULL)";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
