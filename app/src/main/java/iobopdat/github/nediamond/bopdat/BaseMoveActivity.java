package iobopdat.github.nediamond.bopdat;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.view.Window;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import java.util.Locale;


/**
 * Created by zach_meyer on 5/26/17.
 */

public abstract class BaseMoveActivity extends Activity  implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor mSensor;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_game_environment);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        }.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
        this.mSensorManager.unregisterListener(this);
    }
}
