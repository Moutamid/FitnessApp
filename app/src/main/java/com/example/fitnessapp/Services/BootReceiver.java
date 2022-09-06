package com.example.fitnessapp.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import androidx.core.app.NotificationManagerCompat;


import com.example.fitnessapp.SharedPreferencesManager;

import java.util.Calendar;
import java.util.List;


public class BootReceiver extends BroadcastReceiver {

    private String mTitle;
    private String mTime;
    private String mDate;
    private String mActive;
    private String mRepeat;
    //private String[] mDateSplit;
    private String[] mTimeSplit;
    private int mYear, mMonth, mHour, mMinute, mDay, mReceivedID;
    private long mRepeatTime;

    private Calendar mCalendar;
    private AlarmReceiver mAlarmReceiver;

    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private SharedPreferencesManager preferenceManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            mCalendar = Calendar.getInstance();
            mAlarmReceiver = new AlarmReceiver();
            preferenceManager = new SharedPreferencesManager(context);

                mReceivedID = preferenceManager.retrieveInt("rid",0);
                mRepeat = preferenceManager.retrieveString("repeat",null);
               mActive = preferenceManager.retrieveString("active",null);
                mTime = preferenceManager.retrieveString("time",null);

             //   mDateSplit = mDate.split("/");
                mTimeSplit = mTime.split(":");
                mHour = Integer.parseInt(mTimeSplit[0]);
                mMinute = Integer.parseInt(mTimeSplit[1]);
                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
                mCalendar.set(Calendar.MINUTE, mMinute);
                mCalendar.set(Calendar.SECOND, 0);

                // Create a new notification
                if (mActive.equals("true")) {
                    if (mRepeat.equals("true")) {

                        mAlarmReceiver.setAlarmRepeat(context, mCalendar, mReceivedID, mRepeatTime,"true");
                    }
                    else if (mRepeat.equals("false")) {
                        NotificationManagerCompat.from(context).cancel(mReceivedID);
                        NotificationManagerCompat.from(context).cancelAll();
                        mAlarmReceiver.cancelAlarm(context,mReceivedID);
                    }
                }
        }
    }
}