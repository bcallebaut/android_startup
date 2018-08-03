package be.belgiplast.dashboard.database;

import android.provider.BaseColumns;

public final class CoachContract {
    private CoachContract(){}

    public static final class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_STARTDATE = "start";
        public static final String COLUMN_NAME_ENDDATE = "end";

    }
}
