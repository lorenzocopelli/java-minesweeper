package com.lorenzocopelli.minesweeper;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class GridButton extends JButton
{
    public GridButton()
    {
        setPreferredSize(new Dimension(30, 30));
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }
}
