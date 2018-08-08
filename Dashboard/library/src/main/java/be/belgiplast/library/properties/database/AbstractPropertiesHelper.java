package be.belgiplast.library.properties.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import be.belgiplast.library.properties.Property;

public class AbstractPropertiesHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "properties.db";

    private final String SQL_CREATE_ENTRIES;
    private final String SQL_DELETE_ENTRIES;

    private String tableName;
    private String group;

    public AbstractPropertiesHelper(Context context,String tablename,String group) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.tableName = tablename;
        SQL_CREATE_ENTRIES = String.format( "CREATE TABLE " + "%s" + " (" +
                PropertiesColumns._ID + " INTEGER PRIMARY KEY," +
                PropertiesColumns.COLUMN_NAME_ID + " TEXT," +
                PropertiesColumns.COLUMN_NAME_GROUP + " TEXT," +
                PropertiesColumns.COLUMN_NAME_VALUE + " TEXT," +
                PropertiesColumns.COLUMN_NAME_TYPE + " TEXT" +
                ")",tableName);
        SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS " + "%s",tableName);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public long insertProperty(Property task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(PropertiesColumns.COLUMN_NAME_ID, task.getName());
        values.put(PropertiesColumns.COLUMN_NAME_GROUP, task.getGroup());
        values.put(PropertiesColumns.COLUMN_NAME_VALUE, task.getValue());
        values.put(PropertiesColumns.COLUMN_NAME_TYPE, task.getType());



        // insert row
        long id = db.insert(PropertiesColumns.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Property getProperty(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableName,
                new String[]{tableName, PropertiesColumns.COLUMN_NAME_ID, PropertiesColumns.COLUMN_NAME_GROUP, PropertiesColumns.COLUMN_NAME_VALUE,PropertiesColumns.COLUMN_NAME_TYPE},
                PropertiesColumns._ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Property note = new Property();
        note.setName(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_ID)));
        note.setValue(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_VALUE)));
        note.setGroup(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_GROUP)));
        note.setType(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_TYPE)));


        // close the db connection
        cursor.close();

        return note;
    }

    public List<Property> getAllNotes() {
        List<Property> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + PropertiesColumns.TABLE_NAME + " ORDER BY " +
                PropertiesColumns.COLUMN_NAME_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Property note = new Property();
                note.setName(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_ID)));
                note.setValue(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_VALUE)));
                note.setGroup(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_GROUP)));
                note.setType(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_TYPE)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Property task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PropertiesColumns.COLUMN_NAME_ID, task.getName());
        values.put(PropertiesColumns.COLUMN_NAME_VALUE, task.getValue());
        values.put(PropertiesColumns.COLUMN_NAME_GROUP, task.getGroup());
        values.put(PropertiesColumns.COLUMN_NAME_TYPE, task.getType());

        // updating row
        return db.update(PropertiesColumns.TABLE_NAME, values, PropertiesColumns._ID + " = ?",
                new String[]{String.valueOf(task.getName())});
    }

    public void deleteNote(Property task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, PropertiesColumns._ID + " = ?",
                new String[]{String.valueOf(task.getName())});//TODO: Change this. Tsk has no id
        db.close();
    }
}
