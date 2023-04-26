package com.lorenzocopelli.minesweeper.listeners;

import com.lorenzocopelli.minesweeper.MinesweeperForm;
import com.lorenzocopelli.minesweeper.model.GameTimer;
import com.lorenzocopelli.minesweeper.utils.Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmojiButtonActionListener implements ActionListener
{
    private final MinesweeperForm minesweeperForm;

    public EmojiButtonActionListener(MinesweeperForm minesweeperForm)
    {
        this.minesweeperForm = minesweeperForm;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        GameTimer.getSharedInstance().stop();
        minesweeperForm.getTimeLabel().setText(Utils.zeroFill(String.valueOf(0), 3));

        minesweeperForm.setGameGrid();
        minesweeperForm.setDefaultEmojiIcon();
    }
}
