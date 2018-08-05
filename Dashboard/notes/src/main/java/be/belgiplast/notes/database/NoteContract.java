package be.belgiplast.notes.database;

import android.provider.BaseColumns;

public final class NoteContract {
    private NoteContract(){}

    public static final class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    }
}
