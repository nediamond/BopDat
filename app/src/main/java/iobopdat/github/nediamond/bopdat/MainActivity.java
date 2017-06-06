package iobopdat.github.nediamond.bopdat;

import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    final String PREFS_NAME = "prefs_name";
    TextView highScore;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.playButton);

        SharedPreferences myPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = myPreferences.edit();
        highScore = (TextView) findViewById(R.id.textView2);

        int j = myPreferences.getInt("highScore", -1);
        if (j == -1)
            highScore.setText("");
        else
            highScore.setText("High Score: " + j);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
