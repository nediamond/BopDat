package iobopdat.github.nediamond.bopdat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.lang.Math;
import java.util.Locale;
import java.util.Random;

/**
 * Created by zach_meyer on 5/26/17.
 */

public class TapDatActivity extends Activity{
    TextToSpeech t1;

    // These should really be in an abstract class
    private static final String[] BG_COLOR_CHOICES = {"#232528", "#eaf6ff"};
    private static final String[] TXT_COLOR_CHOICES = {"#542cc1", "#ffa400", "#009ffd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_game_environment);

        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        }.start();
        ((TextView) findViewById(R.id.commandDisplay)).setText("Tap Dat");

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.speak("Tap Dat", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        ConstraintLayout gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        gameLayout.setOnTouchListener(
                new ConstraintLayout.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent event) {
                        setResult(Activity.RESULT_OK);
                        finish();
                        return true;
                    }
                }
        );


        Random rand = new Random();
        int  bg_col = Color.parseColor(BG_COLOR_CHOICES[rand.nextInt(BG_COLOR_CHOICES.length)]);
        int  txt_col = Color.parseColor(TXT_COLOR_CHOICES[rand.nextInt(TXT_COLOR_CHOICES.length)]);

        ((TextView) findViewById(R.id.commandDisplay)).setTextColor(txt_col);
        gameLayout.setBackgroundColor(bg_col);
    }

    @Override
    protected void onPause() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

}
