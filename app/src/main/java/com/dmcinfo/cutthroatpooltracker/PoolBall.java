package com.dmcinfo.cutthroatpooltracker;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by tylerb on 6/14/2016.
 */
public class PoolBall extends Activity
{

    TextView ThreePlayerBalls;
    TextView FivePlayerBalls;
    Boolean IsInPlay;
    Integer InPlayImage;
    Integer PocketedImage;

    private static Context app_context;
    private static Activity app_activity;

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
        int id = app_context.getResources().getIdentifier(ball, "id", app_context.getPackageName());
        return (TextView) this.app_activity.findViewById(id);
    }

    private TextView GetFivePlayerBallRefs(String ball)
    {
        int id = app_context.getResources().getIdentifier(ball,
                "id",
                app_context.getPackageName());
        return (TextView) app_activity.findViewById(id);
    }

    public PoolBall(int ball_number)
    {
        //decrement the ball number so API calls can be used as 1-15
        ball_number --;
        ThreePlayerBalls = GetThreePlayerBallRefs(three_player_ball_ids[ball_number]);
        FivePlayerBalls = GetFivePlayerBallRefs(five_player_ball_ids[ball_number]);
        IsInPlay = true;
        InPlayImage = in_play_images[ball_number];
        PocketedImage = pocketed_images[ball_number];
    }

    public static TreeMap InitPoolBalls(Context context, Activity activity)
    {
        app_context = context;
        app_activity = activity;
        TreeMap PoolBalls = new TreeMap();
        // initialize all the pool balls
        for(int i=1; i<=15; i++)
        {
            PoolBalls.put(i, new PoolBall(i));
        }

        return PoolBalls;
    }

    public static Integer FindBall(View ball)
    {
        int found;
        String ballID = ball.getResources().getResourceName(ball.getId()).split("/")[1];
        found = Arrays.asList(five_player_ball_ids).indexOf(ballID);
        if (found >= 0)
        {
            return  found + 1;
        }
        else
        {
            return Arrays.asList(three_player_ball_ids).indexOf(ballID) + 1;
        }
    }

    public static PoolBall ToggleBall(Object obj )
    {
        PoolBall ball = (PoolBall) obj;

        if (ball.IsInPlay)
        {
            // if the ball was in play, set it as pocketed now
            ball.PockectBall(ball);
        }
        else
        {
            ball.ReturnBallToPlay(ball);
        }

        return ball;
    }

    public static boolean IsBallInPlay(Object obj)
    {
        PoolBall ball = (PoolBall) obj;

        return ball.IsInPlay;
    }

    public static PoolBall UpdateBall(Object obj, boolean isInPlay)
    {
        PoolBall ball = (PoolBall) obj;

        ball.IsInPlay = isInPlay;
        if(isInPlay)
        {
            ball = ReturnBallToPlay(ball);
        }
        else
        {
            ball = PockectBall(ball);
        }
        return  ball;

    }

    private static PoolBall PockectBall(PoolBall ball)
    {
        ball.ThreePlayerBalls.setBackgroundResource(ball.PocketedImage);
        ball.FivePlayerBalls.setBackgroundResource(ball.PocketedImage);

        ball.ThreePlayerBalls.setActivated(false);
        ball.FivePlayerBalls.setActivated(false);

        ball.IsInPlay = false;

        return ball;
    }

    private static PoolBall ReturnBallToPlay(PoolBall ball)
    {
        ball.ThreePlayerBalls.setBackgroundResource(ball.InPlayImage);
        ball.FivePlayerBalls.setBackgroundResource(ball.InPlayImage);

        ball.ThreePlayerBalls.setActivated(true);
        ball.FivePlayerBalls.setActivated(true);

        ball.IsInPlay = true;

        return ball;
    }

}
