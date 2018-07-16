package com.disanumber.timer.util

import android.content.Context
import android.preference.PreferenceManager
import com.disanumber.timer.timer.TimerActivity

class PrefUtil {

    companion object {

        private const val TIMER_LENGTH_ID = "com.disanumber.timer.timer_length"//key for preference seekbar
        //LIKE STATIC MEMBERS IN JAVA
        fun getTimerLength(context: Context): Int {//all timer length doesn't change running timer if we change minutes
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)//get preferences by context
            return preferences.getInt(TIMER_LENGTH_ID, 10)
         }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "com.disanumber.timer.previous_timer_length"

        fun getPreviousTimerLenghtSeconds(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)//get preferences by context
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)//return previous timer length
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context){//set timer seconds
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.disanumber.timer.timer_state"

        fun getTimerState(context: Context): TimerActivity.TimerState{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)//get preferences by context
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)//enum states by default is INT, 0 - is Stopped Timer
            return  TimerActivity.TimerState.values()[ordinal]//index of enum states
        }

        fun setTimerState(state: TimerActivity.TimerState, context: Context){//set timer state
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID = "com.disanumber.timer.previous_timer_length"

        fun getSecondsRemaining(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)//get preferences by context
            return preferences.getLong(SECONDS_REMAINING_ID, 0)//return seconds remaining
        }

        fun setSecondsRemaining(seconds: Long, context: Context){//set seconds remaining
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

        private const val AlARM_SET_TIME_ID = "com.disanumber.timer.background_time"
        fun getAlarmSetTime(context: Context):Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)//get preferences by context
            return preferences.getLong(AlARM_SET_TIME_ID, 0)
        }
        fun setAlarmSetTime(time: Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(AlARM_SET_TIME_ID, time)
            editor.apply()
        }
    }
}