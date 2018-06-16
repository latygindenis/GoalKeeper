package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goalslist.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalActivity;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalsAdapter extends RecyclerView.Adapter<GoalsHolder>{
    private ArrayList<Goal> goals;
    private Context context;

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
        holder.progressText.setText(goal.getSuccess_count() + "/66");
        holder.progressBar.setMax(66);
        holder.progressBar.setProgress(goal.getSuccess_count());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal.setSuccess_count(goal.getSuccess_count() + 1);
                notifyDataSetChanged();
            }
        });
        holder.goalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GoalActivity.newIntent(context, goal.getUuid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //Обязательно нужно перегружать
        return goals.size();
    }

}