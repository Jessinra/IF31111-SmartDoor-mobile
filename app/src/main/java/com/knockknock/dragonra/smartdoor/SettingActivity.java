package com.knockknock.dragonra.smartdoor;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

public class SettingActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPreferences";
    SeekBar seekBar;
    Switch aSwitch;
    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(60);

        final SharedPreferences mSetting = getSharedPreferences(PREFS_NAME, 0);
        int brightness = mSetting.getInt("brightness", getBrightness());

        seekBar.setProgress(brightness);
        setBrightness(brightness);
        getPermission();


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && success){
                    setBrightness(progress);
                    SharedPreferences.Editor editor = mSetting.edit();
                    editor.putInt("brightness",progress);
                    editor.apply();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!success){
                    Toast.makeText(SettingActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setBrightness(int brightness){
        if (brightness<0){
            brightness = 0;
        } else if (brightness>60) {
            brightness = 60;
        }

        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);

    }

    private int getBrightness(){
        int brightness = 60;
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
        return brightness;
    }

    private void getPermission(){
        boolean value;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            value = Settings.System.canWrite(getApplicationContext());
            if(value){
                success = true;
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                startActivityForResult(intent, 1000);
            }
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (requestCode==1000){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                boolean value = Settings.System.canWrite(getApplicationContext());
                if (value){
                    success = true;
                } else {
                    Toast.makeText(this, "Permission not granted 2", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
