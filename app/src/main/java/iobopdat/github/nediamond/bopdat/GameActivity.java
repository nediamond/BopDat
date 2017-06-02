package iobopdat.github.nediamond.bopdat;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.content.Intent;

import java.util.Random;


public class GameActivity extends Activity {
    final int MOVE_PICK = 1;
    final int LIFT_DAT = 0;
    Random rand;
    int choice;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_game_environment);

        rand = new Random();
        choice = rand.nextInt(4);
        Intent intent = new Intent(GameActivity.this, LiftDatActivity.class);
        startActivityForResult(intent,MOVE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MOVE_PICK && resultCode == RESULT_OK){

            if(choice == LIFT_DAT) {
                Intent intent = new Intent(GameActivity.this, LiftDatActivity.class);
                startActivityForResult(intent, MOVE_PICK);
            }
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
