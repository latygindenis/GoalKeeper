package goalkeeper.android.bignerdranch.com.goalkeeper.data;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.UUID;

import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.MainActivity;
import goalkeeper.android.bignerdranch.com.goalkeeper.presentation.goaldetail.GoalDetailActivity;

/**
 * AlarmReceiver handles the broadcast message and generates Notification
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = ((UUID)intent.getSerializableExtra("UUID")).hashCode();
        String title  =  GoalsLab.get(context).getGoal((UUID) intent.getSerializableExtra("UUID")).getTitle_goal();

        //Get notification manager to manage/send notifications


        //Intent to invoke app when click on notification.
        //In this sample, we want to start/launch this sample app when user clicks on notification

        Intent intentToRepeat = GoalDetailActivity.newIntent(context, (UUID) intent.getSerializableExtra("UUID"));
        //set flag to restart/relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Pending intent to handle launch of Activity in intent above
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, notificationId, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);

        //Build notification
        Notification repeatedNotification = buildLocalNotification(context, pendingIntent, title).build();

        //Send local notification
        NotificationHelper.getNotificationManager(context).notify(notificationId, repeatedNotification);
    }

    public NotificationCompat.Builder buildLocalNotification(Context context, PendingIntent pendingIntent, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(android.R.drawable.arrow_up_float)
                        .setContentTitle(title)
                        .setAutoCancel(true);

        return builder;
    }
}