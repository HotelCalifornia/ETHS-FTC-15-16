package com.evanstonrobotics.ftcrobot.utility

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

import java.util.ArrayList

/**
 * @author Alex Brooke
 * @date 12 Dec, 2015
 *
 * Trying out kotlin because why not
 *
 * This class (well, object) follows the singleton pattern, which means only one instance of the object will ever be
 * created throughout the whole program. It's useful in cases like this, where we might need to access the same sensor
 * from anywhere within the program, and we don't want to go around instantiating new objects all over the place.
 *
 * The same functionality can be achieved in pure java through liberal use of the static keyword. An example of this can
 * be seen in the now deprecated class [com.evanstonrobotics.ftcrobot.utility.SensorServiceOld] (the documentation
 * plugin doesn't believe that it's there, but it is~)
 *
 * To access the singleton instance (for example, to initialise it from the main activity, or to read sensor data) in
 * Java syntax, call:
 *
 * ```SensorService.INSTANCE```
 *
 * From there, you can access its public properties, like [started] or [accelerometer], like this:
 *
 * ```SensorService.INSTANCE.getStarted();
 *    SensorService.INSTANCE.getAccelerometer();
 *    // ETC.```
 *
 * To do the same in Kotlin, you can skip the first step, and simply call
 *
 * ```SensorService.started
 *    SensorService.accelerometer```
 */
object SensorService {
    private var sensorManager:SensorManager? = null
    private var sensorEventListener:SensorEventListener? = null

    var started = false
    var accelerometer = FloatArray(3)

    private var rollingAverage = arrayOfNulls<ArrayList<Float>>(3)
    private val MAX_SAMPLE_SIZE = 5

    /**
     * Start the [SensorService] by initialising properties and registering event listeners
     *
     * @param[applicationContext] The [Context] the within which the
     * [main activity][com.qualcomm.ftcrobotcontroller.FtcRobotController] is running
     */
    fun start(applicationContext:Context) {
        if(started) {
            return
        }

        rollingAverage[0] = ArrayList<Float>()
        rollingAverage[1] = ArrayList<Float>()
        rollingAverage[2] = ArrayList<Float>()

        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorEventListener = object:SensorEventListener {
            override fun onSensorChanged(event:SensorEvent) {
                val type = event.sensor.type
                when (type) {
                    Sensor.TYPE_ACCELEROMETER -> updateAccelerometer(event.values)
                }
            }
            override fun onAccuracyChanged(sensor:Sensor, accuracy:Int) {}
        }
        // The following statement is a bitch //
        sensorManager!!.registerListener(sensorEventListener,
                (sensorManager as SensorManager).getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME)
        // </bitch> //
        started = true
    }

    /**
     * Update the values in [accelerometer] with values that have been nicely smoothed with a low-pass filter (a rolling
     * average).
     *
     * @param[vals] Values obtained from [sensorEventListener]'s onSensorChanged event listener
     */
    private fun updateAccelerometer(vals:FloatArray) {
        rollingAverage[0] = roll(rollingAverage[0]!!, vals[0])
        rollingAverage[1] = roll(rollingAverage[1]!!, vals[1])
        rollingAverage[2] = roll(rollingAverage[2]!!, -vals[2])

        accelerometer[0] = averageList(rollingAverage[0]!!)
        accelerometer[1] = averageList(rollingAverage[1]!!)
        accelerometer[2] = averageList(rollingAverage[2]!!)
    }

    /**
     * Roll values in along in a list, keeping the list a constant length ([MAX_SAMPLE_SIZE]):
     * ```
     * In [0]: list = [1, 3, 5, 7, 9]
     * In [1]: roll(list, 11)
     * Out [1]: [3, 5, 7, 9, 11]
     * ```
     * (Example provided in Python because it's basically sudocode, right?)
     *
     * @param[list] The list to be 'rolled'
     * @param[newMember] The new member that will be added to the list
     * @return The newly rolled list (afaik, this is unnecessary because of the pass-by-reference schema, but whatever)
     */
    private fun roll(list:ArrayList<Float>, newMember:Float):ArrayList<Float> {
        if(list.size == MAX_SAMPLE_SIZE) {
            list.removeAt(0)
        }
        list.add(newMember)
        return list
    }

    /**
     * Average the values in a list
     *
     * @param[list] The list
     * @return The average value of all the elements in [list]
     */
    private fun averageList(list:List<Float>):Float {
        var total = 0f
        for (f in list) {
            total += f
        }
        return total / list.size
    }

    /**
     * Cease the [SensorService] by unregeistering [sensorEventListener] from [sensorManager] and setting [started] to
     * `false`
     */
    fun stop() {
        if(started) {
            sensorManager!!.unregisterListener(sensorEventListener)
            started = false
        }
    }
}