package com.example.fitnessapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.fitnessapp.Services.AlarmReceiver;
import com.example.fitnessapp.Services.StepCountingService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeScreen extends Fragment{

    private TextView todayCountTxt,intakeTxt;
    private Switch enableSteps,enableNotify;
    private static final String TAG = "HomeScreen";
    private SharedPreferencesManager prefs;
    String countedStep;
    String DetectedStep;
    private Intent intent;
    String currentTime;
    boolean isServiceStopped;
    private Calendar mCalendar;
    private int mHour, mMinute;
    private String mTime;
    private String mRepeat = "false";
    private String mActive = "true";
    private int age;
    private String gender;
    private long mRepeatTime;
    private int ID;
    private String steps;
    private boolean isNotify,isSteps;
    private int height,weight;
    private static final int PHYSICAL_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_screen,container,false);
        todayCountTxt = (TextView)root.findViewById(R.id.today_steps);
        intakeTxt = (TextView)root.findViewById(R.id.intake);
        enableSteps = (Switch) root.findViewById(R.id.step_count);
        enableNotify = (Switch) root.findViewById(R.id.water_intake);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        prefs = new SharedPreferencesManager(getActivity());
        intent = new Intent(getActivity(), StepCountingService.class);
        age = prefs.retrieveInt("age",0);
        gender = prefs.retrieveString("gender","");
        weight = prefs.retrieveInt("weight",0);
        height = prefs.retrieveInt("height",0);
        ID = (int) System.currentTimeMillis();

        //Turn On And Off Step Count
        enableSteps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.ACTIVITY_RECOGNITION) ==
                                PackageManager.PERMISSION_DENIED) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, PHYSICAL_CODE);
                        }else{
                            prefs.storeBoolean("steps_enable",true);
                            viewInit();
                            Toast.makeText(getActivity(), "Permission already granted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        prefs.storeBoolean("steps_enable",true);
                        viewInit();
                    }
                }else {
                    prefs.storeBoolean("steps_enable",false);
                }
            }
        });
        //Turn On And Off Notification
        enableNotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    prefs.storeBoolean("notify",true);
                    storeAlarm();
                }else{
                    prefs.storeBoolean("notify",false);
                    int rId = prefs.retrieveInt("rid",0);
                    disableAlarm(rId);
                }
            }
        });
        isNotify = prefs.retrieveBoolean("notify",false);
        isSteps = prefs.retrieveBoolean("steps_enable",false);

        todayCountTxt.setText("0");
        calculateWaterIntake();
        return root;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            if (requestCode == PHYSICAL_CODE) {
                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    prefs.storeBoolean("steps_enable", true);
                    viewInit();
                } else {
                    Toast.makeText(getActivity(), " Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //turn Off Notifications
    private void disableAlarm(int rId) {
        prefs.storeString("repeat","false");
        prefs.storeString("active","true");
        NotificationManagerCompat.from(getActivity()).cancel(rId);
        NotificationManagerCompat.from(getActivity()).cancelAll();
        new AlarmReceiver().cancelAlarm(getActivity(),rId);
    }


    private void viewInit() {
            isServiceStopped = true; //Current Service state
            String msg = "Step Counter Started";
            getActivity().startService(new Intent(getActivity(), StepCountingService.class));
            getActivity().registerReceiver(broadcastReceiver, new IntentFilter(StepCountingService.BROADCAST_ACTION));
            isServiceStopped = false;
    }

    // BroadcastReceiver to receive the message from the Step Detector Service
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateViews(intent);
        }
    };

    //Update Step Counter
    private void updateViews(Intent intent) {
        countedStep = intent.getStringExtra("Counted_Step");
       // DetectedStep = intent.getStringExtra("Detected_Step");
        Log.d(TAG, String.valueOf(countedStep));
        Log.d(TAG, String.valueOf(DetectedStep));
        currentTime = intent.getStringExtra("time");
        if (todays().equals(currentTime)) {
            steps = prefs.retrieveString("counted","0");
            todayCountTxt.setText(steps);
        }

    }
    private String todays() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(Calendar.getInstance().getTime());
    }


    private void calculateWaterIntake() {

        if (age > 3 && age < 9){
            intakeTxt.setText("5 cups, or 40 oz.");
        }
        if (age > 8 && age < 14){
            intakeTxt.setText("7–8 cups, or 56–64 oz.");
        }
        if (age > 13 && age < 19){
            intakeTxt.setText("8–11 cups, or 64–88 oz.");
        }
        if (gender.equals("Male") && age > 18){
            intakeTxt.setText("13 cups, or 104 oz.");
        }
        if (gender.equals("Female") && age > 18){
            intakeTxt.setText("women 9 cups, or 72 oz.");
        }
    }

    //Save Notification Details
    private void storeAlarm() {

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mTime = mHour + ":" + mMinute;

        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);
        mRepeat = "true";
        prefs.storeInt("rid",ID);
        // preferenceManager.putString("date",mDate);
        prefs.storeString("time",mTime);
        prefs.storeString("repeat",mRepeat);
        prefs.storeString("active",mActive);

        mRepeatTime = 3600 * 1000 * 2;
       // mRepeatTime = 2*60*1000;
        if (mRepeat.equals("true")) {
            new AlarmReceiver().setAlarmRepeat(getActivity(), mCalendar, ID, mRepeatTime,"true");
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();
        //if (enableSteps.isChecked()) {
        isNotify = prefs.retrieveBoolean("notify",false);
        isSteps = prefs.retrieveBoolean("steps_enable",false);
        if (isSteps) {
            enableSteps.setChecked(true);
            enableSteps.setEnabled(true);
            intent = new Intent(getActivity(), StepCountingService.class);
            viewInit();
        }

        if (isNotify){
            enableNotify.setChecked(true);
            enableNotify.setEnabled(true);
            storeAlarm();
        }

    }


}
