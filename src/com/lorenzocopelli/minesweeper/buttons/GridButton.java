package com.lorenzocopelli.minesweeper.buttons;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GridButton extends JButton
{
    private final int row;
    private final int column;
    private boolean isFlag;
    private boolean isQuestionMark;

    public GridButton(int row, int column)
    {
        setPreferredSize(new Dimension(30, 30));
        setBorder(new BevelBorder(BevelBorder.RAISED));

        this.row = row;
        this.column = column;
        isFlag = false;
        isQuestionMark = false;
    }

    public boolean isFlag()
    {
        return isFlag;
    }

    public void setFlag(boolean flag)
    {
        isFlag = flag;
    }

    public boolean isQuestionMark()
    {
        return isQuestionMark;
    }

    public void setQuestionMark(boolean questionMark)
    {
        isQuestionMark = questionMark;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public void removeActionListeners()
    {
        ActionListener[] actionListeners = this.getActionListeners();

        for (ActionListener actionListener : actionListeners)
        {
            this.removeActionListener(actionListener);
        }
    }

    public void removeMouseListeners()
    {
        MouseListener[] mouseListeners = this.getMouseListeners();

        for (MouseListener mouseListener : mouseListeners)
        {
            this.removeMouseListener(mouseListener);
        }
    }
}
