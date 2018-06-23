package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goalslist.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;
import java.util.ArrayList;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.GoalsLab;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalDetailActivity;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalsAdapter extends RecyclerView.Adapter<GoalsHolder>{
    private ArrayList<Goal> goals;
    private Context context;
    private boolean checked = false;

    public GoalsAdapter (ArrayList<Goal> goals, Context cotext){
        this.goals = goals;
        this.context = cotext;
    }

    @Override
    public GoalsHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Создается холдер по макету
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.list_item_goal, parent, false);
        return new GoalsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoalsHolder holder, int position) { //Представление связывается с моделью
        final Goal goal = goals.get(position);
        holder.titleOfGoal.setText(goal.getTitle_goal());
        holder.progressText.setText(goal.getStatistics().getCurrent_streak() + "/66");
        holder.progressBar.setMax(66);
        holder.progressBar.setProgress(goal.getStatistics().getCurrent_streak());
        if(goal.getStatistics().isTodayChecked()){
            holder.button.setText("Отменить");
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!goal.getStatistics().isTodayChecked()){
                    goal.getStatistics().getSuccess_dates().add(new CalendarDay(new Date()));
                    goal.getStatistics().updateStatistics();
                    GoalsLab.get(context).updateGoal(goal);
                    holder.button.setText("Отменить");
                    holder.progressBar.setProgress(goal.getStatistics().getCurrent_streak());
                    notifyDataSetChanged();
                } else {
                    goal.getStatistics().getSuccess_dates().remove(goal.getStatistics().getSuccess_dates().size() - 1);
                    goal.getStatistics().updateStatistics();
                    GoalsLab.get(context).updateGoal(goal);
                    holder.button.setText("Отметиться");
                    holder.progressBar.setProgress(goal.getStatistics().getCurrent_streak());
                    notifyDataSetChanged();
                }

            }
        });
        holder.goalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GoalDetailActivity.newIntent(context, goal.getUuid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //Обязательно нужно перегружать
        return goals.size();
    }

}