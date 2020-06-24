//package comineashantrehan.linkedin.httpswww.ucourse_sprint_3;
package comineashantrehan.linkedin.httpswww.ucourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.sql.Blob;

public class Document_Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "documents.db";
    public static final int version = 1;


    public Document_Database(Context context) {
        super(context, DB_NAME, null, version);
    }

    public boolean Upload(String name, String image)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Documents.COL_DOCNAME, name);
        contentValues.put(Documents.COL_IMAGE, image);

        long result = db.insert(Documents.TABLE_NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    public boolean Download(String name)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        String filename;


        return false; // TODO
    }

    public Documents getDocumentByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.query(
                Documents.TABLE_NAME,
                null,
                "name=?",
                new String[] {name},
                null,
                null,
                null,
                null
        );

        if (cursor2.moveToNext())
            return getDoc(cursor2);
        return null;
    }

    private Documents getDoc(Cursor cursor)
    {
        return new Documents(cursor.getString(cursor.getColumnIndex(Documents.COL_DOCNAME)));
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + Documents.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, documentName TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Documents.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Documents.TABLE_NAME);
        onCreate(db);
    }
}