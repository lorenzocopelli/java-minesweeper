package com.lorenzocopelli.minesweeper.listeners;

import com.lorenzocopelli.minesweeper.MinesweeperForm;
import com.lorenzocopelli.minesweeper.buttons.MineButton;
import com.lorenzocopelli.minesweeper.model.GameModel;
import com.lorenzocopelli.minesweeper.model.GameTimer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MineButtonActionListener implements ActionListener
{
    private final MinesweeperForm minesweeperForm;
    private final MineButton mineButton;
    private final GameModel gameModel;

    public MineButtonActionListener(MinesweeperForm minesweeperForm, MineButton gridButton)
    {
        this.minesweeperForm = minesweeperForm;
        this.mineButton = gridButton;
        gameModel = GameModel.getSharedInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (mineButton.isFlag())
        {
            return;
        }

        GameTimer.getSharedInstance().stop();

        minesweeperForm.showMineButtons();
        showMineButton();
        minesweeperForm.disableButtons();
        minesweeperForm.setLoserEmojiIcon();

        gameModel.setClicked(mineButton.getRow(), mineButton.getColumn());
        mineButton.removeActionListener(this);
    }

    private void showMineButton()
    {
        URL mineIconURL = getClass().getClassLoader().getResource("items/exploded-mine.png");

        if (mineIconURL != null)
        {
            mineButton.setIcon(new ImageIcon(mineIconURL));
            mineButton.setPressedIcon(new ImageIcon(mineIconURL));
        }

        minesweeperForm.repaint();
    }
}
