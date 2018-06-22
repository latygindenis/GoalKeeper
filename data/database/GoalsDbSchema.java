package goalkeeper.android.bignerdranch.com.goalkeeper.data.database;

/**
 * Created by denis on 15.06.2018.
 */

public class GoalsDbSchema {
    public static final class GoalsTable {
        public static final String NAME = "goals";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String SUCCESS_DATES = "success_dates";
        }

    }
}
