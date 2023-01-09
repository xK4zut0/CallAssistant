package com.example.callassistant;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class CallReceiver extends BroadcastReceiver {
    private TextToSpeech mTTS;
    private AudioManager audioManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            //audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
        else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)){
        }
        else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = mTTS.setLanguage(Locale.GERMAN);
                        speak("1 2 3 4 5 6");
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        } else {
                        }
                    } else {
                        showToast(context, "something went wrong");
                    }
                }
            });
        }
    }

    void showToast(Context context,String message){
        Toast toast=Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private void speak(String msg) {
        String text = msg;
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
