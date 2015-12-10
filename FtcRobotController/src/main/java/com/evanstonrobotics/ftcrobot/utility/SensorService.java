package com.evanstonrobotics.ftcrobot.utility;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * @author Alex
 */
public class SensorService {
    private static SensorManager sensorManager;
    private static SensorEventListener sensorEventListener;
    private static boolean started = false;

    private static float[] accelerometer = new float[3];
    private static float[] gravity = new float[3];

    public static void start(final Context applicationContext) {
        if(started) {
            return;
        }
        sensorManager = (SensorManager)applicationContext.getSystemService(Context.SENSOR_SERVICE);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                int type = event.sensor.getType();
                switch(type) {
                    case Sensor.TYPE_ACCELEROMETER:
                        accelerometer = event.values.clone();
                        break;
                    case Sensor.TYPE_GRAVITY:
                        gravity = event.values.clone();
                        break;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

        started = true;
    }

    public static float[] getAccelerometer() {
        return accelerometer;
    }

    public static float[] getGravity() {
        return gravity;
    }

    public boolean getStarted() {
        return started;
    }

    public static void stop() {
        if(started) {
            sensorManager.unregisterListener(sensorEventListener);
            started = false;
        }
    }
}
