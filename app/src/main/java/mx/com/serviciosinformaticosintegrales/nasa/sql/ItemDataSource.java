package mx.com.serviciosinformaticosintegrales.nasa.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.com.serviciosinformaticosintegrales.nasa.model.Favorities;

public class ItemDataSource {
    private final SQLiteDatabase db;

    public ItemDataSource(Context context) {
        SqliteHelper helper = new SqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void saveItem(Favorities favorities)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteHelper.COLUMN_TITULO, favorities.getTitulo());
        contentValues.put(SqliteHelper.COLUMN_FOTO, favorities.getFoto());
        db.insert(SqliteHelper.TABLE_NAME, null, contentValues);
    }

    public List<Favorities> getAllItems()
    {
        List<Favorities> favoritiesList = new ArrayList<>();
        Cursor cursor = db.query(SqliteHelper.TABLE_NAME,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            Favorities favorities= new Favorities();
            favorities.setId(cursor.getInt(cursor.getColumnIndexOrThrow(SqliteHelper.COLUMN_ID)));
            favorities.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(SqliteHelper.COLUMN_TITULO)));
            favorities.setFoto(cursor.getString(cursor.getColumnIndexOrThrow(SqliteHelper.COLUMN_FOTO)));
            favoritiesList.add(favorities);
        }

        return favoritiesList;
    }
}
