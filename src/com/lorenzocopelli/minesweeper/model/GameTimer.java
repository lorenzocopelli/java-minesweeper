package com.lorenzocopelli.minesweeper.model;

import com.lorenzocopelli.minesweeper.MinesweeperForm;
import com.lorenzocopelli.minesweeper.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer
{
    private static GameTimer sharedInstance;
    private MinesweeperForm minesweeperForm;
    private Timer timer;
    private int elapsedSeconds;
    private boolean active;

    private class Task extends TimerTask
    {
        @Override
        public void run()
        {
            if (minesweeperForm == null)
            {
                return;
            }

            if (elapsedSeconds >= Constants.timeLimit)
            {
                return;
            }

            ++elapsedSeconds;
            minesweeperForm.getTimeLabel().setText(Utils.zeroFill(String.valueOf(elapsedSeconds), 3));
        }
    }

    public static GameTimer getSharedInstance()
    {
        if (sharedInstance == null)
        {
            sharedInstance = new GameTimer();
        }

        return sharedInstance;
    }

    private GameTimer()
    {
        active = false;
    }

    public void init(MinesweeperForm minesweeperForm)
    {
        this.minesweeperForm = minesweeperForm;
    }

    public void start()
    {
        if (active)
        {
            return;
        }

        elapsedSeconds = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new Task(), 1000, 1000);
        active = true;

        System.gc();
    }

    public void stop()
    {
        if (!active)
        {
            return;
        }

        timer.cancel();
        active = false;
    }
}
