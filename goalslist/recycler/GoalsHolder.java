package criminalintent.android.bignerdranch.com.goalkeeper.goalslist.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import criminalintent.android.bignerdranch.com.goalkeeper.R;
import criminalintent.android.bignerdranch.com.goalkeeper.data.Goal;

/**
 * Created by denis on 14.06.2018.
 */

public class GoalsHolder extends RecyclerView.ViewHolder{
    CardView goalCard;
    TextView titleOfGoal;
    ProgressBar progressBar;
    TextView progressText;
    Button button;


    public GoalsHolder(View itemView){
        super(itemView);
        goalCard = itemView.findViewById(R.id.goal_card);
        titleOfGoal = itemView.findViewById(R.id.title_of_goal);
        progressBar = itemView.findViewById(R.id.progressBar);
        progressText = itemView.findViewById(R.id.progresText);
        button = itemView.findViewById(R.id.button);
    }
}