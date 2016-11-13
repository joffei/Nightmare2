package com.example.android.sixsigma.nightmare;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;

public class AddAlarmActivity extends AppCompatActivity {

    private static EditText alarmName;
    private static TimePicker wakeUpTime;
    private static Button sundayButton;
    private static Button mondayButton;
    private static Button tuesdayButton;
    private static Button wednesdayButton;
    private static Button thursdayButton;
    private static Button fridayButton;
    private static Button saturdayButton;
    private static RelativeLayout ringToneSelectionLayout;
    private static TextView ringToneName;
    private static Ringtone ringtone;
    private static TextView cancel;
    private static TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        alarmName = (EditText) findViewById(R.id.alarm_name_edit_text);
        wakeUpTime = (TimePicker) findViewById(R.id.wake_up_time);
        sundayButton = (Button) findViewById(R.id.sunday_button);
        mondayButton = (Button) findViewById(R.id.monday_button);
        tuesdayButton = (Button) findViewById(R.id.tuesday_button);
        wednesdayButton = (Button) findViewById(R.id.wednesday_button);
        thursdayButton = (Button) findViewById(R.id.thursday_button);
        fridayButton = (Button) findViewById(R.id.friday_button);
        saturdayButton = (Button) findViewById(R.id.saturday_button);
        ringToneSelectionLayout = (RelativeLayout) findViewById(R.id.ring_tone_selection_layout);
        ringToneName = (TextView) findViewById(R.id.ring_tone_name_text_view);
        cancel = (TextView) findViewById(R.id.cancel_text_view);
        save = (TextView) findViewById(R.id.save_text_view);
        final boolean[] days = new boolean[7];
        for(int i = 0; i < 7; ++i){
            days[i] = false;
        }

        wakeUpTime.setCurrentHour(8);
        wakeUpTime.setCurrentMinute(0);

        Ringtone defringtone = RingtoneManager.getRingtone(AddAlarmActivity.this, Settings.System.DEFAULT_RINGTONE_URI);

        Uri curToneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_ALARM);

        ringtone = RingtoneManager.getRingtone(AddAlarmActivity.this, curToneUri);

        ringToneName.setText(defringtone.getTitle(AddAlarmActivity.this));


        sundayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle(sundayButton, days, 0);
            }
        });

        mondayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle(mondayButton, days, 1);
            }
        });

        tuesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle(tuesdayButton, days, 2);
            }
        });

        wednesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle((Button)view, days, 3);
            }
        });

        thursdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle((Button)view, days, 4);
            }
        });

        fridayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle((Button)view, days, 5);
            }
        });

        saturdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle((Button)view, days, 6);
            }
        });

        ringToneSelectionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getRingtone = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                getRingtone.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Choose an Alarm Ringtone");
                getRingtone.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                getRingtone.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                getRingtone.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
                AddAlarmActivity.this.startActivityForResult(getRingtone, 1914);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Alarm alarm = new Alarm();

                alarm.setName(TextUtils.isEmpty(alarmName.getText().toString())? "<No Name>"
                                                            :alarmName.getText().toString());

                alarm.setHour(wakeUpTime.getCurrentHour());

                alarm.setMinute(wakeUpTime.getCurrentMinute());

                alarm.setTone(ringtone);

                alarm.setRepeatDays(days);

                alarm.setActive(true);


                Intent returnIntent = new Intent(AddAlarmActivity.this, MainActivity.class);
                Bundle sendData = new Bundle();
                sendData.putSerializable("newAlarm", (Serializable)alarm);
                returnIntent.putExtra("bundle", sendData);
                startActivity(returnIntent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(AddAlarmActivity.this, MainActivity.class);
                startActivity(returnIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1914){
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            ringtone = RingtoneManager.getRingtone(AddAlarmActivity.this, uri);
            //RingtoneManager.setActualDefaultRingtoneUri(AddAlarmActivity.this, RingtoneManager.TYPE_ALARM, uri);
            ringToneName.setText(ringtone.getTitle(AddAlarmActivity.this));
        }
    }

    private void toggle(Button button, boolean[] days, int index){
        int activeColor = AddAlarmActivity.this.getColor(R.color.activeButton);
        int activeText = AddAlarmActivity.this.getColor(R.color.white);
        int inactiveText = AddAlarmActivity.this.getColor(R.color.black);

        if(days[index] == false){
            button.setTextColor(activeText);
            button.setBackgroundColor(activeColor);
            //button.setElevation((float)0.0);
        }else{
            button.setTextColor(inactiveText);
            button.setBackgroundColor(activeText);
            //button.setElevation((float)5.0);
        }
        days[index] = !(days[index]);
    }
}
