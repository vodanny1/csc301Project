package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SQLManager extends SQLiteOpenHelper {
    public static final String DB_NAME = "prof14.db";
    public static final int version = 1;

    public static enum LOGIN {
        TRUE,
        FALSE,
        NOUSER
    }

    public SQLManager(Context context) {
        super(context, DB_NAME, null, version);
    }

    public LOGIN validLogin(String id, String pass) {
        User user = getUserById(id);
        if (user == null) return LOGIN.NOUSER;
        String encode;
        try {
            encode = MD5Helper.getMD5(pass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return LOGIN.FALSE;
        }
        return user.pass.equals(encode) ? LOGIN.TRUE : LOGIN.FALSE;
    }

    public boolean register(String id, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        String query =
                String.format("INSERT INTO %s(id, pass) SELECT ?, ? WHERE NOT EXISTS(SELECT * FROM %s WHERE id=?)",
                        User.TABLE_NAME, User.TABLE_NAME);
        String md5pass;
        try {
            md5pass = MD5Helper.getMD5(pass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
        db.execSQL(query, new String[] {id, md5pass, id});
        // check affected rows
        Cursor c = db.rawQuery("SELECT changes() 'affected_rows'", null);
        c.moveToFirst();
        int affected = c.getInt(c.getColumnIndex("affected_rows"));
        db.close();
        return affected == 1;
    }

    public boolean addData(String fName, String lName, String fFood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CourseInformationItems.COLCODE ,fName);
        contentValues.put(CourseInformationItems.COLGRADE, lName);
        contentValues.put(CourseInformationItems.COLCOMMENT, fFood);

        long result = db.insert(CourseInformationItems.TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + CourseInformationItems.TABLE_NAME, null);
        return data;
    }

    public List<CourseInformationItems> getCourseItem(){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect  = {"ID", "FIRSTNAME", "LASTNAME", "FAVFOOD"};
        String tableName = "courseInformation";
        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<CourseInformationItems> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                CourseInformationItems courseitems = new CourseInformationItems();
                courseitems.setFirstName(cursor.getString(cursor.getColumnIndex("FIRSTNAME")));
                courseitems.setLastName(cursor.getString(cursor.getColumnIndex("LASTNAME")));
                courseitems.setFavFood(cursor.getString(cursor.getColumnIndex("FAVFOOD")));
                result.add(courseitems);
            } while (cursor.moveToNext());
        }
        return result;

    }

    public List<String> getCourseCode()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect  = {"FIRSTNAME"};
        String tableName = "courseInformation";
        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{



                result.add(cursor.getString(cursor.getColumnIndex("FIRSTNAME")));
            } while (cursor.moveToNext());
        }
        return result;
    }
    public List<CourseInformationItems> getCoursebyCourseCode(String code)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect  = {"ID", "FIRSTNAME", "LASTNAME", "FAVFOOD"};
        String tableName = "courseInformation";
        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,"FIRSTNAME = ?",new String[] {code},null,null,null);
        List<CourseInformationItems> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                CourseInformationItems courseitems = new CourseInformationItems();
                courseitems.setFirstName(cursor.getString(cursor.getColumnIndex("FIRSTNAME")));
                courseitems.setLastName(cursor.getString(cursor.getColumnIndex("LASTNAME")));
                courseitems.setFavFood(cursor.getString(cursor.getColumnIndex("FAVFOOD")));
                result.add(courseitems);
            } while (cursor.moveToNext());
        }
        return result;

    }

    public boolean rate(String name, String email, int rating, String login){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Professor.COL_NAME, name);
        contentValues.put(Professor.COL_EMAIL, email);
        contentValues.put(Professor.COL_RATE, rating);
        contentValues.put(Professor.COL_LOGIN, login);
        long result = 1;
        //long result = db.insert(Professor.TABLE_NAME, null, contentValues);
        //long result = db.update(Professor.TABLE_NAME, contentValues, "login=" + login, null);

        Cursor c = null;
        String q = String.format("SELECT * FROM professors WHERE name='%s' AND email='%s' AND login='%s'",name,email,login);
        c = db.rawQuery(q, null);
        if(c!=null && c.getCount() >0){
            //result = db.update(Professor.TABLE_NAME, contentValues, "login=" + login + " AND name=" + name + " AND email=" + email, null);
            db.update(Professor.TABLE_NAME, contentValues, "name = ? AND email = ? AND login = ?", new String[]{name, email, login});
        } else {
            result = db.insert(Professor.TABLE_NAME, null, contentValues);
        }
        /*
        int id = (int)db.insertWithOnConflict(Professor.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        long result = 1;
        if (id == -1){
            Log.d("LOGINN", "in here");
            db.update(Professor.TABLE_NAME, contentValues, "login=" + login + "and name=" + name, null);
        }
        */

        if (result == -1){
            return false;
        } else {
            return true;
        }
        //return;
    }

    public double getAvg(String name, String email){
        SQLiteDatabase db = this.getReadableDatabase();
        double avgVal = 0;

        /*
        String q = "SELECT AVG("+Professor.COL_RATE+") FROM " + Professor.TABLE_NAME + " WHERE " + Professor.COL_NAME +
                "=" + name + " AND " + Professor.COL_EMAIL + "=" + email;
        */
        String q = String.format("SELECT AVG(rating) FROM professors WHERE name='%s' AND email='%s'", name, email);

        Cursor c = db.rawQuery(q, null);
        if (c != null) {
            c.moveToFirst();
            avgVal = c.getDouble(0);
        }
        return avgVal;
    }

    public Cursor viewData(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        //String q = String.format("SELECT name FROM professors WHERE name='%s'", name);
        String q = String.format("SELECT * FROM professors WHERE name='%s'", name);

        //String q = "SELECT * FROM professors";
        //String q = "SELECT * FROM professors";
        Cursor cursor = db.rawQuery(q, null);

        return cursor;

    }

    public User getUserById(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                User.TABLE_NAME,
                null,
                "id=?",
                new String[] {id},
                null,
                null,
                null,
                null
        );
        if (cursor.moveToNext())
            return getUser(cursor);
        return null;
    }

    private User getUser(Cursor cursor) {
        return new User(cursor.getString(cursor.getColumnIndex(User.COL_ID))
        , cursor.getString(cursor.getColumnIndex(User.COL_PASS)));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE users (id TEXT PRIMARY KEY, pass TEXT)";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + Professor.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, rating INTEGER, login TEXT)"; // might have to change primary key
        db.execSQL(query2);

        String createTable = "CREATE TABLE "+ CourseInformationItems.TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FIRSTNAME TEXT, LASTNAME TEXT, FAVFOOD TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + Professor.TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CourseInformationItems.TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + Professor.TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CourseInformationItems.TABLE_NAME);
        onCreate(db);
    }
}
