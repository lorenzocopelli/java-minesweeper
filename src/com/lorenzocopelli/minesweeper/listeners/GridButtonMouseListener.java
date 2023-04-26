package com.lorenzocopelli.minesweeper.listeners;

import com.lorenzocopelli.minesweeper.MinesweeperForm;
import com.lorenzocopelli.minesweeper.buttons.GridButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GridButtonMouseListener implements MouseListener
{
    private final MinesweeperForm minesweeperForm;
    private final GridButton gridButton;

    public GridButtonMouseListener(MinesweeperForm minesweeperForm, GridButton gridButton)
    {
        this.minesweeperForm = minesweeperForm;
        this.gridButton = gridButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (SwingUtilities.isRightMouseButton(e))
        {
            if (gridButton.isQuestionMark())
            {
                gridButton.setIcon(null);
                gridButton.setQuestionMark(false);
                return;
            }

            if (gridButton.isFlag())
            {
                URL iconURL = getClass().getClassLoader().getResource("items/question-mark.png");

                if (iconURL != null)
                {
                    gridButton.setIcon(new ImageIcon(iconURL));
                    gridButton.setPressedIcon(new ImageIcon(iconURL));
                }

                gridButton.setFlag(false);
                gridButton.setQuestionMark(true);
                return;
            }

            URL iconURL = getClass().getClassLoader().getResource("items/flag.png");

            if (iconURL != null)
            {
                gridButton.setIcon(new ImageIcon(iconURL));
                gridButton.setPressedIcon(new ImageIcon(iconURL));
            }

            gridButton.setFlag(true);
        }
        else
        {
            if (gridButton.isFlag())
            {
                return;
            }

            URL emojiIconURL = getClass().getClassLoader().getResource("emoji/emoji-2.png");

            if (emojiIconURL != null)
            {
                minesweeperForm.getEmojiButton().setIcon(new ImageIcon(emojiIconURL));
                minesweeperForm.getEmojiButton().setPressedIcon(new ImageIcon(emojiIconURL));
            }

            gridButton.setBorder(new LineBorder(Color.GRAY));
            gridButton.setQuestionMark(false);
            gridButton.removeMouseListener(this);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
