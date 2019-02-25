package com.knockknock.dragonra.smartdoor.utility;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

public class SignificantMovementSensorHandler implements SensorEventListener {

    private Context sensorContext;
    private SensorManager sensorManager;
    private Sensor significantMovementSensor;
    private SensorEvent lastSensorState;

    public SignificantMovementSensorHandler(Context context) {
        sensorContext = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        significantMovementSensor = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
    }

    public SensorEvent getLastSensorState() {
        return lastSensorState;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO: implement real sensor change
        lastSensorState = event;

        CharSequence text = "Significant movement detected !!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(sensorContext, text, duration);
        toast.show();
    }

    public void register() {
        sensorManager.registerListener(this, significantMovementSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregister() {
        sensorManager.unregisterListener(this);
    }
}
