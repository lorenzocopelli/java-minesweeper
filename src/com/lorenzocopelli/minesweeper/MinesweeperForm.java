package com.lorenzocopelli.minesweeper;

import com.lorenzocopelli.minesweeper.buttons.GridButton;
import com.lorenzocopelli.minesweeper.buttons.MineButton;
import com.lorenzocopelli.minesweeper.buttons.NumberButton;
import com.lorenzocopelli.minesweeper.listeners.*;
import com.lorenzocopelli.minesweeper.model.Constants;
import com.lorenzocopelli.minesweeper.model.GameModel;
import com.lorenzocopelli.minesweeper.model.GameTimer;
import com.lorenzocopelli.minesweeper.utils.Pair;
import com.lorenzocopelli.minesweeper.utils.PairList;
import com.lorenzocopelli.minesweeper.utils.Utils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MinesweeperForm extends JFrame
{
    private JPanel contentPane;
    private JLabel timeLabel;
    private JLabel minesLabel;
    private JButton emojiButton;
    private JPanel gameGrid;
    private final GameModel gameModel;
    private GridButton[][] gridButtons;

    public MinesweeperForm(String title)
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setResizable(false);

        gameModel = GameModel.getSharedInstance();
        GameTimer.getSharedInstance().init(this);

        setLabels();
        setEmojiButton();
        setGameGrid();

        pack();
        setLocationByPlatform(true);

        minesLabel.setText(Utils.zeroFill(String.valueOf(Constants.numMines), 3));
    }

    private void setLabels()
    {
        minesLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        URL fontURL = getClass().getClassLoader().getResource("font/digital-7-mono.ttf");

        if (fontURL == null)
        {
            return;
        }

        File fontFile = new File(fontURL.getFile());

        try
        {
            minesLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(48f));
            timeLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(48f));
        }
        catch (FontFormatException | IOException ignored) {}
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
        EmojiButtonActionListener actionListener = new EmojiButtonActionListener(this);
        emojiButton.addActionListener(actionListener);
    }

    public JLabel getTimeLabel()
    {
        return timeLabel;
    }

    public void setGameGrid()
    {
        clearButtons();
        gridButtons = new GridButton[Constants.numRows][Constants.numColumns];
        gameModel.newGame();

        GridBagConstraints gbc = new GridBagConstraints();

        for (int row = 0; row < Constants.numRows; ++row)
        {
            for (int column = 0; column < Constants.numColumns; ++column)
            {
                GridButton gridButton;

                if (gameModel.isMine(row, column))
                {
                    gridButton = new MineButton(row, column);
                    MineButtonActionListener actionListener = new MineButtonActionListener(this, (MineButton) gridButton);
                    gridButton.addActionListener(actionListener);
                }
                else
                {
                    gridButton = new NumberButton(row, column);
                    NumberButtonActionListener actionListener = new NumberButtonActionListener(this, (NumberButton) gridButton);
                    gridButton.addActionListener(actionListener);
                }

                GridButtonMouseListener mouseListener = new GridButtonMouseListener(this, gridButton);
                gridButton.addMouseListener(mouseListener);

                gbc.gridy = row;
                gbc.gridx = column;
                gameGrid.add(gridButton, gbc);
                gridButtons[row][column] = gridButton;
            }
        }

        repaint();
        pack();
    }

    private void clearButtons()
    {
        if (gridButtons == null)
        {
            return;
        }

        for (int i = 0; i < Constants.numRows; ++i)
        {
            for (int j = 0; j < Constants.numColumns; ++j)
            {
                gameGrid.remove(gridButtons[i][j]);
                gridButtons[i][j] = null;
            }
        }

        System.gc();
    }

    public GridButton[][] getGridButtons()
    {
        return gridButtons;
    }

    public void disableButtons()
    {
        for (int i = 0; i < Constants.numRows; ++i)
        {
            for (int j = 0; j < Constants.numColumns; ++j)
            {
                gridButtons[i][j].removeMouseListeners();
                gridButtons[i][j].removeActionListeners();
            }
        }
    }

    public void showMineButtons()
    {
        PairList<Integer, Integer> minesCells = gameModel.getMines();
        URL mineIconURL = getClass().getClassLoader().getResource("items/mine.png");

        if (mineIconURL == null)
        {
            return;
        }

        for (Pair<Integer, Integer> mineCell : minesCells)
        {
            GridButton gridButton = gridButtons[mineCell.first()][mineCell.second()];
            gridButton.setIcon(new ImageIcon(mineIconURL));
            gridButton.setPressedIcon(new ImageIcon(mineIconURL));
            gridButton.setBorder(new LineBorder(Color.GRAY));
        }

        repaint();
    }

    public void setDefaultEmojiIcon()
    {
        URL emojiIconURL = getClass().getClassLoader().getResource("emoji/emoji-1.png");

        if (emojiIconURL != null)
        {
            emojiButton.setIcon(new ImageIcon(emojiIconURL));
            emojiButton.setPressedIcon(new ImageIcon(emojiIconURL));
        }
    }

    public void setWinnerEmojiIcon()
    {
        URL emojiIconURL = getClass().getClassLoader().getResource("emoji/emoji-4.png");

        if (emojiIconURL != null)
        {
            emojiButton.setIcon(new ImageIcon(emojiIconURL));
            emojiButton.setPressedIcon(new ImageIcon(emojiIconURL));
        }
    }

    public void setLoserEmojiIcon()
    {
        URL emojiIconURL = getClass().getClassLoader().getResource("emoji/emoji-3.png");

        if (emojiIconURL != null)
        {
            emojiButton.setIcon(new ImageIcon(emojiIconURL));
            emojiButton.setPressedIcon(new ImageIcon(emojiIconURL));
        }
    }
}
