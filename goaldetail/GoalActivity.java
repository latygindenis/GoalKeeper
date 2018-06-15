package goalkeeper.android.bignerdranch.com.goalkeeper.goaldetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalActivity extends AppCompatActivity{
    GoalDetailFragment goalDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = getSupportFragmentManager();
        if (goalDetailFragment == null){
            goalDetailFragment = new GoalDetailFragment();
            fm.beginTransaction().add(R.id.emptyFragment, goalDetailFragment).commit();
        }
    }
}
