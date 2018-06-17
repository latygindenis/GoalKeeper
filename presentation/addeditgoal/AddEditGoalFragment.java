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
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalActivity;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalDetailFragment;

/**
 * Created by denis on 16.06.2018.
 */

public class AddEditGoalFragment extends Fragment {
    EditText goalTitle;
    Button addGoal;
    EditText periodEditText;
    Goal goal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_goal, container, false);
        goal = new Goal(UUID.randomUUID());
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
                GoalsLab.get(getActivity()).addGoal(goal);
                Intent intent = GoalActivity.newIntent(getActivity(), goal.getUuid());
                startActivity(intent);
            }
        });

        return v;
    }
}
