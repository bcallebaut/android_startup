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
import be.belgiplast.notes.model.BaseNote;
import be.belgiplast.notes.model.Note;

public class AbstractPropertiesHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "properties.db";

    protected final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + "%s" + " (" +
                    PropertiesColumns._ID + " INTEGER PRIMARY KEY," +
                    PropertiesColumns.COLUMN_NAME_ID + " TEXT," +
                    PropertiesColumns.COLUMN_NAME_GROUP + " TEXT," +
                    PropertiesColumns.COLUMN_NAME_VALUE + " TEXT," +
                    PropertiesColumns.COLUMN_NAME_TYPE + " TEXT" +
                    ")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + "%s";

    public AbstractPropertiesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public long insertTask(Note task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(PropertiesColumns.COLUMN_NAME_ID, task.getId());
        values.put(PropertiesColumns.COLUMN_NAME_TEXT, task.getText());
        values.put(PropertiesColumns.COLUMN_NAME_TIMESTAMP, task.getTimestamp().toString());



        // insert row
        long id = db.insert(PropertiesColumns.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Property getProperty(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PropertiesColumns.TABLE_NAME,
                new String[]{PropertiesColumns.TABLE_NAME, PropertiesColumns.COLUMN_NAME_ID, PropertiesColumns.COLUMN_NAME_TEXT, PropertiesColumns.COLUMN_NAME_TIMESTAMP},
                PropertiesColumns._ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new BaseNote();
        note.setId(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_ID)));
        note.setText(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_TEXT)));
        note.setTimestamp(Date.valueOf(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_TIMESTAMP))));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + PropertiesColumns.TABLE_NAME + " ORDER BY " +
                PropertiesColumns.COLUMN_NAME_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new BaseNote();
                note.setId(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_ID)));
                note.setText(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_TEXT)));
                note.setTimestamp(Date.valueOf(cursor.getString(cursor.getColumnIndex(PropertiesColumns.COLUMN_NAME_TIMESTAMP))));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + PropertiesColumns.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Note task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PropertiesColumns.COLUMN_NAME_ID, task.getId());
        values.put(PropertiesColumns.COLUMN_NAME_TEXT, task.getText());
        values.put(PropertiesColumns.COLUMN_NAME_TIMESTAMP, task.getTimestamp().toString());

        // updating row
        return db.update(PropertiesColumns.TABLE_NAME, values, PropertiesColumns._ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    public void deleteNote(Note task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PropertiesColumns.TABLE_NAME, PropertiesColumns._ID + " = ?",
                new String[]{String.valueOf(task.getId())});//TODO: Change this. Tsk has no id
        db.close();
    }
}
