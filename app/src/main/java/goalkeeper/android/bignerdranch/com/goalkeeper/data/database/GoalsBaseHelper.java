package goalkeeper.android.bignerdranch.com.goalkeeper.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsDbSchema.GoalsTable;

/**
 * Created by denis on 15.06.2018.
 */

public class GoalsBaseHelper extends SQLiteOpenHelper{
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "goalsBase.db";

    public GoalsBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + GoalsTable.NAME + "(" +
                        " _id integer primary key autoincrement, "+
                        GoalsTable.Cols.UUID + ", " +
                        GoalsTable.Cols.TITLE + ", " +
                        GoalsTable.Cols.SUCCESS_DATES + ", "+
                        GoalsTable.Cols.HOUR_NOTIF + ", " +
                        GoalsTable.Cols.MIN_NOTIF + ", " +
                        GoalsTable.Cols.ENABLE_NOTIF + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
