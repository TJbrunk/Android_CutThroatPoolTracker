package com.dmcinfo.cutthroatpooltracker;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

//drag and drop imports:
import android.view.DragEvent;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    private static final String TAG = MainActivity.class.getSimpleName();
    private Spinner player1, player2, player3, player4, player5;
    private Spinner group1, group2, group3, group4, group5, group1_3, group2_3, group3_3;
    private ArrayList players, groups, ballsInPlay;
    private PlayerDB playerDB;
    public CharSequence dragData;
    private int pktoload = 1;

    View FivePlayers, ThreePlayers;
    TextView PlayersButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //views to drag
        player1 = (Spinner) findViewById(R.id.player1);
        player2 = (Spinner) findViewById(R.id.player2);
        player3 = (Spinner) findViewById(R.id.player3);
        player4 = (Spinner) findViewById(R.id.player4);
        player5 = (Spinner) findViewById(R.id.player5);

        //views to drop onto
        group1 = (Spinner)findViewById(R.id.g1);
        group2 = (Spinner)findViewById(R.id.g2);
        group3 = (Spinner)findViewById(R.id.g3);
        group4 = (Spinner)findViewById(R.id.g4);
        group5 = (Spinner)findViewById(R.id.g5);
        group1_3 = (Spinner)findViewById(R.id.g1_3);
        group2_3 = (Spinner)findViewById(R.id.g2_3);
        group3_3 = (Spinner)findViewById(R.id.g3_3);
/*
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
        group1_3.setOnDragListener(new ChoiceDragListener());
        group2_3.setOnDragListener(new ChoiceDragListener());
        group3_3.setOnDragListener(new ChoiceDragListener());
*/
        this.add_players();

        // Initialize the balls in play list
        ballsInPlay = new ArrayList();
        for (int i = 1; i<16; i++) {
            ballsInPlay.add(i);
        }

    }

    /**
     * LongClickListener will handle touch events on draggable views
     */

    /*private final class LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
          //  Toast.makeText(getApplicationContext(), "LONG CLICK", Toast.LENGTH_SHORT).show();
            *//*
             * Drag details: we only need default behavior
             * - clip data could be set to pass data as part of drag
             * - shadow can be tailored
             *//*

                ClipData name = ClipData.newPlainText("","");

                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                //start dragging the item touched
                v.startDrag(name, shadowBuilder, v, 0);
                return true;
        }
    }*/


    /**
     * DragListener will handle dragged views being dropped on the drop area
     * - only the drop action will have processing added to it as we are not
     * - amending the default behavior for other parts of the drag process
     */
    /*private class ChoiceDragListener implements OnDragListener {

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
                 //   dropTarget.setTag(dropped.getId());

                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }*/

/* ----------------------------- 3 & 5 Player View switching ----------------------------------*/
    public void switch_views (View v){
        FivePlayers = findViewById(R.id.five_player);
        ThreePlayers = findViewById(R.id.three_player);
        PlayersButton = (TextView) findViewById(R.id.player_button);

        if (FivePlayers.getVisibility() == View.VISIBLE){
            FivePlayers.setVisibility(View.INVISIBLE);
            ThreePlayers.setVisibility(View.VISIBLE);
            player4.setVisibility(View.GONE);
            player4.setSelection(0);
            player5.setVisibility(View.GONE);
            player5.setSelection(0);
            PlayersButton.setText("3 Player");
        }
        else {
            ThreePlayers.setVisibility(View.INVISIBLE);
            FivePlayers.setVisibility(View.VISIBLE);
            player4.setVisibility(View.VISIBLE);
            player5.setVisibility(View.VISIBLE);
            PlayersButton.setText("5 Player");
        }
        AddPlayerToGame();
    }

    //     ***************************               Toggle Ball images      *************************
    public void toggle (View ball){
        String BallID;
        BallID = ball.getResources().getResourceName(ball.getId()).split("/")[1];
      //  Log.d(TAG, "Ball ID is:  " + BallID);
        switch (BallID) {
            case "b1":
            case "b1_3":
                ToggleBall(ball, 1, R.drawable.one, R.drawable.one_out);
                break;
            case "b2":
            case "b2_3":
                ToggleBall(ball, 2, R.drawable.two, R.drawable.two_out);
                break;
            case "b3":
            case "b3_3":
                ToggleBall(ball, 3, R.drawable.three, R.drawable.three_out);
                break;
            case "b4_3":
            case "b4":
                ToggleBall(ball, 4, R.drawable.four, R.drawable.four_out);
                break;
            case "b5_3":
            case "b5":
                ToggleBall(ball, 5, R.drawable.five, R.drawable.five_out);
                break;
            case "b6_3":
            case "b6":
                ToggleBall(ball, 6, R.drawable.six, R.drawable.six_out);
                break;
            case "b7_3":
            case "b7":
                ToggleBall(ball, 7, R.drawable.seven, R.drawable.seven_out);
                break;
            case "b8_3":
            case "b8":
                ToggleBall(ball, 8, R.drawable.eight, R.drawable.eight_out);
                break;
            case "b9_3":
            case "b9":
                ToggleBall(ball, 9, R.drawable.nine, R.drawable.nine_out);
                break;
            case "b10_3":
            case "b10":
                ToggleBall(ball, 10, R.drawable.ten, R.drawable.ten_out);
                break;
            case "b11_3":
            case "b11":
                ToggleBall(ball, 11, R.drawable.eleven, R.drawable.eleven_out);
                break;
            case "b12_3":
            case "b12":
                ToggleBall(ball, 12, R.drawable.twelve, R.drawable.twelve_out);
                break;
            case "b13_3":
            case "b13":
                ToggleBall(ball, 13, R.drawable.thirteen, R.drawable.thirteen_out);
                break;
            case "b14_3":
            case "b14":
                ToggleBall(ball, 14, R.drawable.fourteen, R.drawable.fourteen_out);
                break;
            case "b15_3":
            case "b15":
                ToggleBall(ball, 15, R.drawable.fifteen, R.drawable.fifteen_out);
                break;
        }

        this.IsGameOver();
    }

    private void ToggleBall(View ball, int number, int normalImage, int outImage)
    {
        if (ball.isActivated()){
            ball.setBackgroundResource(normalImage);
            ball.setActivated(false);
            ballsInPlay.add(number);
        }
        else
        {
            ball.setBackgroundResource(outImage);
            ball.setActivated(true);
            ballsInPlay.remove(ballsInPlay.indexOf(number));
        }
    }

    private void IsGameOver()
    {
        boolean is3Player;
        int activePlayers = 0;
        int activePlayer = 0;
        if (is3Player = ThreePlayers.getVisibility() == View.VISIBLE)
        {
            if (ballsInPlay.size() < 6)
            {
                // for each ball in play check who it belongs to
                for (Object item:ballsInPlay) {
                    if ((int)item < 6 && activePlayer != 1) {
                        activePlayers += 1;
                        activePlayer = 1;
                    } else if ((int) item > 5 && (int)item < 11 && activePlayer != 2) {
                        activePlayers += 1;
                        activePlayer = 2;
                    } else if ((int)item > 10 && activePlayer != 3){
                        activePlayers += 1;
                        activePlayer = 3;
                    }
                    // break out of the loop if more than one active player
                    if (activePlayers > 1) {
                        break;
                    }
                }
            }
        }
        else
        {
            if (ballsInPlay.size() < 4)
            {
                // for each ball in play check who it belongs to
                for (Object item:ballsInPlay) {
                    if ((int)item < 4 && activePlayer != 1) {
                        activePlayers += 1;
                        activePlayer = 1;
                    } else if ((int)item > 3 && (int)item < 7 && activePlayer != 2) {
                        activePlayers += 1;
                        activePlayer = 2;
                    } else if ((int)item > 6 && (int)item < 10 && activePlayer != 3) {
                        activePlayers += 1;
                        activePlayer = 3;
                    } else if ((int)item > 9 && (int)item < 13 && activePlayer != 4) {
                        activePlayers += 1;
                        activePlayer = 4;
                    } else if ((int)item > 12 && activePlayer != 5){
                        activePlayers += 1;
                        activePlayer = 5;
                    }
                    // break out of the loop if more than one active player
                    if (activePlayers > 1) {
                        break;
                    }
                }
            }
        }

        // Create a popup with the winners name if there is one active player left
        if (activePlayers == 1) {
            Toast.makeText(this, this.GetWinningPlayer(activePlayer, is3Player) + " is the last one standing", Toast.LENGTH_LONG).show();
        }
    }

    private String GetWinningPlayer(int player, boolean is3Player)
    {
        if (is3Player)
        {
            switch(player)
            {
                case 1:
                    return group1_3.getSelectedItem().toString();
                case 2:
                    return group2_3.getSelectedItem().toString();
                case 3:
                    return group3_3.getSelectedItem().toString();
                default:
                    return "";
            }
        }
        else
        {
            switch(player)
            {
                case 1:
                    return group1.getSelectedItem().toString();
                case 2:
                    return group2.getSelectedItem().toString();
                case 3:
                    return group3.getSelectedItem().toString();
                case 4:
                    return group4.getSelectedItem().toString();
                case 5:
                    return group5.getSelectedItem().toString();
                default:
                    return "";
            }
        }
    }

    public void reset_pool (View v){
        player1.setSelection(0);
        player2.setSelection(0);
        player3.setSelection(0);
        player4.setSelection(0);
        player5.setSelection(0);
        recreate();
   }


    public class dbHelper extends SQLiteOpenHelper {
       private static final  int DATABASE_VERSION = 1;
       private static final String DATABASE_NAME = "Cutthroat_Pool.sqlite3";
       protected static final String PLAYER_TABLE = "Players";

       private static final String CREATE_PLAYER_TABLE = "create table if not exists "
               + PLAYER_TABLE
               + " ( _id integer primary key autoincrement," +
               " FirstName TEXT NOT NULL," +
               " LastName TEXT NOT NULL);";

        private static final String DROP_PLAYER_TABLE = "drop table if exists " + PLAYER_TABLE;

       public dbHelper(Context context) {
           super(context, DATABASE_NAME, null, DATABASE_VERSION);
           context.deleteDatabase(DATABASE_NAME);
       };

       @Override
       public void onCreate(SQLiteDatabase db) {
           db.execSQL(DROP_PLAYER_TABLE);
           db.execSQL(CREATE_PLAYER_TABLE);
       };

       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
           //not sure what we want to do here, but this must be implemented
       };
    };
    public class PlayerDB extends dbHelper {
        public PlayerDB (Context context) {
            super(context);
        }

        private static final String COL_ID = "_id";
        private static final String COL1 = "FirstName";
        private static final String COL2 = "LastName";

        public void addPlayer(String FName, String LName ){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COL1, FName);
            values.put(COL2, LName);

            db.insert(PLAYER_TABLE, null, values);
            db.close();
        };

        public String getPlayer(int Row){
            String name = "none";
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM "+PLAYER_TABLE+" WHERE _id="+Row;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()){

                do {
                    name = cursor.getString(1);
                } while (cursor.moveToNext());
            }

            db.close();

            return name;
        }
    }

    public void add_players (){
        playerDB = new PlayerDB(this);
        playerDB.addPlayer("Guest", "-");
        playerDB.addPlayer("Nick", "A");
        playerDB.addPlayer("Tyler", "B");
        playerDB.addPlayer("Otto", "G");
        playerDB.addPlayer("Jimmy", "C");
        playerDB.addPlayer("Sully", "J");
        playerDB.addPlayer("Boris", "C");
        playerDB.addPlayer("Devon", "F");
        playerDB.addPlayer("Tim", "Gee");

        load_players();
    }

    private void load_players (){
        int i = 1;
        this.players = new ArrayList();
        this.players.clear();
        //this.players.add(""); // Add black player so that none can be selected
        while (this.playerDB.getPlayer(i) != "none") {
            this.players.add(this.playerDB.getPlayer(i));
            i += 1;
        }
        ArrayAdapter adapter = new ArrayAdapter(this, com.dmcinfo.cutthroatpooltracker.R.layout.player_dropdown_item, this.players);
        player1.setAdapter(adapter);
        player2.setAdapter(adapter);
        player3.setAdapter(adapter);
        player4.setAdapter(adapter);
        player5.setAdapter(adapter);

        player1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddPlayerToGame();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddPlayerToGame();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddPlayerToGame();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddPlayerToGame();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AddPlayerToGame();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void AddPlayerToGame()
    {
        FivePlayers = findViewById(R.id.five_player);
        this.groups = new ArrayList();
        this.groups.clear();
        this.groups.add("New Player"); // Add blank player to list
        if (player1.getSelectedItem() != "") {
            this.groups.add(player1.getSelectedItem());
        }
        if (player2.getSelectedItem() != "") {
            this.groups.add(player2.getSelectedItem());
        }
        if (player3.getSelectedItem() != "") {
            this.groups.add(player3.getSelectedItem());
        }
        if (FivePlayers.getVisibility() == View.VISIBLE) {
            if (player4.getSelectedItem() != "Guest") {
                this.groups.add(player4.getSelectedItem());
            }
            if (player5.getSelectedItem() != "Guest") {
                this.groups.add(player5.getSelectedItem());
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(this, com.dmcinfo.cutthroatpooltracker.R.layout.player_dropdown_item, groups);
        group1.setAdapter(adapter);
        group1_3.setAdapter(adapter);
        group2.setAdapter(adapter);
        group2_3.setAdapter(adapter);
        group3.setAdapter(adapter);
        group3_3.setAdapter(adapter);
        group4.setAdapter(adapter);
        group5.setAdapter(adapter);
    }
};


