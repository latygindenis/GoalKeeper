package goalkeeper.android.bignerdranch.com.goalkeeper.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import goalkeeper.android.bignerdranch.com.goalkeeper.data.ArrayConvertHelper;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsDbSchema.GoalsTable;

/**
 * Created by denis on 15.06.2018.
 */

public class GoalCursorWrapper extends CursorWrapper{
    public GoalCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Goal getGoal() {
       String uuidString = getString(getColumnIndex(GoalsTable.Cols.UUID));
       String title = getString(getColumnIndex(GoalsTable.Cols.TITLE));

       ArrayConvertHelper arrayConvertHelper = new ArrayConvertHelper();

       List<CalendarDay> dates = arrayConvertHelper.
               fromStringToDateArray(getString(getColumnIndex(GoalsTable.Cols.SUCCESS_DATES)));


       Goal goal = new Goal(UUID.fromString(uuidString));
       goal.setTitle_goal(title);
       goal.getStatistics().setSuccess_dates(dates);
       goal.getStatistics().updateStatistics();
       return goal;
    }
}
