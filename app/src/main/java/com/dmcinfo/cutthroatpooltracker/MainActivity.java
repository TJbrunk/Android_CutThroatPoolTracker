package com.dmcinfo.cutthroatpooltracker;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Typeface;
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

//drag and drop imports:
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;


    public class MainActivity extends ActionBarActivity {
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        private TextView player1, player2, player3, player4, player5;
        private TextView group1, group2, group3, group4, group5;
        public CharSequence dragData;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //  addListenerOnButton();

            //views to drag
            player1 = (TextView)findViewById(R.id.player1);
            player2 = (TextView)findViewById(R.id.player2);
            //player3 = (TextView)findViewById(R.id.player3);

            //views to drop onto
            group1 = (TextView)findViewById(R.id.g1);
       //     group2 = (TextView)findViewById(R.id.g2);
        //    group3 = (TextView)findViewById(R.id.g3);

            //set touch listeners
            player1.setOnTouchListener(new ChoiceTouchListener());
         //   player2.setOnLongClickListener(new ChoiceTouchListener());
            player2.setOnTouchListener(new ChoiceTouchListener());
       //     option3.setOnTouchListener(new ChoiceTouchListener());

            //set drag listeners
            group1.setOnDragListener(new ChoiceDragListener());
         //   group2.setOnDragListener(new ChoiceDragListener());
        //    choice3.setOnDragListener(new ChoiceDragListener());
        }

        /**
         * ChoiceTouchListener will handle touch events on draggable views
         */
        private final class ChoiceTouchListener implements OnTouchListener {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            /*
             * Drag details: we only need default behavior
             * - clip data could be set to pass data as part of drag
             * - shadow can be tailored
             */
                    ClipData name = ClipData.newPlainText("","");
                 //   ClipData name = ClipData.newPlainText("PlayerName", "PLAYER_PLAYER");

                    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    //start dragging the item touched
                    view.startDrag(name, shadowBuilder, view, 0);
                    return true;
                } else {
                    return false;
                }
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
                        //stop displaying the view where it was before it was dragged
                        //        view.setVisibility(View.INVISIBLE);
                        //update the text in the target view to reflect the data being dropped
                        dropTarget.setText(dropTarget.getText().toString() + dropped.getText().toString());
                        //make it bold to highlight the fact that an item has been dropped
                        dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                        //if an item has already been dropped here, there will be a tag
                        Object tag = dropTarget.getTag();
                     /*   //if there is already an item here, set it back visible in its original place
                        if(tag!=null)
                        {
                            //the tag is the view id already dropped here
                            int existingID = (Integer)tag;
                            //set the original view visible again
                            findViewById(existingID).setVisibility(View.VISIBLE);
                        }*/
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);

                    case DragEvent.ACTION_DRAG_ENDED:
                        //no action necessary
                        break;
                    default:
                        break;
                }
                return true;
            }
        }
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    private static final String TAG = MainActivity.class.getSimpleName();

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  addListenerOnButton();
    }*.

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
 /*   Button button;
    View FivePlayers;
    View ThreePlayers;
    TextView SwitchText;

    public void addListenerOnButton() {
    // Toggle between 3 player and 5 player views
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
    }*/


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

}