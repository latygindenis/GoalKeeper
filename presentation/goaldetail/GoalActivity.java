package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.MainActivity;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalActivity extends AppCompatActivity{
    private static final String EXTRA_CRIME_ID = "crime_id";
    GoalDetailFragment goalDetailFragment;

    public static Intent newIntent(Context packageContext, UUID uuid){
        Intent intent = new Intent(packageContext, GoalActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, uuid);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UUID uuid = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        FragmentManager fm = getSupportFragmentManager();
        if (goalDetailFragment == null){
            goalDetailFragment = GoalDetailFragment.newInstance(uuid);
            fm.beginTransaction().add(R.id.emptyFragment, goalDetailFragment).commit();
        }
    }
        @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent( getApplicationContext(), MainActivity.class );
        startActivity(intent);
    }
}
