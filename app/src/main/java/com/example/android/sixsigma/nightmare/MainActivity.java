package com.example.android.sixsigma.nightmare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static Button addAlarmButton;
    private static ImageView active;
    private static ListView alarmsList;
    private static ArrayList<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        active = (ImageView) findViewById(R.id.sound_icon);
        alarmsList = (ListView)findViewById(R.id.alarms_list);
        boolean[] g1 = {false,false,true,false,true,true,false};
        boolean[] g2 = {true,false,true,false,true,false,true};
        boolean[] g3 = {false,false,false,false,false,true,false};
        boolean[] g4 = {false,false,false,false,false,false,false};

        Uri curToneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_ALARM);

        Ringtone ringtone = RingtoneManager.getRingtone(MainActivity.this, curToneUri);

        alarms = new ArrayList<Alarm>();

        Alarm alarm1 = new Alarm("Wake Up Call", 7, 45, ringtone, g1, true);
        alarms.add(alarm1);
        Alarm alarm2 = new Alarm("Service", 9, 30, ringtone, g2, false);
        alarms.add(alarm2);
        Alarm alarm3 = new Alarm("Friday Class", 13, 15, ringtone, g3, true);
        alarms.add(alarm3);
        Alarm alarm4 = new Alarm("", 8, 00, ringtone, g4, false);
        alarms.add(alarm4);
        Intent returned = new Intent(MainActivity.this, AddAlarmActivity.class);
        Bundle sentData = returned.getBundleExtra("bundle");
        if(sentData != null) {
            Alarm nextAlarm = (Alarm) sentData.getSerializable("newAlarm");
            if (nextAlarm != null) {
                alarms.add(nextAlarm);
            }
        }

        AlarmArrayAdapter adapter = new AlarmArrayAdapter(MainActivity.this, alarms);

        alarmsList.setAdapter(adapter);

        addAlarmButton = (Button) findViewById(R.id.add_alarm_button);
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
                startActivity(intent);
            }
        });

        /*active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(active.getId() == R.mipmap.ic_volume_up_black_24dp){
                    active.setImageResource(R.mipmap.ic_volume_off_black_24dp);
                }else{
                    active.setImageResource(R.mipmap.ic_volume_up_black_24dp);
                }
            }
        });*/
    }
}
