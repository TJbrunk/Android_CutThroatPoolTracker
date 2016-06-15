package com.dmcinfo.cutthroatpooltracker;

import android.support.v4.util.Pools;
import android.widget.TextView;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by tylerb on 6/14/2016.
 */
public class PoolBall extends MainActivity
{

    TextView ThreePlayBalls;
    TextView FivePlayerBalls;
    Boolean IsInPlay;
    Integer InPlayImage;
    Integer PocketedImage;

    private static String five_player_ball_ids[] = {
            "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8",
            "b9", "b10", "b11", "b12", "b13", "b14", "b15"};

    private static String three_player_ball_ids[] = {
            "b1_3", "b2_3", "b3_3", "b4_3", "b5_3", "b6_3",
            "b7_3", "b8_3", "b9_3", "b10_3", "b11_3",
            "b12_3", "b13_3", "b14_3", "b15_3"};

    private static Integer in_play_images[] = {R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.nine,
            R.drawable.ten,
            R.drawable.eleven,
            R.drawable.twelve,
            R.drawable.thirteen,
            R.drawable.fourteen,
            R.drawable.fifteen};

    private static Integer pocketed_images[] = { R.drawable.one_out,
            R.drawable.two_out,
            R.drawable.three_out,
            R.drawable.four_out,
            R.drawable.five_out,
            R.drawable.six_out,
            R.drawable.seven_out,
            R.drawable.eight_out,
            R.drawable.nine_out,
            R.drawable.ten_out,
            R.drawable.eleven_out,
            R.drawable.twelve_out,
            R.drawable.thirteen_out,
            R.drawable.fourteen_out,
            R.drawable.fifteen_out};

    private TextView GetThreePlayerBallRefs(String ball)
    {
        int id = getResources().getIdentifier(ball,
                "id",
                getPackageName());
        return (TextView) findViewById(id);
    }

    private TextView GetFivePlayerBallRefs(String ball)
    {
        int id = getResources().getIdentifier(ball,
                "id",
                getPackageName());
        return (TextView) findViewById(id);
    }

    public PoolBall(int ball_number)
    {
        //decrement the ball number so API calls can be used as 1-15
        ball_number --;
        ThreePlayBalls = GetThreePlayerBallRefs(three_player_ball_ids[ball_number]);
        FivePlayerBalls = GetFivePlayerBallRefs(five_player_ball_ids[ball_number]);
        IsInPlay = true;
        InPlayImage = in_play_images[ball_number];
        PocketedImage = pocketed_images[ball_number];
    }

    public static TreeMap InitPoolBalls()
    {
        TreeMap PoolBalls = new TreeMap();
        // initialize all the pool balls
        for(int i=1; i<=15; i++)
        {
            PoolBalls.put(i, new PoolBall(i));
        }

        return PoolBalls;
    }

}
