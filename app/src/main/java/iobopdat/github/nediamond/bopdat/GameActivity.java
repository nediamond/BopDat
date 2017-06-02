package iobopdat.github.nediamond.bopdat;

import android.app.Activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Window;
import android.widget.TextView;
import android.content.Intent;

import java.util.Random;


public class GameActivity extends Activity {
    final int MOVE_PICK = 1;
    final int LIFT_DAT=0, PUSH_DAT=1, PULL_DAT=2, SHAKE_DAT=3;
    Random rand;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_game_environment);

        rand = new Random();

        startActivityForResult(chooseMove(),MOVE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //wait(1000);
        if(requestCode == MOVE_PICK && resultCode == RESULT_OK){
            score++;
            startActivityForResult(chooseMove(), MOVE_PICK);
        }
        else{
            this.setContentView(R.layout.activity_game_environment);
            ((TextView) findViewById(R.id.commandDisplay)).setText("Your score is\n" + Integer.toString(score));
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private Intent chooseMove() {
        int choice = rand.nextInt(4);
        Intent intent;
        switch (choice) {
            case LIFT_DAT:
                intent = new Intent(GameActivity.this, LiftDatActivity.class);
                break;
            case PUSH_DAT:
                intent = new Intent(GameActivity.this, PushDatActivity.class);
                break;
            case PULL_DAT:
                intent = new Intent(GameActivity.this, PullDatActivity.class);
                break;
            case SHAKE_DAT:
                intent = new Intent(GameActivity.this, ShakeDatActivity.class);
                break;
            default:
                intent = new Intent(GameActivity.this, MainActivity.class);
                break;
        }
        return intent;
    }
}
