package iobopdat.github.nediamond.bopdat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import java.lang.Math;

/**
 * Created by zach_meyer on 5/26/17.
 */

public class MoveActivity extends BaseMoveActivity{
    float[] gravity = new float[3];
    float[] linear_acceleration = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TextView) findViewById(R.id.commandDisplay)).setText("Move Dat");
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        final float alpha = 0.8f;

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];


        ((TextView) findViewById(R.id.commandDisplay1)).setText(Float.toString(linear_acceleration[1]));
        ((TextView) findViewById(R.id.commandDisplay2)).setText(Float.toString(linear_acceleration[2]));

        int thresh = 10;
        if (Math.abs(linear_acceleration[0]) > thresh ||
                Math.abs(linear_acceleration[1]) > thresh ||
                Math.abs(linear_acceleration[2]) > thresh) {
            //Intent intent = new Intent();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}
