package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import com.prolificinteractive.materialcalendarview.CalendarDay;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by denis on 13.06.2018.
 */

public class Goal {
    private UUID uuid;
    private String title_goal="";
    private List<CalendarDay> success_date;
    int success_count;
    int period;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Goal(String title_goal) {
        this.title_goal = title_goal;
        success_count = 0;
        uuid = UUID.randomUUID();
        period = 1;
        success_date =new ArrayList<>();
    }

    public void setSuccess_date(List<CalendarDay> success_date) {
        this.success_date = success_date;
    }

    public List<CalendarDay> getSuccess_date() {

        return success_date;
    }

    public Goal(UUID uuid) {
        this.uuid = uuid;
        success_date =new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;

    }

    public String getTitle_goal() {
        return title_goal;
    }

    public void setTitle_goal(String title_goal) {
        this.title_goal = title_goal;
    }

    public int getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(int success_count) {
        this.success_count = success_count;
    }
}
