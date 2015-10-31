package com.dmcinfo.cutthroatpooltracker;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//drag and drop imports:
import android.view.DragEvent;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.widget.Toast;


public class MainActivity extends Activity {
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText player1, player2, player3, player4, player5;
    private TextView group1, group2, group3, group4, group5;
    public CharSequence dragData;

    View FivePlayers, ThreePlayers;
    TextView SwitchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //views to drag
        player1 = (EditText) findViewById(R.id.player1);
        player2 = (EditText) findViewById(R.id.player2);
        player3 = (EditText) findViewById(R.id.player3);
        player4 = (EditText) findViewById(R.id.player4);
        player5 = (EditText) findViewById(R.id.player5);

        //views to drop onto
        group1 = (TextView)findViewById(R.id.g1);
        group2 = (TextView)findViewById(R.id.g2);
        group3 = (TextView)findViewById(R.id.g3);
        group4 = (TextView)findViewById(R.id.g4);
        group5 = (TextView)findViewById(R.id.g5);

        //set long click listeners
        player1.setOnLongClickListener(new LongClickListener());
        player2.setOnLongClickListener(new LongClickListener());
        player3.setOnLongClickListener(new LongClickListener());
        player4.setOnLongClickListener(new LongClickListener());
        player5.setOnLongClickListener(new LongClickListener());

        //set drag listeners
        group1.setOnDragListener(new ChoiceDragListener());
        group2.setOnDragListener(new ChoiceDragListener());
        group3.setOnDragListener(new ChoiceDragListener());
        group4.setOnDragListener(new ChoiceDragListener());
        group5.setOnDragListener(new ChoiceDragListener());
    }

    /**
     * LongClickListener will handle touch events on draggable views
     */
    private final class LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(getApplicationContext(), "LONG CLICK", Toast.LENGTH_SHORT).show();
            /*
             * Drag details: we only need default behavior
             * - clip data could be set to pass data as part of drag
             * - shadow can be tailored
             */

                ClipData name = ClipData.newPlainText("","");

                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                //start dragging the item touched
                v.startDrag(name, shadowBuilder, v, 0);
                return true;
        }
    }


    /**
     * DragListener will handle dragged views being dropped on the drop area
     * - only the drop action will have processing added to it as we are not
     * - amending the default behavior for other parts of the drag process
     */
    private class ChoiceDragListener implements OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:

                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropped.getText().toString());
                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    //set the tag in the target view being dropped on - to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());

                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }

/* ----------------------------- 3 & 5 Player View switching ----------------------------------*/
    public void switch_views (View v){
        FivePlayers = findViewById(R.id.five_player);
        ThreePlayers = findViewById(R.id.three_player);
        SwitchText = (TextView) findViewById(R.id.player_switch_text);
    //    Toast.makeText(getApplicationContext(), "Switching modes", Toast.LENGTH_SHORT).show();

        if (FivePlayers.getVisibility() == View.VISIBLE){
    //        Toast.makeText(getApplicationContext(), "Switching to 3 player mode", Toast.LENGTH_SHORT).show();
            FivePlayers.setVisibility(View.INVISIBLE);
            ThreePlayers.setVisibility(View.VISIBLE);
            player4.setVisibility(View.GONE);
            player5.setVisibility(View.GONE);
            SwitchText.setText("3 Player");
        }
        else {
    //        Toast.makeText(getApplicationContext(), "Switching to 5 player mode", Toast.LENGTH_SHORT).show();
            ThreePlayers.setVisibility(View.INVISIBLE);
            FivePlayers.setVisibility(View.VISIBLE);
            player4.setVisibility(View.VISIBLE);
            player5.setVisibility(View.VISIBLE);
            SwitchText.setText("5 Player");
        }
    }

    //     ***************************               Toggle Ball images      *************************
   public void toggle (View ball){
        String BallID;
        BallID = ball.getResources().getResourceName(ball.getId()).split("/")[1];
      //  Log.d(TAG, "Ball ID is:  " + BallID);
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
                }break;
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

   public void reset_pool (View v){
       //Toast.makeText(getApplicationContext(), "RESETTING", Toast.LENGTH_SHORT).show();
       player1.setText("");
       player2.setText("");
       player3.setText("");
       player4.setText("");
       player5.setText("");
       recreate();
   }
};
