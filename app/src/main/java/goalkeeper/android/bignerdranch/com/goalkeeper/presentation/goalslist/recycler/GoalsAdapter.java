package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goalslist.recycler;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;
import java.util.ArrayList;
import java.util.TimerTask;

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
    private AlarmManager am;
    Intent resultIntent;
    PendingIntent resultPendingIntent;
    public GoalsAdapter (ArrayList<Goal> goals, Context cotext){
        this.goals = goals;
        this.context = cotext;
    }

    @Override
    public GoalsHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Создается холдер по макету
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.list_item_goal, parent, false);
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        return new GoalsHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoalsHolder holder, int position) { //Представление связывается с моделью
        final Goal goal = goals.get(position);
        holder.titleOfGoal.setText(goal.getTitle_goal());
        holder.progressText.setText(goal.getStatistics().getCurrent_streak() + "/66");
        holder.progressBar.setMax(66);
        holder.progressBar.setProgress(goal.getStatistics().getCurrent_streak());
        int green, red;
        green = holder.button.getResources().getColor(R.color.holo_green);
        red = holder.button.getResources().getColor(R.color.holo_red);
        if(goal.getStatistics().isTodayChecked()){
            holder.button.setBackgroundColor(green);
        } else {
            holder.button.setBackgroundColor(red);
        }
        final Animation animAlpha = AnimationUtils.loadAnimation(context, R.anim.alpha);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                if (!goal.getStatistics().isTodayChecked()){
                    goal.getStatistics().getSuccess_dates().add(new CalendarDay(new Date()));
                    goal.getStatistics().updateStatistics();
                    GoalsLab.get(context).updateGoal(goal);
                    int green =holder.button.getResources().getColor(R.color.holo_green);
                    holder.button.setBackgroundColor(green);
                    holder.progressBar.setProgress(goal.getStatistics().getCurrent_streak());
                    notifyDataSetChanged();
                } else {
                    goal.getStatistics().getSuccess_dates().remove(goal.getStatistics().getSuccess_dates().size() - 1);
                    goal.getStatistics().updateStatistics();
                    GoalsLab.get(context).updateGoal(goal);
                    int red = holder.button.getResources().getColor(R.color.holo_red);
                    holder.button.setBackgroundColor(red);
                    holder.progressBar.setProgress(goal.getStatistics().getCurrent_streak());
                    notifyDataSetChanged();
                    am.cancel(resultPendingIntent);
                }


                NotificationChannel notificationChannel = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = new NotificationChannel("newid", "s", NotificationManager.IMPORTANCE_DEFAULT);
                }
                resultIntent = GoalDetailActivity.newIntent(context, goal.getUuid());
                resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(context, "newid")
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Пора!")
                                    .setContentText("Время выполнить цель: "+ goal.getTitle_goal())
                                      .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);

                    Notification notification = builder.build();

                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(1, notification);

                am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, 5000, resultPendingIntent);



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