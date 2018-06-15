package goalkeeper.android.bignerdranch.com.goalkeeper.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsDbSchema.GoalsTable;

/**
 * Created by denis on 15.06.2018.
 */

public class GoalCursorWrapper extends CursorWrapper{
    public GoalCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Goal getGoal(){
       String uuidString = getString(getColumnIndex(GoalsTable.Cols.UUID));
       String title = getString(getColumnIndex(GoalsTable.Cols.TITLE));
       int success_count = getInt(getColumnIndex(GoalsTable.Cols.SUCCESS_COUNT));

       Goal goal = new Goal(UUID.fromString(uuidString));
       goal.setTitle_goal(title);
       goal.setSuccess_count(success_count);
       return goal;
    }
}
