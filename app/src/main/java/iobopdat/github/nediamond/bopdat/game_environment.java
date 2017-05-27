package iobopdat.github.nediamond.bopdat;

import android.app.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.content.Context;
import android.content.Intent;


public class game_environment extends Activity {
    final int MOVE_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_game_environment);
        Intent intent = new Intent(game_environment.this,MoveActivity.class);
        startActivityForResult(intent,MOVE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MOVE_PICK && resultCode == RESULT_OK){
            this.setContentView(R.layout.activity_game_environment);
            ((TextView) findViewById(R.id.commandDisplay)).setText("Move Gud");
        }
        else{
            this.setContentView(R.layout.activity_game_environment);
            ((TextView) findViewById(R.id.commandDisplay)).setText("Move NotSoGud");
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
