package com.example.android.sixsigma.nightmare;

import android.media.Ringtone;

/**
 * Created by Jesse Offei-Nkansah on 11/13/2016.
 */
public class Alarm {

    private String name;
    private int hour;
    private int minute;
    private Ringtone tone;
    private boolean[] repeatDays;
    private boolean active;

    public Alarm(){
        this("");
    }

    public Alarm(int hour, int minute){
        this("", hour, minute);
    }

    public Alarm(String name){
        this(name, 0, 0);
    }

    public Alarm(String name, int hour, int minute){
        this(name, hour, minute, true);
    }

    public Alarm(String name, int hour, int minute, boolean active){
        this(name, hour, minute, null, null, active);
        repeatDays = new boolean[7];
        for(int i = 0; i < 7; ++i){
            repeatDays[i] = false;
        }
    }

    public Alarm(String name, int hour, int minute, Ringtone tone, boolean[] repeat){
        this(name, hour, minute, tone, repeat, true);
    }

    public Alarm(String name, int hour, int minute, Ringtone tone, boolean[] repeatDays, boolean active){
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.tone = tone;
        this.repeatDays = repeatDays;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public Ringtone getTone() {
        return tone;
    }

    public boolean[] getRepeatDays() {
        return repeatDays;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setTone(Ringtone tone) {
        this.tone = tone;
    }

    public void setRepeatDays(boolean[] repeatDays) {
        this.repeatDays = repeatDays;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
