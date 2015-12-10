package com.evanstonrobotics.ftcrobot.utility;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 */
public class SensorService {
    private static SensorManager sensorManager;
    private static SensorEventListener sensorEventListener;
    private static boolean started = false;

    private static float[] accelerometer = new float[3];

    // there's a warning here about unchecked types, but it doesn't matter because we know what's
    // going into this list
    @SuppressWarnings("unchecked")
    private static List<Float>[] rollingAverage = new List[3];
    private static final int MAX_SAMPLE_SIZE = 5;

    public static void start(final Context applicationContext) {
        if(started) {
            return;
        }
        // using the more verbose List<E> list = new ArrayList<E>() because the compiler yelled at
        // me last time I omitted the second type (pre-java 1.6 compatibility probably)
        rollingAverage[0] = new ArrayList<Float>();
        rollingAverage[1] = new ArrayList<Float>();
        rollingAverage[2] = new ArrayList<Float>();

        sensorManager = (SensorManager)applicationContext.getSystemService(Context.SENSOR_SERVICE);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                int type = event.sensor.getType();
                switch(type) {
                    case Sensor.TYPE_ACCELEROMETER:
                        updateAccelerometer(event.values);
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

    private static void updateAccelerometer(float[] vals) {
        rollingAverage[0] = roll(rollingAverage[0], vals[0]);
        rollingAverage[1] = roll(rollingAverage[1], vals[1]);
        rollingAverage[2] = roll(rollingAverage[2], -vals[2]);

        accelerometer[0] = averageList(rollingAverage[0]);
        accelerometer[1] = averageList(rollingAverage[1]);
        accelerometer[2] = averageList(rollingAverage[2]);
    }

    private static List<Float> roll(List<Float> list, float newMember) {
        if(list.size() == MAX_SAMPLE_SIZE) {
            list.remove(0);
        }
        list.add(newMember);
        return list;
    }

    private static float averageList(List<Float> list) {
        float total = 0;
        for(float f : list) {
            total += f;
        }

        return total / list.size();
    }

    public static float[] getAccelerometer() {
        return accelerometer;
    }

    public static boolean getStarted() {
        return started;
    }

    public static void stop() {
        if(started) {
            sensorManager.unregisterListener(sensorEventListener);
            started = false;
        }
    }
}
