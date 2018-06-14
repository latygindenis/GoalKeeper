package criminalintent.android.bignerdranch.com.goalkeeper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import criminalintent.android.bignerdranch.com.goalkeeper.goalslist.GoalFragment;
import criminalintent.android.bignerdranch.com.goalkeeper.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment goalsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragment = new SettingsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    goalsFragment = new GoalFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, goalsFragment).commit();

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fm = getSupportFragmentManager();
        if (goalsFragment  == null) {
            goalsFragment = new GoalFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, goalsFragment)
                    .commit();
        }

    }

}
