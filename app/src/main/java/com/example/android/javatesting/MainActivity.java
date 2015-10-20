package com.example.android.javatesting;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.Spinner;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Button button;
    View FivePlayers;
    View ThreePlayers;
    TextView SwitchText;

    public void addListenerOnButton() {
    /* Toggle between 3 player and 5 player views */
        button = (Button) findViewById(R.id.num_player_switch);
        FivePlayers = findViewById(R.id.five_player);
        ThreePlayers = findViewById(R.id.three_player);
        SwitchText = (TextView)findViewById(R.id.player_switch_text);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (FivePlayers.getVisibility() == View.VISIBLE) {
                //    Log.d(TAG, "FivePlayers is visible");
                    FivePlayers.setVisibility(View.INVISIBLE);
                    ThreePlayers.setVisibility(View.VISIBLE);
                    SwitchText.setText("3 Player");
                } else{
                //    Log.d(TAG, "FivePlayers is Invisible");
                    ThreePlayers.setVisibility(View.GONE);
                    FivePlayers.setVisibility(View.VISIBLE);
                    SwitchText.setText("5 Player");
                }
            }
        });
    }
}
