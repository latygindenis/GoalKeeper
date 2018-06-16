package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by denis on 13.06.2018.
 */

public class GoalsLab{

    private static GoalsLab sGoalsLab;
    private ArrayList<Goal> mGoalArrayList;

    public static GoalsLab get(Context context){
        if(sGoalsLab == null){
            sGoalsLab = new GoalsLab(context);
        }
        return sGoalsLab;
    }

    private GoalsLab(Context context) {
        mGoalArrayList = new ArrayList<>();

    }
    public void addGoal(Goal goal){
        mGoalArrayList.add(goal);
    }

    public Goal getGoal(UUID uuid){
        for (int i=0; i<mGoalArrayList.size(); i++) {
            if (mGoalArrayList.get(i).getUuid().equals(uuid)){
                return mGoalArrayList.get(i);
            }
        }
        return null;
    }

    public void delGoal(UUID uuid){
        for (int i=0; i<mGoalArrayList.size(); i++) {
            if (mGoalArrayList.get(i).getUuid().equals(uuid)){
               mGoalArrayList.remove(i);
            }
        }
    }

    public ArrayList<Goal> getGoals() {
        return mGoalArrayList;
    }
}

