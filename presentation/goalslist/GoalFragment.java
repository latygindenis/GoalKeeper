package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goalslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.GoalsLab;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.addeditgoal.AddEditGoalActivity;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalActivity;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goalslist.recycler.GoalsAdapter;

/**
 * Created by denis on 11.06.2018.
 */

public class GoalFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GoalsAdapter mGoalsAdapter;
    private FloatingActionButton addGoalButton;
    private GoalsLab mGoalsLab;
    private static final String ARG_GOAL_ID = "goal_id";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_goals, container, false);
        mGoalsLab = GoalsLab.get(getActivity());
        mRecyclerView = v.findViewById(R.id.goals_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addGoalButton = v.findViewById(R.id.fab);
        addGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditGoalActivity.class);
                startActivity(intent);
            }
        });
        updateUI();
        return v;
    }

    private void updateUI() {
        ArrayList<Goal> goals = mGoalsLab.getGoals();
        if (mGoalsAdapter == null){
            mGoalsAdapter = new GoalsAdapter(goals, getActivity());
            mRecyclerView.setAdapter(mGoalsAdapter);
        }
    }
}
