package com.lorenzocopelli.minesweeper;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MinesweeperForm extends JFrame
{
    private JPanel contentPane;
    private JLabel timeLabel;
    private JLabel minesLabel;
    private JButton emojiButton;
    private JPanel gameGrid;

    public MinesweeperForm(String title)
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setResizable(false);

        setEmojiButton();
        setGameGrid();

        pack();
        setLocationRelativeTo(null);
    }

    public JButton getEmojiButton()
    {
        return emojiButton;
    }

    private void setEmojiButton()
    {
        emojiButton.setBorder(new BevelBorder(BevelBorder.RAISED));

        EmojiButtonChangeListener changeListener = new EmojiButtonChangeListener(this);
        emojiButton.getModel().addChangeListener(changeListener);
    }

    private void setGameGrid()
    {
        GridBagConstraints gbc = new GridBagConstraints();

        for (int row = 0; row < Constants.numRows; ++row)
        {
            for (int column = 0; column < Constants.numColumns; ++column)
            {
                GridButton gridButton = new GridButton();
                GridButtonChangeListener changeListener = new GridButtonChangeListener(this, gridButton);
                gridButton.getModel().addChangeListener(changeListener);

                gbc.gridy = row;
                gbc.gridx = column;
                gameGrid.add(gridButton, gbc);
            }
        }
    }
}
