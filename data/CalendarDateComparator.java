package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Comparator;

/**
 * Created by denis on 22.06.2018.
 */

public class CalendarDateComparator implements Comparator<CalendarDay>{

    @Override
    public int compare(CalendarDay o1, CalendarDay o2) {
        return o1.hashCode() - o2.hashCode();
    }
}
