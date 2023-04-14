package com.lorenzocopelli.minesweeper;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.net.URL;

public class GridButtonChangeListener implements ChangeListener
{
    private final MinesweeperForm minesweeperForm;
    private final GridButton gridButton;

    public GridButtonChangeListener(MinesweeperForm minesweeperForm, GridButton gridButton)
    {
        this.minesweeperForm = minesweeperForm;
        this.gridButton = gridButton;
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        ButtonModel buttonModel = gridButton.getModel();

        if (buttonModel.isArmed())
        {
            gridButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
            URL emojiIconUrl = getClass().getClassLoader().getResource("emoji/emoji-2.png");

            if (emojiIconUrl != null)
            {
                minesweeperForm.getEmojiButton().setIcon(new ImageIcon(emojiIconUrl));
            }
        }
        else
        {
            gridButton.setBorder(new BevelBorder(BevelBorder.RAISED));
            URL emojiIconUrl = getClass().getClassLoader().getResource("emoji/emoji-1.png");

            if (emojiIconUrl != null)
            {
                minesweeperForm.getEmojiButton().setIcon(new ImageIcon(emojiIconUrl));
            }
        }
    }
}
