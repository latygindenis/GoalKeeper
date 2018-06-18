package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.addeditgoal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.GoalsLab;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalDetailActivity;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalDetailFragment;

/**
 * Created by denis on 16.06.2018.
 */

public class AddEditGoalFragment extends Fragment {
    private static final String ARG_GOAL_ID = "goal_id";


    EditText goalTitle;
    Button addGoal;
    EditText periodEditText;
    Goal goal;
    UUID uuid;
    boolean newGoal = false;

    public static AddEditGoalFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_GOAL_ID, uuid);
        AddEditGoalFragment fragment = new AddEditGoalFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uuid = (UUID)getArguments().getSerializable(ARG_GOAL_ID);
        if(GoalsLab.get(getContext()).getGoal(uuid)!=null){
            goal = GoalsLab.get(getContext()).getGoal(uuid);
        }
        else {
            newGoal = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_goal, container, false);


        goalTitle = v.findViewById(R.id.goal_title);
        goalTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                goal.setTitle_goal(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        periodEditText = v.findViewById(R.id.periodEditText);
        periodEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addGoal = v.findViewById(R.id.addGoalButton);
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newGoal){
                    GoalsLab.get(getActivity()).addGoal(goal);
                    Intent intent = GoalDetailActivity.newIntent(getActivity(), goal.getUuid());
                    startActivity(intent);
                } else {
                    GoalsLab.get(getActivity()).updateGoal(goal);
                    Intent intent = GoalDetailActivity.newIntent(getActivity(), goal.getUuid());
                    startActivity(intent);
                }

            }
        });
        if (newGoal){
            goal = new Goal(UUID.randomUUID());
        } else {
            goalTitle.setText(goal.getTitle_goal());
            addGoal.setText("Сохранить изменения");
        }

        return v;
    }

}
