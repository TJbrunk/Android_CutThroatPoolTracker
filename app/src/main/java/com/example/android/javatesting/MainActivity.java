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
/* ----------------------------- 3 & 5 Player View switching ----------------------------------*/
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
                } else {
                    //    Log.d(TAG, "FivePlayers is Invisible");
                    ThreePlayers.setVisibility(View.GONE);
                    FivePlayers.setVisibility(View.VISIBLE);
                    SwitchText.setText("5 Player");
                }
            }
        });
    }
    public void toggle (View ball){
        String BallID;
        BallID = ball.getResources().getResourceName(ball.getId()).split("/")[1];
        Log.d(TAG, "Ball ID is:  " + BallID);
        switch (BallID) {
            case "b1":
            case "b3_1":
                if (ball.isActivated()){
                    //Log.d(TAG, "Toggle method called in activated");
                    ball.setBackgroundResource(R.drawable.one);
                    ball.setActivated(false);
                }
                else{
                    //Log.d(TAG, "Toggle method called in NON activated");
                    ball.setBackgroundResource(R.drawable.one_out);
                    ball.setActivated(true);
                }
                break;
            case "b2":
            case "b3_2":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.two);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.two_out);
                    ball.setActivated(true);
                }break;
            case "b3":
            case "b3_3":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.three);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.three_out);
                    ball.setActivated(true);
                }break;
            case "b3_4":
            case "b4":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.four);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.four_out);
                    ball.setActivated(true);
                }break;
            case "b3_5":
            case "b5":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.five);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.five_out);
                    ball.setActivated(true);
                }break;
            case "b3_6":
            case "b6":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.six);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.six_out);
                    ball.setActivated(true);
                }break;
            case "b3_7":
            case "b7":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.seven);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.seven_out);
                    ball.setActivated(true);
                }break;
            case "b3_8":
            case "b8":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.eight);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.eight_out);
                    ball.setActivated(true);
                }break;
            case "b3_9":
            case "b9":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.nine);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.nine_out);
                    ball.setActivated(true);
                }break;
            case "b3_10":
            case "b10":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.ten);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.ten_out);
                    ball.setActivated(true);
                }break;
            case "b3_11":
            case "b11":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.eleven);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.eleven_out);
                    ball.setActivated(true);
                }break;
            case "b3_12":
            case "b12":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.twelve);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.twelve_out);
                    ball.setActivated(true);
                }break;
            case "b3_13":
            case "b13":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.thirteen);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.thirteen_out);
                    ball.setActivated(true);
                }break;
            case "b3_14":
            case "b14":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.fourteen);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.fourteen_out);
                    ball.setActivated(true);
                }break;
            case "b3_15":
            case "b15":
                if (ball.isActivated()){
                    ball.setBackgroundResource(R.drawable.fifteen);
                    ball.setActivated(false);
                }
                else{
                    ball.setBackgroundResource(R.drawable.fifteen_out);
                    ball.setActivated(true);
                }break;
        }
    }

}
