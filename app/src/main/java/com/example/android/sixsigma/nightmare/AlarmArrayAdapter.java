package com.example.android.sixsigma.nightmare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jesse Offei-Nkansah on 11/13/2016.
 */
public class AlarmArrayAdapter extends ArrayAdapter<Alarm> {

    AlarmArrayAdapter(Context context, ArrayList<Alarm> alarms){
        super(context, R.layout.custom_alarm_item, alarms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Alarm alarm = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_alarm_item, parent, false);
        }

        ImageView sound = (ImageView) convertView.findViewById(R.id.sound_icon);
        TextView alarmName = (TextView) convertView.findViewById(R.id.alarm_name_text_view);
        TextView alarmTime = (TextView) convertView.findViewById(R.id.alarm_time_text_view);
        TextView repeatDays = (TextView) convertView.findViewById(R.id.repeat_days_text_view);

        if(alarm.isActive()){
            sound.setImageResource(R.drawable.ic_volume_up_black_24dp);
        }else{
            sound.setImageResource(R.drawable.ic_volume_off_black_24dp);
        }

        alarmName.setText(alarm.getName());
        alarmTime.setText(alarm.getHour() + ":" + alarm.getMinute());
        String repeats = "";

        if(alarm.getRepeatDays()[0]){
            repeats += "/S";
        }
        if(alarm.getRepeatDays()[1]){
            repeats += "/M";
        }
        if(alarm.getRepeatDays()[2]){
            repeats += "/T";
        }
        if(alarm.getRepeatDays()[3]){
            repeats += "/W";
        }
        if(alarm.getRepeatDays()[4]){
            repeats += "/T";
        }
        if(alarm.getRepeatDays()[5]){
            repeats += "/F";
        }
        if(alarm.getRepeatDays()[6]){
            repeats += "/S";
        }

        if(repeats.length() > 1){
            repeats = repeats.substring(1);
        }

        repeatDays.setText(repeats);

        return convertView;
    }
}
