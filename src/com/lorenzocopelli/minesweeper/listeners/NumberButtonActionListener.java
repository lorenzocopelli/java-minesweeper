package com.lorenzocopelli.minesweeper.listeners;

import com.lorenzocopelli.minesweeper.MinesweeperForm;
import com.lorenzocopelli.minesweeper.buttons.NumberButton;
import com.lorenzocopelli.minesweeper.model.GameModel;
import com.lorenzocopelli.minesweeper.model.GameTimer;
import com.lorenzocopelli.minesweeper.utils.Pair;
import com.lorenzocopelli.minesweeper.utils.PairList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class NumberButtonActionListener implements ActionListener
{
    private final MinesweeperForm minesweeperForm;
    private final NumberButton numberButton;
    private final GameModel gameModel;

    public NumberButtonActionListener(MinesweeperForm minesweeperForm, NumberButton numberButton)
    {
        this.minesweeperForm = minesweeperForm;
        this.numberButton = numberButton;
        gameModel = GameModel.getSharedInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (numberButton.isFlag())
        {
            return;
        }

        GameTimer.getSharedInstance().start();

        int number = gameModel.getNumber(numberButton.getRow(), numberButton.getColumn());

        if (number == 0)
        {
            showNumberButtons();
        }
        else
        {
            showNumberButton(numberButton, number);
        }

        numberButton.removeActionListener(this);

        gameModel.setClickedNumbers(gameModel.getClickedNumbers() + 1);
        gameModel.setClicked(numberButton.getRow(), numberButton.getColumn());

        minesweeperForm.setDefaultEmojiIcon();

        if (gameModel.gameWon())
        {
            minesweeperForm.disableButtons();
            minesweeperForm.setWinnerEmojiIcon();
        }
    }

    private void showNumberButtons()
    {
        PairList<Integer, Integer> cellsToShow = gameModel.getCellsToShow(numberButton.getRow(), numberButton.getColumn());

        for (Pair<Integer, Integer> cell : cellsToShow)
        {
            NumberButton button = (NumberButton) minesweeperForm.getGridButtons()[cell.first()][cell.second()];

            int buttonNumber = gameModel.getNumber(button.getRow(), button.getColumn());
            showNumberButton(button, buttonNumber);
            button.setBorder(new LineBorder(Color.GRAY));
            button.setQuestionMark(false);
            button.removeActionListeners();
            button.removeMouseListeners();

            gameModel.setClicked(button.getRow(), button.getColumn());
        }

        minesweeperForm.repaint();

        gameModel.setClickedNumbers(gameModel.getClickedNumbers() + cellsToShow.size());
    }

    private void showNumberButton(NumberButton numberButton, int number)
    {
        URL numberIconURL = getClass().getClassLoader().getResource("numbers/" + number + ".png");

        if (numberIconURL != null)
        {
            numberButton.setIcon(new ImageIcon(numberIconURL));
            numberButton.setPressedIcon(new ImageIcon(numberIconURL));
        }

        minesweeperForm.repaint();
    }
}
