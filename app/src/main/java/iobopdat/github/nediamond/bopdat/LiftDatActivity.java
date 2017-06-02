package iobopdat.github.nediamond.bopdat;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by zach_meyer on 5/26/17.
 */

public class LiftDatActivity extends BaseMoveActivity{
    float[] gravity = new float[3];
    float[] linear_acceleration = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TextView) findViewById(R.id.commandDisplay)).setText("Lift Dat");
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


        if (linear_acceleration[2] < -2.5) {
            //Intent intent = new Intent();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}
