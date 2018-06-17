package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.addeditgoal;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.MainActivity;

/**
 * Created by denis on 16.06.2018.
 */

public class AddEditGoalActivity extends AppCompatActivity {

    AddEditGoalFragment addEditGoalFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        FragmentManager fm = getSupportFragmentManager();
        if (addEditGoalFragment == null){
            addEditGoalFragment = new AddEditGoalFragment();
            fm.beginTransaction().add(R.id.emptyFragment, addEditGoalFragment).commit();
        }
    }

}
