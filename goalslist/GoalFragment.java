package criminalintent.android.bignerdranch.com.goalkeeper.goalslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import criminalintent.android.bignerdranch.com.goalkeeper.R;
import criminalintent.android.bignerdranch.com.goalkeeper.data.Goal;
import criminalintent.android.bignerdranch.com.goalkeeper.data.GoalsLab;
import criminalintent.android.bignerdranch.com.goalkeeper.goalslist.recycler.GoalsAdapter;

/**
 * Created by denis on 11.06.2018.
 */

public class GoalFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GoalsAdapter mGoalsAdapter;
    private GoalsLab mGoalsLab = GoalsLab.get(getActivity());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0; i<100; i++){
            Goal goal = new Goal(Integer.toString(i) + "Цель");
            mGoalsLab.addGoal(goal);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_goals, container, false);
        mRecyclerView = v.findViewById(R.id.goals_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private void updateUI() {
        ArrayList<Goal> goals = mGoalsLab.getGoals();
        if (mGoalsAdapter == null){
            mGoalsAdapter = new GoalsAdapter(goals);
            mRecyclerView.setAdapter(mGoalsAdapter);
        }
    }
}
