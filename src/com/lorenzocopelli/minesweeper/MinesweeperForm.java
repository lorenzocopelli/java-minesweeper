package com.lorenzocopelli.minesweeper;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class MinesweeperForm extends JFrame
{
    private JPanel contentPane;
    private JLabel timeLabel;
    private JLabel bombsLabel;
    private JButton emojiButton;

    public MinesweeperForm(String title)
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        setEmojiButton();
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
}
