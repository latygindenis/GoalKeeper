package goalkeeper.android.bignerdranch.com.goalkeeper.goaldetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goal, container, false);
        return v;
    }
}
