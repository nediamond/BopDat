package iobopdat.github.nediamond.bopdat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import java.util.Random;


/**
 * Created by zach_meyer on 5/26/17.
 */

public abstract class BaseMoveActivity extends Activity  implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor mSensor;
    TextToSpeech t1;
    private static final String[] BG_COLOR_CHOICES = {"#232528", "#eaf6ff"};
    private static final String[] TXT_COLOR_CHOICES = {"#542cc1", "#ffa400", "#009ffd"};

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

        Random rand = new Random();
        int  bg_col = Color.parseColor(BG_COLOR_CHOICES[rand.nextInt(BG_COLOR_CHOICES.length)]);
        int  txt_col = Color.parseColor(TXT_COLOR_CHOICES[rand.nextInt(TXT_COLOR_CHOICES.length)]);

        ((TextView) findViewById(R.id.commandDisplay)).setTextColor(txt_col);
        findViewById(R.id.gameLayout).setBackgroundColor(bg_col);

        // Because this class is used for Accel. based moves, it should fail if screen is touched
        final BaseMoveActivity that = this;
        ConstraintLayout gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        gameLayout.setOnTouchListener(
                new ConstraintLayout.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent event) {
                        setResult(Activity.RESULT_CANCELED);
                        that.mSensorManager.unregisterListener(that);
                        finish();
                        return true;
                    }
                }
        );

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
