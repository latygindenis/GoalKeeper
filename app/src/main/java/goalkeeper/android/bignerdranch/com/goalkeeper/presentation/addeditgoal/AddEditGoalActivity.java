package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.addeditgoal;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.MainActivity;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalDetailActivity;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalDetailFragment;

/**
 * Created by denis on 16.06.2018.
 */

public class AddEditGoalActivity extends AppCompatActivity {

    private static final String EXTRA_GOAL_ID = "goal_id";
    private UUID uuid;
    public static Intent newIntent(Context packageContext, UUID uuid){
        Intent intent = new Intent(packageContext, AddEditGoalActivity.class);
        intent.putExtra(EXTRA_GOAL_ID, uuid);
        return intent;
    }

    AddEditGoalFragment addEditGoalFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        uuid = (UUID)getIntent().getSerializableExtra(EXTRA_GOAL_ID) ;
        FragmentManager fm = getSupportFragmentManager();
        getSupportActionBar().setTitle("Редактирование цели");
        if (addEditGoalFragment == null){
            addEditGoalFragment = AddEditGoalFragment.newInstance(uuid);
            fm.beginTransaction().add(R.id.emptyFragment, addEditGoalFragment).commit();
        }
    }

}
