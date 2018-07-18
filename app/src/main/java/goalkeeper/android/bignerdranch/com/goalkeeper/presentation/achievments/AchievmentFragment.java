package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.achievments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;


import java.util.ArrayList;
import java.util.List;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.GoalsLab;

public class AchievmentFragment extends Fragment {

    BarChart chart;
    GoalsLab goalsLab;
    List<BarEntry> entries = new ArrayList<>();
    List<String> labels = new ArrayList<>();
    int count;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievment, null);
        chart = v.findViewById(R.id.chart);

        for (Goal goal:goalsLab.get(getContext()).getGoals()){
            entries.add(new BarEntry(count++, goal.getStatistics().getCurrent_streak()));
            labels.add(goal.getTitle_goal());
        }
//        BarDataSet dataSet = new BarDataSet(entries, "Текущий прогресс");
//        BarData barData = new BarData( labels, dataSet);
//        chart.setData(barData);
//        chart.animateXY(300, 300);
//        chart.setFitBars(true);
//        chart.invalidate();
        return v;
    }
}
