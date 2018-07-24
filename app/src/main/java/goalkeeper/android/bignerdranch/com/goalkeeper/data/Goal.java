package goalkeeper.android.bignerdranch.com.goalkeeper.data;


import java.util.UUID;

/**
 * Created by denis on 13.06.2018.
 */

public class Goal {
    private UUID uuid;
    private String title_goal="";
    private int hourNotif;



    private int minNotif;
    private int enableNotif = 0;
    private Statistics statistics = new Statistics();

    int period;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Goal(String title_goal) {
        this.title_goal = title_goal;
        uuid = UUID.randomUUID();
        period = 1;
    }

    public Goal(UUID uuid) {
        this.uuid = uuid;

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

    public Statistics getStatistics() {
        return statistics;
    }

    public int getHourNotif() {
        return hourNotif;
    }

    public void setHourNotif(int hourNotif) {
        this.hourNotif = hourNotif;
    }

    public int getMinNotif() {
        return minNotif;
    }

    public void setMinNotif(int minNotif) {
        this.minNotif = minNotif;
    }

    public int isEneableNotif() {
        return enableNotif;
    }

    public void setEnableNotif(int enableNotif) {
        this.enableNotif = enableNotif;
    }

    public int getEnableNotif() {
        return enableNotif;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
