package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import android.os.Build;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by denis on 18.06.2018.
 */

public class Statistics {

    public Statistics() {

    }

    public List<CalendarDay> getSuccess_dates() {
        return success_dates;
    }

    public void setSuccess_dates(List<CalendarDay> success_dates) {
        this.success_dates = success_dates;
    }

    private List<CalendarDay> success_dates;
    private List<CalendarDay> sortable_list;

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public Date getToday_date() {
        return today_date;
    }

    public void setToday_date(Date today_date) {
        this.today_date = today_date;
    }

    private int total_amount;
    private int current_streak;
    private int max_streak;
    private int numbers_of_attempt;

    public boolean isTodayChecked() {
        return TodayChecked;
    }

    boolean TodayChecked = false;
    private Date today_date;

    public Statistics(List<CalendarDay> success_dates){
        this.success_dates = success_dates;
        updateStatistics();
    }

    public int getCurrent_streak() {
        return current_streak;
    }

    public void setCurrent_streak(int current_streak) {
        this.current_streak = current_streak;
    }

    public int getMax_streak() {
        return max_streak;
    }

    public void setMax_streak(int max_streak) {
        this.max_streak = max_streak;
    }

    public int getNumbers_of_attempt() {
        return numbers_of_attempt;
    }

    public void setNumbers_of_attempt(int numbers_of_attempt) {
        this.numbers_of_attempt = numbers_of_attempt;
    }

    public void updateStatistics(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        total_amount = success_dates.size();
        numbers_of_attempt = 1;
        current_streak = 0;
        max_streak = 0;
        if (success_dates.size()!= 0){
            CalendarDateComparator calendarDateComparator =new CalendarDateComparator();
            sortable_list = new ArrayList<CalendarDay> (success_dates);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sortable_list.sort(calendarDateComparator);
            }
            check_streak();

            TodayChecked = sortable_list.get(sortable_list.size() - 1).getDay() == calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            TodayChecked = false;
        }
    }

    private void check_streak(){
        today_date = new Date();
            Date date_buf = sortable_list.get(0).getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date_buf);
            CalendarDay buffer;
            int buffer_streak=0;
            for(CalendarDay calendarDay:sortable_list){

                if (cal.get(Calendar.DAY_OF_MONTH) == calendarDay.getCalendar().get(Calendar.DAY_OF_MONTH)){
                    buffer_streak++;
                } else if (buffer_streak !=0){
                    numbers_of_attempt ++;
                    buffer_streak =1;
                }
                if (buffer_streak > max_streak){
                    max_streak = buffer_streak;
                }

                buffer = CalendarDay.from(calendarDay.getDate());

                cal.setTime(buffer.getDate());
                cal.add(Calendar.DAY_OF_MONTH, 1);

            }

            cal.setTime(new Date());
            if (cal.get(Calendar.DAY_OF_MONTH)- sortable_list.get(sortable_list.size()-1).getCalendar().get(Calendar.DAY_OF_MONTH)  <=1 ){
                current_streak = buffer_streak;
                if (buffer_streak == 0){
                    current_streak = 1;
                }
            } else {
                current_streak = 0;
            }

    }

}
