package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.GoalsLab;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsDbSchema;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.addeditgoal.AddEditGoalActivity;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalDetailFragment extends Fragment {
    private static final String ARG_GOAL_ID = "goal_id";

    MaterialCalendarView calendarView;
    Goal goal;
    TextView title;
    TextView cur_attempt;
    TextView cur_streak;
    TextView max_streak;
    TextView total;


    public static GoalDetailFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_GOAL_ID, uuid);
        GoalDetailFragment fragment = new GoalDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID)getArguments().getSerializable(ARG_GOAL_ID);
        goal = GoalsLab.get(getActivity()).getGoal(uuid);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_goal, container, false);
        setHasOptionsMenu(true);

        cur_attempt = v.findViewById(R.id.numberOfAttemptTextView);
        cur_streak = v.findViewById(R.id.curStreakTextView);
        max_streak = v.findViewById(R.id.maxStreakTextView);
        total = v.findViewById(R.id.totalDays);

        calendarView = v.findViewById(R.id.calendarView);
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

        for (CalendarDay calendarDay:goal.getStatistics().getSuccess_dates()){
            calendarView.setDateSelected(calendarDay, true);
        }

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (date.getDate().getTime() <= new Date().getTime()){
                    List<CalendarDay> calendarDays = calendarView.getSelectedDates();
                    goal.getStatistics().setSuccess_dates(calendarDays);
                    goal.getStatistics().updateStatistics();
                    cur_attempt.setText(goal.getStatistics().getNumbers_of_attempt() +"");
                    cur_streak.setText(goal.getStatistics().getCurrent_streak()+"");
                    max_streak.setText(goal.getStatistics().getMax_streak()+"");
                    total.setText(goal.getStatistics().getTotal_amount()+"");
                    GoalsLab.get(getActivity()).updateGoal(goal);
                } else{
                    widget.setDateSelected(date, false);
                }

            }
        });
        title = v.findViewById(R.id.title_detail);
        title.setText(goal.getTitle_goal());
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menu_item_edit_goal:
                Intent intent = AddEditGoalActivity.newIntent(getActivity(), goal.getUuid());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
