package com.dmcinfo.cutthroatpooltracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
        // Initialize the balls in play list
//        ResetBallsInPlay();
        setContentView(R.layout.activity_main);

        //views to drag
//        player1 = (Spinner) findViewById(R.id.player1);
//        player2 = (Spinner) findViewById(R.id.player2);
//        player3 = (Spinner) findViewById(R.id.player3);
//        player4 = (Spinner) findViewById(R.id.player4);
//        player5 = (Spinner) findViewById(R.id.player5);
//
//        //views to drop onto
//        group1 = (Spinner)findViewById(R.id.g1);
//        group2 = (Spinner)findViewById(R.id.g2);
//        group3 = (Spinner)findViewById(R.id.g3);
//        group4 = (Spinner)findViewById(R.id.g4);
//        group5 = (Spinner)findViewById(R.id.g5);
//        group1_3 = (Spinner)findViewById(R.id.g1_3);
//        group2_3 = (Spinner)findViewById(R.id.g2_3);
//        group3_3 = (Spinner)findViewById(R.id.g3_3);
//
//        FivePlayers = findViewById(R.id.five_player);
//        ThreePlayers = findViewById(R.id.three_player);
//        ThreePlayers.setVisibility(View.INVISIBLE);
//        FivePlayers.setVisibility(View.VISIBLE);
//
//        this.add_players();
    }

/* ----------------------------- 3 & 5 Player View switching ----------------------------------*/
//    public void switch_views (View v){
//        FivePlayers = findViewById(R.id.five_player);
//        ThreePlayers = findViewById(R.id.three_player);
//        PlayersButton = (TextView) findViewById(R.id.player_button);
//
//        if (FivePlayers.getVisibility() == View.VISIBLE){
//            FivePlayers.setVisibility(View.INVISIBLE);
//            ThreePlayers.setVisibility(View.VISIBLE);
//            player4.setVisibility(View.GONE);
//            player4.setSelection(0);
//            player5.setVisibility(View.GONE);
//            player5.setSelection(0);
//            PlayersButton.setText("3 Player");
//        }
//        else {
//            ThreePlayers.setVisibility(View.INVISIBLE);
//            FivePlayers.setVisibility(View.VISIBLE);
//            player4.setVisibility(View.VISIBLE);
//            player5.setVisibility(View.VISIBLE);
//            PlayersButton.setText("5 Player");
//        }
//        AddPlayerToGame();
//        ResetBallsInPlay();
//    }
//
//    //     ***************************               Toggle Ball images      *************************
//    public void toggle (View ball){
//        String BallID;
//        BallID = ball.getResources().getResourceName(ball.getId()).split("/")[1];
//        switch (BallID) {
//            case "b1":
//            case "b1_3":
//                ToggleBall(ball, 1, R.drawable.one, R.drawable.one_out);
//                break;
//            case "b2":
//            case "b2_3":
//                ToggleBall(ball, 2, R.drawable.two, R.drawable.two_out);
//                break;
//            case "b3":
//            case "b3_3":
//                ToggleBall(ball, 3, R.drawable.three, R.drawable.three_out);
//                break;
//            case "b4_3":
//            case "b4":
//                ToggleBall(ball, 4, R.drawable.four, R.drawable.four_out);
//                break;
//            case "b5_3":
//            case "b5":
//                ToggleBall(ball, 5, R.drawable.five, R.drawable.five_out);
//                break;
//            case "b6_3":
//            case "b6":
//                ToggleBall(ball, 6, R.drawable.six, R.drawable.six_out);
//                break;
//            case "b7_3":
//            case "b7":
//                ToggleBall(ball, 7, R.drawable.seven, R.drawable.seven_out);
//                break;
//            case "b8_3":
//            case "b8":
//                ToggleBall(ball, 8, R.drawable.eight, R.drawable.eight_out);
//                break;
//            case "b9_3":
//            case "b9":
//                ToggleBall(ball, 9, R.drawable.nine, R.drawable.nine_out);
//                break;
//            case "b10_3":
//            case "b10":
//                ToggleBall(ball, 10, R.drawable.ten, R.drawable.ten_out);
//                break;
//            case "b11_3":
//            case "b11":
//                ToggleBall(ball, 11, R.drawable.eleven, R.drawable.eleven_out);
//                break;
//            case "b12_3":
//            case "b12":
//                ToggleBall(ball, 12, R.drawable.twelve, R.drawable.twelve_out);
//                break;
//            case "b13_3":
//            case "b13":
//                ToggleBall(ball, 13, R.drawable.thirteen, R.drawable.thirteen_out);
//                break;
//            case "b14_3":
//            case "b14":
//                ToggleBall(ball, 14, R.drawable.fourteen, R.drawable.fourteen_out);
//                break;
//            case "b15_3":
//            case "b15":
//                ToggleBall(ball, 15, R.drawable.fifteen, R.drawable.fifteen_out);
//                break;
//        }
//
//        this.IsGameOver();
//    }
//
//    private void ToggleBall(View ball, int number, int normalImage, int outImage)
//    {
//        if (ball.isActivated()){
//            ball.setBackgroundResource(normalImage);
//            ball.setActivated(false);
//            ballsInPlay.add(number);
//        }
//        else
//        {
//            ball.setBackgroundResource(outImage);
//            ball.setActivated(true);
//            ballsInPlay.remove(ballsInPlay.indexOf(number));
//        }
//    }
//
//    private void IsGameOver()
//    {
//        boolean is3Player;
//        int activePlayers = 0;
//        int activePlayer = 0;
//        if (is3Player = ThreePlayers.getVisibility() == View.VISIBLE)
//        {
//            if (ballsInPlay.size() < 6)
//            {
//                // for each ball in play check who it belongs to
//                for (Object item:ballsInPlay) {
//                    if ((int)item < 6 && activePlayer != 1) {
//                        activePlayers += 1;
//                        activePlayer = 1;
//                    } else if ((int) item > 5 && (int)item < 11 && activePlayer != 2) {
//                        activePlayers += 1;
//                        activePlayer = 2;
//                    } else if ((int)item > 10 && activePlayer != 3){
//                        activePlayers += 1;
//                        activePlayer = 3;
//                    }
//                    // break out of the loop if more than one active player
//                    if (activePlayers > 1) {
//                        break;
//                    }
//                }
//            }
//        }
//        else
//        {
//            if (ballsInPlay.size() < 4)
//            {
//                // for each ball in play check who it belongs to
//                for (Object item:ballsInPlay) {
//                    if ((int)item < 4 && activePlayer != 1) {
//                        activePlayers += 1;
//                        activePlayer = 1;
//                    } else if ((int)item > 3 && (int)item < 7 && activePlayer != 2) {
//                        activePlayers += 1;
//                        activePlayer = 2;
//                    } else if ((int)item > 6 && (int)item < 10 && activePlayer != 3) {
//                        activePlayers += 1;
//                        activePlayer = 3;
//                    } else if ((int)item > 9 && (int)item < 13 && activePlayer != 4) {
//                        activePlayers += 1;
//                        activePlayer = 4;
//                    } else if ((int)item > 12 && activePlayer != 5){
//                        activePlayers += 1;
//                        activePlayer = 5;
//                    }
//                    // break out of the loop if more than one active player
//                    if (activePlayers > 1) {
//                        break;
//                    }
//                }
//            }
//        }
//
//        // Create a popup with the winners name if there is one active player left
//        if (activePlayers == 1) {
//            Toast.makeText(this, this.GetWinningPlayer(activePlayer, is3Player) + " is the last one standing", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private String GetWinningPlayer(int player, boolean is3Player)
//    {
//        if (is3Player)
//        {
//            switch(player)
//            {
//                case 1:
//                    return group1_3.getSelectedItem().toString();
//                case 2:
//                    return group2_3.getSelectedItem().toString();
//                case 3:
//                    return group3_3.getSelectedItem().toString();
//                default:
//                    return "";
//            }
//        }
//        else
//        {
//            switch(player)
//            {
//                case 1:
//                    return group1.getSelectedItem().toString();
//                case 2:
//                    return group2.getSelectedItem().toString();
//                case 3:
//                    return group3.getSelectedItem().toString();
//                case 4:
//                    return group4.getSelectedItem().toString();
//                case 5:
//                    return group5.getSelectedItem().toString();
//                default:
//                    return "";
//            }
//        }
//    }
//
//    public void reset_pool (View v){
//        player1.setSelection(0);
//        player2.setSelection(0);
//        player3.setSelection(0);
//        player4.setSelection(0);
//        player5.setSelection(0);
//        ResetBallsInPlay();
//        recreate();
//   }
//
//    public void add_players (){
//        playerDB = new PlayerDB(this);
//        playerDB.addPlayer("Guest", "-");
//        playerDB.addPlayer("Nick", "Aroneseno");
//        playerDB.addPlayer("Tyler", "Brink");
//        playerDB.addPlayer("Otto", "Gottlieb");
//        playerDB.addPlayer("Jimmy", "Condon");
//        playerDB.addPlayer("Sully", "John");
//        playerDB.addPlayer("Boris", "Cherkasskiy");
//        playerDB.addPlayer("Devon", "Fritz");
//        playerDB.addPlayer("Tim", "Gee");
//        playerDB.addPlayer("Ryan", "Lake");
//
//        load_players();
//    }
//
//    private void load_players (){
//        int i = 1;
//        this.players = new ArrayList();
//        this.players.clear();
//        //this.players.add(""); // Add black player so that none can be selected
//        while (this.playerDB.getPlayer(i) != "none") {
//            this.players.add(this.playerDB.getPlayer(i));
//            i += 1;
//        }
//        ArrayAdapter adapter = new ArrayAdapter(this, com.dmcinfo.cutthroatpooltracker.R.layout.player_dropdown_item, this.players);
//        player1.setAdapter(adapter);
//        player2.setAdapter(adapter);
//        player3.setAdapter(adapter);
//        player4.setAdapter(adapter);
//        player5.setAdapter(adapter);
//
//        player1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                AddPlayerToGame();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        player2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                AddPlayerToGame();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        player3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                AddPlayerToGame();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        player4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                AddPlayerToGame();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        player5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                AddPlayerToGame();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }
//
//    private void AddPlayerToGame()
//    {
//        FivePlayers = findViewById(R.id.five_player);
//        this.groups = new ArrayList();
//        this.groups.clear();
//        this.groups.add(""); // Add blank player to list
//        if (player1.getSelectedItem() != "") {
//            this.groups.add(player1.getSelectedItem());
//        }
//        if (player2.getSelectedItem() != "") {
//            this.groups.add(player2.getSelectedItem());
//        }
//        if (player3.getSelectedItem() != "") {
//            this.groups.add(player3.getSelectedItem());
//        }
//        if (FivePlayers.getVisibility() == View.VISIBLE) {
//            if (player4.getSelectedItem() != "Guest") {
//                this.groups.add(player4.getSelectedItem());
//            }
//            if (player5.getSelectedItem() != "Guest") {
//                this.groups.add(player5.getSelectedItem());
//            }
//        }
//        ArrayAdapter adapter = new ArrayAdapter(this, com.dmcinfo.cutthroatpooltracker.R.layout.player_dropdown_item, groups);
//        group1.setAdapter(adapter);
//        group1_3.setAdapter(adapter);
//        group2.setAdapter(adapter);
//        group2_3.setAdapter(adapter);
//        group3.setAdapter(adapter);
//        group3_3.setAdapter(adapter);
//        group4.setAdapter(adapter);
//        group5.setAdapter(adapter);
//    }
//
//    private void ResetBallsInPlay()
//    {
//        ballsInPlay = new ArrayList();
//        for (int i = 1; i<16; i++) {
//            ballsInPlay.add(i);
//        }
//    }
//

    public void UpdateBallsInPlay(View v)
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://jsonplaceholder.typicode.com/posts";

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
                        Toast.makeText(getApplicationContext(), "Unable to contact camera", Toast.LENGTH_SHORT).show();
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

//    public void ReadJsonArray(String jsonArrayString) throws JSONException
//    {
//        JSONArray jArrayString = new JSONArray()
//    }

    public void ReadJsonMessage(String jsonString) throws JSONException
    {
        String jsonTest = "[{\"Ball\":1,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":1}},{\"Ball\":2,\"In Play\":true,\"Location\":{\"X\":2,\"Y\":3}},{\"Ball\":3,\"In Play\":false,\"Location\":{\"X\":4,\"Y\":5}}]";
        JSONArray jsonArray = new JSONArray(jsonTest);
        //JSONArray jsonArray = new JSONArray("[{\"Ball\":1,\"In Play\":true,\"Location\":{\"X\":0,\"Y\":1}},{\"Ball\":2,\"In Play\":true,\"Location\":{\"X\":2,\"Y\":3}},{\"Ball\":3,\"In Play\":false,\"Location\":{\"X\":4,\"Y\":5}}]");
        Integer iBall;
        String sBall;
        Boolean InPlay;
        /*Integer LocationX = null;
        Integer LocationY = null;*/

        for (int j = 0; j< jsonArray.length() ; j++)
        {
            JSONObject jsonObject =  jsonArray.getJSONObject(j);
            iBall = jsonObject.getInt("Ball");
            sBall = iBall.toString();
            InPlay = jsonObject.getBoolean("In Play");
            if(InPlay)
            {
                Toast.makeText(getApplicationContext(), "Ball "+ sBall +" is in play", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Ball "+ sBall +" is pocketed", Toast.LENGTH_SHORT).show();
            }

        }
        //JSONObject jsonObject =  jsonArray.getJSONObject(1);
        //Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();
        //title = jsonObject.getString("title");
        //Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
    }

}