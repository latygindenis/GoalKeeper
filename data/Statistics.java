package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import com.prolificinteractive.materialcalendarview.CalendarDay;

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
    private int total_amount;
    private int current_streak;
    private int max_streak;
    private int numbers_of_attempt;
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
        total_amount = success_dates.size();
        check_streak();
    }

    private void check_streak(){
        today_date = new Date();
        Date date_buf = success_dates.get(0).getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_buf);
        CalendarDay buffer = success_dates.get(0);
        int buffer_streak=0;
        for(CalendarDay calendarDay:success_dates){

            if (Math.abs(cal.getTime().getTime()-calendarDay.getDate().getTime()) < 43200  ){
                buffer_streak++;
            } else {
                buffer_streak = 0;
            }
            if (buffer_streak > max_streak){
                max_streak = buffer_streak;
            }
            buffer = calendarDay;
            cal.setTime(buffer.getDate());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        if (Math.abs(today_date.getTime() - success_dates.get(success_dates.size()).getDate().getTime()) < 43200){
            current_streak = buffer_streak;
            if (buffer_streak == 0){
                current_streak = 1;
            }
        } else {
            current_streak = 0;
        }
    }

}
