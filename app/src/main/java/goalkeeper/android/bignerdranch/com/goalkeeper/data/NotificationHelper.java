package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.UUID;

import static android.content.Context.ALARM_SERVICE;

public class NotificationHelper {
    private static AlarmManager alarmManagerRTC;
    private static PendingIntent alarmIntentRTC;

    public static void scheduleRepeatingRTCNotification(Context context, int hour, int min, UUID uuid, int enable) {
        //get calendar instance to be able to select what time notification should be scheduled
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //Setting time of the day (8am here) when notification will be sent every day (default)

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        Log.i("time", "hour: " + calendar.get(Calendar.HOUR_OF_DAY) + " min: " + calendar.get(Calendar.MINUTE));


        //Setting intent to class where Alarm broadcast message will be handled
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("UUID", uuid);
        //Setting alarm pending intent
        alarmIntentRTC = PendingIntent.getBroadcast(context, uuid.hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //getting instance of AlarmManager service
        alarmManagerRTC = (AlarmManager)context.getSystemService(ALARM_SERVICE);

        //Setting alarm to wake up device every day for clock time.
        //AlarmManager.RTC_WAKEUP is responsible to wake up device for sure, which may not be good practice all the time.
        // Use this when you know what you're doing.
        //Use RTC when you don't need to wake up device, but want to deliver the notification whenever device is woke-up
        //We'll be using RTC.WAKEUP for demo purpose only
        if (enable == 1){
            alarmManagerRTC.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntentRTC);
        } else {
            if (alarmManagerRTC != null) {
                alarmManagerRTC.cancel(alarmIntentRTC);
            }
        }



    }

    public static void cancelAlarmRTC() {
        if (alarmManagerRTC!= null) {
            alarmManagerRTC.cancel(alarmIntentRTC);
        }
    }
    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


}
