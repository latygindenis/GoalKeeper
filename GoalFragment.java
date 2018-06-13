package criminalintent.android.bignerdranch.com.goalkeeper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by denis on 11.06.2018.
 */

public class GoalFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GoalsAdapter mGoalsAdapter;
    private GoalsLab mGoalsLab = GoalsLab.get(getActivity());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0; i<100; i++){
            Goal goal = new Goal(Integer.toString(i) + "Цель");
            mGoalsLab.addGoal(goal);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_goals, container, false);
        mRecyclerView = v.findViewById(R.id.goals_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private void updateUI() {
        ArrayList<Goal> goals = mGoalsLab.getGoals();

        if (mGoalsAdapter == null){
            mGoalsAdapter = new GoalsAdapter(goals);
            mRecyclerView.setAdapter(mGoalsAdapter);
        }
    }

    private class GoalsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Goal goal;
        CardView goalCard;
        TextView titleOfGoal;


        public GoalsHolder(View itemView){
            super(itemView);
            goalCard = itemView.findViewById(R.id.goal_card);
            titleOfGoal = itemView.findViewById(R.id.title_of_goal);
        }
        public void bindGoal(Goal goal){
            titleOfGoal.setText(goal.getTitle_goal());
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class GoalsAdapter extends RecyclerView.Adapter<GoalsHolder>{
        private ArrayList<Goal> goals;

        public GoalsAdapter (ArrayList<Goal> goals){
            this.goals = goals;
        }

        @Override
        public GoalsHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Создается холдер по макету
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_goal, parent, false);
            return new GoalsHolder(view);
        }

        @Override
        public void onBindViewHolder(GoalsHolder holder, int position) { //Представление связывается с моделью
            Goal goal = goals.get(position);
            holder.bindGoal(goal);
        }

        @Override
        public int getItemCount() { //Обязательно нужно перегружать
            return goals.size();
        }
    }
}
