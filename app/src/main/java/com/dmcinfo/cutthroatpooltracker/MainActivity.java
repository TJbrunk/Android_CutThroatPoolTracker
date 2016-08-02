package com.dmcinfo.cutthroatpooltracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class MainActivity extends Activity {

    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    private Spinner player1, player2, player3, player4, player5;
    private Spinner group1, group2, group3, group4, group5, group1_3, group2_3, group3_3;
    private ArrayList players, groups, ballsInPlay;
    private PlayerDB playerDB;
    private TreeMap PoolBalls;

    View FivePlayers, ThreePlayers;
    TextView PlayersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Initialize the balls in play list
        ResetBallsInPlay();
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

        FivePlayers = findViewById(R.id.five_player);
        ThreePlayers = findViewById(R.id.three_player);
        ThreePlayers.setVisibility(View.INVISIBLE);
        FivePlayers.setVisibility(View.VISIBLE);

        this.PoolBalls = PoolBall.InitPoolBalls(this, this);
        this.add_players();
    }

/* ----------------------------- 3 & 5 Player View switching ----------------------------------*/
    public void switch_views (View v)
    {
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
        ResetBallsInPlay();
    }

    //     ***************************               Toggle Ball images      *************************
    // This method is linked to every ball image through the style sheet, and gets called
    // when the ball is clicked
    public void toggle (View ball)
    {
        int ball_number = PoolBall.FindBall(ball);
        PoolBalls.put(ball_number, PoolBall.ToggleBall(this.PoolBalls.get(ball_number)));

//        if (PoolBall.IsBallInPlay(PoolBalls.get(ball_number)))
//        {
//            ballsInPlay.add(ball_number);
//        }
//        else
//        {
//            ballsInPlay.remove(ball_number);
//        }
        //this.IsGameOver();
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

    public void reset_pool (View v)
    {
        player1.setSelection(0);
        player2.setSelection(0);
        player3.setSelection(0);
        player4.setSelection(0);
        player5.setSelection(0);
        ResetBallsInPlay();
        recreate();
   }

    public void add_players ()
    {
        playerDB = new PlayerDB(this);

        playerDB.addPlayer("Boris", "Cherkasskiy");
        playerDB.addPlayer("Devon", "Fritz");
        playerDB.addPlayer("Jimmy", "Condon");
        playerDB.addPlayer("Nick", "Aroneseno");
        playerDB.addPlayer("Otto", "Gottlieb");
        playerDB.addPlayer("Ryan", "Lake");
        playerDB.addPlayer("Sully", "John");
        playerDB.addPlayer("Tim", "Gee");
        playerDB.addPlayer("Tyler", "Brink");
        playerDB.addPlayer("Cameron", "Fyfe");

//        playerDB.addPlayer("Guest1", "-");
//        playerDB.addPlayer("Guest2", "-");
//        playerDB.addPlayer("Guest3", "-");

        load_players();
    }

    private void load_players ()
    {
        int i = 1;
        this.players = new ArrayList();
        this.players.clear();
        this.players.add(""); // Add black player so that none can be selected
        while (this.playerDB.getPlayer(i) != "none") {
            this.players.add(this.playerDB.getPlayer(i));
            i += 1;
        }
        Collections.sort(players);
        this.players.add("Guest 1");
        this.players.add("Guest 2");
        this.players.add("Guest 3");

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
        this.groups.add(""); // Add blank player to list
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

    private void ResetBallsInPlay()
    {
        ballsInPlay = new ArrayList();
        for (int i = 1; i<16; i++) {
            ballsInPlay.add(i);
        }
    }

    // called buy update button on GUI. Requests JSON string defining all the balls from LabVIEW app
    public void UpdateBallsInPlay(View v)
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="http://jsonplaceholder.typicode.com/posts";
        final String url = "http://192.168.12.77:8001/PoolBallStatus";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
//                  Toast.makeText(getApplicationContext(), response.substring(0, 500), Toast.LENGTH_LONG).show();
                        try {
                            ReadJsonMessage(response);
                        }
                        catch (JSONException e){
                            Toast.makeText(getApplicationContext(), "JSON Error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Unable to contact " + url, Toast.LENGTH_LONG).show();
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // Called when a JSON message is received.
    // Parses the JSON sting and updates all the balls on the GUI
    public void ReadJsonMessage(String jsonString) throws JSONException
    {
        // static string for testing purposes:
        //String jsonTest = "[{\"Ball\":1,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":2,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":3,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":4,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":5,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":6,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":7,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":8,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":9,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":10,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":11,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":12,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":13,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":14,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}},{\"Ball\":15,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":0}}]";
        //JSONArray jsonArray = new JSONArray(jsonTest);

        // LV is providing an array of objects. Cast the string to an array
        JSONArray jsonArray = new JSONArray(jsonString);
        Integer iBall;
        Boolean InPlay;
        /*Integer LocationX = null;
        Integer LocationY = null;*/


        // loop over all 15 balls defined in the JSON array
        for (int j = 0; j< jsonArray.length() ; j++)
        {
            // get the next ball
            JSONObject jsonObject =  jsonArray.getJSONObject(j);
            // get the ball number
            iBall = jsonObject.getInt("Ball");
            // get if the ball is active or not
            InPlay = jsonObject.getBoolean("In Play");
            PoolBall.UpdateBall(this.PoolBalls.get(iBall), InPlay);
        }
    }
}