package criminalintent.android.bignerdranch.com.goalkeeper;

import java.util.UUID;

/**
 * Created by denis on 13.06.2018.
 */

public class Goal {
    private UUID uuid;
    private String title_goal;
    int sucsess_count;

    public Goal(String title_goal) {
        this.title_goal = title_goal;
        sucsess_count = 0;
        uuid = UUID.randomUUID();
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

    public int getSucsess_count() {
        return sucsess_count;
    }

    public void setSucsess_count(int sucsess_count) {
        this.sucsess_count = sucsess_count;
    }
}
