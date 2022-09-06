package com.example.fitnessapp.Services;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;


import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.locks.Lock;

import static android.content.Context.POWER_SERVICE;

import com.example.fitnessapp.MainActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.SharedPreferencesManager;


public class AlarmReceiver extends BroadcastReceiver {
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    String channelId = "channel_id";
    Uri soundUri;
    SharedPreferencesManager prefs;

    @Override
    public void onReceive(final Context context, Intent intent) {

        PowerManager pm = (PowerManager)context. getSystemService(Context.POWER_SERVICE);
        if(!pm.isInteractive()){

            PowerManager.WakeLock workLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                    PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, ":MyFcmListenerService");
            workLock.acquire(60*1L);


            PowerManager.WakeLock workLock2 = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyFcmListenerService");
            workLock2.acquire(60*1L);
        }

        final int mReceivedID = intent.getIntExtra("alarmId",0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId);
        try {
            soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.alarms);
        }catch (Exception e){
            e.printStackTrace();
        }
            Intent notification_intent = new Intent(context, MainActivity.class);
            notification_intent.putExtra("rid", mReceivedID);
            notification_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    notification_intent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setContentTitle("Fitness App")
                    .setContentText("It's time to drink water...")
                    .setOngoing(true)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setFullScreenIntent(pendingIntent,true)
                    .setSound(soundUri, AudioManager.STREAM_ALARM)
                   // .addAction(R.drawable.ic_baseline_check_24,"OK",button_pending_event)
                    .setLights(Color.WHITE,200,3000)
                    .setContentIntent(pendingIntent);

        KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context. KEYGUARD_SERVICE ) ;
        if (keyguardManager.isKeyguardLocked()) builder.setPriority(NotificationCompat. PRIORITY_MIN ) ;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();
            NotificationChannel channel = new NotificationChannel(channelId, "channel name",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("channel description");
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setVibrationPattern( new long []{ 100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400 }) ;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                channel.canBubble();
            }
         //   channel.setSound(Settings.System.DEFAULT_ALARM_ALERT_URI,audioAttributes);
            channel.setSound(soundUri,audioAttributes);
            channel.canShowBadge();
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        assert notificationManager != null;
        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }


    public void setAlarmRepeat(Context context, Calendar calendar, int ID, long RepeatTime,String mRepeat) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extraz
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("alarmId", ID);

        mPendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Calculate notification timein
        Calendar c = Calendar.getInstance();
        prefs = new SharedPreferencesManager(context);
        long triggerAt = calendar.getTimeInMillis() - c.getTimeInMillis();
        if (mRepeat.equals("true")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(), RepeatTime, mPendingIntent);
              //  Toast.makeText(context,"Loaded",Toast.LENGTH_LONG).show();
            } else {
                mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        calendar.getTimeInMillis(), RepeatTime, mPendingIntent);
            }
        }
        PowerManager pm = (PowerManager)context. getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock workLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyFcmListenerService");
        workLock.acquire(60*1L);

        ComponentName receiver = new ComponentName(context, BootReceiver.class);

        context.getPackageManager().setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        workLock.release();
    }

    public void cancelAlarm(Context context, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Cancel Alarm using Reminder ID
        mPendingIntent = PendingIntent.getBroadcast(context, ID, new Intent(context, AlarmReceiver.class),
                0);
        mAlarmManager.cancel(mPendingIntent);

        // Disable alarm
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        //PackageManager pm = context.getPackageManager();
        context.getPackageManager().setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        mPendingIntent.cancel();
    }
}