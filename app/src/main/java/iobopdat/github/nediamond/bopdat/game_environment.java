package iobopdat.github.nediamond.bopdat;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;

public class game_environment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_game_environment);
    }
}
