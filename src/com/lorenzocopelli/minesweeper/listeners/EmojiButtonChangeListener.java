package com.lorenzocopelli.minesweeper.listeners;

import com.lorenzocopelli.minesweeper.MinesweeperForm;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EmojiButtonChangeListener implements ChangeListener
{
    private final MinesweeperForm minesweeperForm;

    public EmojiButtonChangeListener(MinesweeperForm minesweeperForm)
    {
        this.minesweeperForm = minesweeperForm;
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        JButton emojiButton = minesweeperForm.getEmojiButton();

        if (emojiButton.getModel().isArmed())
        {
            emojiButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
        }
        else
        {
            emojiButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        }
    }
}
