package criminalintent.android.bignerdranch.com.goalkeeper.goalslist.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import criminalintent.android.bignerdranch.com.goalkeeper.R;
import criminalintent.android.bignerdranch.com.goalkeeper.data.Goal;
import criminalintent.android.bignerdranch.com.goalkeeper.goalslist.GoalFragment;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalsAdapter extends RecyclerView.Adapter<GoalsHolder>{
    private ArrayList<Goal> goals;

    public GoalsAdapter (ArrayList<Goal> goals){
        this.goals = goals;
    }

    @Override
    public GoalsHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Создается холдер по макету
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.list_item_goal, parent, false);
        return new GoalsHolder(view);
    }

    @Override
    public void onBindViewHolder(GoalsHolder holder, int position) { //Представление связывается с моделью
        final Goal goal = goals.get(position);
        holder.titleOfGoal.setText(goal.getTitle_goal());
        holder.progressText.setText(goal.getSucsess_count() + "/66");
        holder.progressBar.setMax(66);
        holder.progressBar.setProgress(goal.getSucsess_count());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal.setSucsess_count(goal.getSucsess_count() + 1);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() { //Обязательно нужно перегружать
        return goals.size();
    }

}