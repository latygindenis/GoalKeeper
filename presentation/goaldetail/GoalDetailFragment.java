package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.GoalsLab;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.database.GoalsDbSchema;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalDetailFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";

    MaterialCalendarView calendarView;
    Goal goal;
    TextView title;

    public static GoalDetailFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, uuid);
        GoalDetailFragment fragment = new GoalDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        goal = GoalsLab.get(getActivity()).getGoal(uuid);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_goal, container, false);
        calendarView = v.findViewById(R.id.calendarView);
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

        for (int i=0; i<goal.getSuccess_date().size(); i++){
            calendarView.setDateSelected(CalendarDay.from(goal.getSuccess_date().get(i)), true);
            Log.i(CalendarDay.from(goal.getSuccess_date().get(i)).toString(), "");
        }

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                List<Date> dates = new ArrayList<>();
                List<CalendarDay> calendarDays =  calendarView.getSelectedDates();

                for (int i=0; i<calendarDays.size(); i++){
                    dates.add(calendarDays.get(i).getDate());
                }
                goal.setSuccess_date( dates);
                GoalsLab.get(getActivity()).updateGoal(goal);
            }
        });
        title = v.findViewById(R.id.title_detail);
        title.setText(goal.getTitle_goal());
        return v;
    }
}
