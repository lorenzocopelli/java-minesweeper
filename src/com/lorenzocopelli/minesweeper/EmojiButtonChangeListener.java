package com.lorenzocopelli.minesweeper;

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
        ButtonModel buttonModel = minesweeperForm.getEmojiButton().getModel();

        if (buttonModel.isArmed())
        {
            minesweeperForm.getEmojiButton().setBorder(new BevelBorder(BevelBorder.LOWERED));
        }
        else
        {
            minesweeperForm.getEmojiButton().setBorder(new BevelBorder(BevelBorder.RAISED));
        }
    }
}
