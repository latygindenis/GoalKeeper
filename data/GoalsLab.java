package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalCursorWrapper;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsBaseHelper;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsDbSchema;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsDbSchema.GoalsTable;

/**
 * Created by denis on 13.06.2018.
 */

public class GoalsLab{

    private Context context;
    private SQLiteDatabase database;

    private static GoalsLab sGoalsLab;

    public static GoalsLab get(Context context){
        if(sGoalsLab == null){
            sGoalsLab = new GoalsLab(context);
        }
        return sGoalsLab;
    }

    private GoalsLab(Context context) {
        this.context = context.getApplicationContext();
        database = new GoalsBaseHelper(context).getWritableDatabase();
    }
    public void addGoal(Goal goal){
       ContentValues values = getContentValues(goal);
       database.insert(GoalsTable.NAME, null, values);
    }

    public Goal getGoal(UUID uuid){
      try (GoalCursorWrapper cursor = queryGoals(GoalsTable.Cols.UUID +" = ?",
              new String[]{uuid.toString()})
      ){
          if (cursor.getCount() ==0){
              return null;
          }
          cursor.moveToFirst();
          return cursor.getGoal();
      }
    }

    public void delGoal(Goal goal){
        String uuidString = goal.getUuid().toString();
        database.delete(GoalsTable.NAME,
                GoalsTable.Cols.UUID + " = ? ",
                new String[]{uuidString});
    }
    public static ContentValues getContentValues (Goal goal) {
        ContentValues values = new ContentValues();
        ArrayConvertHelper arrayConvertHelper =new ArrayConvertHelper();
        values.put(GoalsTable.Cols.UUID, goal.getUuid().toString());
        values.put(GoalsTable.Cols.TITLE, goal.getTitle_goal());
        values.put(GoalsTable.Cols.SUCCESS_COUNT, goal.getSuccess_count());
        values.put(GoalsTable.Cols.SUCCESS_DATES, arrayConvertHelper.fromArrayToString(goal.getSuccess_date()));
        return values;
    }

    public ArrayList<Goal> getGoals() {

        ArrayList goals = new ArrayList();

        try(GoalCursorWrapper cursor = queryGoals(null, null)){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                goals.add(cursor.getGoal());
                cursor.moveToNext();
            }
        }
        return goals;
    }


    private GoalCursorWrapper queryGoals(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(GoalsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new GoalCursorWrapper(cursor);
    }
    public void updateGoal(Goal goal) {
        String uuidString = goal.getUuid().toString();
        ContentValues values = getContentValues(goal);

        database.update(GoalsTable.NAME, values,
                GoalsTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }
}

