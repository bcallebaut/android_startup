package be.belgiplast.dashboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import be.belgiplast.dashboard.database.CoachContract.TaskEntry;
import be.belgiplast.library.tasks.AbstractTask;
import be.belgiplast.library.tasks.Task;
import be.belgiplast.library.tasks.TaskStates;

public class CoachHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tasks.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskEntry.TABLE_NAME + " (" +
                    TaskEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TaskEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    TaskEntry.COLUMN_NAME_STATE + " TEXT," +
                    TaskEntry.COLUMN_NAME_STARTDATE + " TEXT," +TaskEntry.COLUMN_NAME_ENDDATE + " TEXT" +")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;

    public CoachHelper(Context context) {
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

    public long insertTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(TaskEntry.COLUMN_NAME_TITLE, task.getName());
        values.put(TaskEntry.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(TaskEntry.COLUMN_NAME_STATE, task.getState().toString());
        values.put(TaskEntry.COLUMN_NAME_STARTDATE, task.getStartDate().toString());
        values.put(TaskEntry.COLUMN_NAME_ENDDATE, task.getStartDate().toString());

        // insert row
        long id = db.insert(TaskEntry.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Task getTask(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TaskEntry.TABLE_NAME,
                new String[]{TaskEntry.TABLE_NAME, TaskEntry.COLUMN_NAME_DESCRIPTION, TaskEntry.COLUMN_NAME_STATE,TaskEntry.COLUMN_NAME_STARTDATE,TaskEntry.COLUMN_NAME_ENDDATE},
                TaskEntry._ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        AbstractTask task = new AbstractTask();
        task.setName(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_TITLE)));
        task.setDescription(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_DESCRIPTION)));
        task.setState(TaskStates.valueOf(TaskStates.class,cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_STATE))));
        task.setStartDate(Date.valueOf(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_STARTDATE))));
        task.setEndDate(Date.valueOf(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_ENDDATE))));

        // close the db connection
        cursor.close();

        return task;
    }

    public List<Task> getAllNotes() {
        List<Task> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TaskEntry.TABLE_NAME + " ORDER BY " +
                TaskEntry.COLUMN_NAME_STATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AbstractTask task = new AbstractTask();
                task.setName(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_TITLE)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_DESCRIPTION)));
                task.setState(TaskStates.valueOf(TaskStates.class,cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_STATE))));
                task.setStartDate(Date.valueOf(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_STARTDATE))));
                task.setEndDate(Date.valueOf(cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_ENDDATE))));

                notes.add(task);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + TaskEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskEntry.COLUMN_NAME_TITLE, task.getName());
        values.put(TaskEntry.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(TaskEntry.COLUMN_NAME_STATE, task.getState().toString());
        values.put(TaskEntry.COLUMN_NAME_STARTDATE, task.getStartDate().toString());
        values.put(TaskEntry.COLUMN_NAME_ENDDATE, task.getStartDate().toString());

        // updating row
        return db.update(TaskEntry.TABLE_NAME, values, TaskEntry._ID + " = ?",
                new String[]{String.valueOf(task.getName())}); //TODO: Change this. Tsk has no id
    }

    public void deleteNote(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TaskEntry.TABLE_NAME, TaskEntry._ID + " = ?",
                new String[]{String.valueOf(task.getName())});//TODO: Change this. Tsk has no id
        db.close();
    }
}
