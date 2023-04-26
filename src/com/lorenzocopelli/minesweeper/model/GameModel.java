package com.lorenzocopelli.minesweeper.model;

/*
 * In gameGrid, -1 indicates a mine.
 * -2, indicates that a cell was clicked.
 * Other numbers, from 0 to 8, indicate normal numbers.
 */

import com.lorenzocopelli.minesweeper.utils.Pair;
import com.lorenzocopelli.minesweeper.utils.PairList;

import java.util.*;

public class GameModel
{
    private static GameModel instance;

    private int[][] gameGrid;
    private int clickedNumbers;

    private GameModel()
    {
        newGame();
    }

    public static GameModel getSharedInstance()
    {
        if (instance == null)
        {
            instance = new GameModel();
        }

        return instance;
    }

    public boolean isMine(int row, int column)
    {
        return gameGrid[row][column] == -1;
    }

    public void newGame()
    {
        gameGrid = new int[Constants.numRows][Constants.numColumns];
        clickedNumbers = 0;

        initGrid();
    }

    private void initGrid()
    {
        int numMines = 0;
        Random random = new Random();

        do
        {
            int row = random.nextInt(Constants.numRows);
            int column = random.nextInt(Constants.numColumns);

            if (gameGrid[row][column] != -1)
            {
                // Insert the mine.
                gameGrid[row][column] = -1;
                ++numMines;

                // Updates neighborhood numbers.
                if (isValidCoordinate(row - 1, column - 1) && !isMine(row - 1, column - 1))
                {
                    ++gameGrid[row - 1][column - 1];
                }
                if (isValidCoordinate(row - 1, column) && !isMine(row - 1, column))
                {
                    ++gameGrid[row - 1][column];
                }
                if (isValidCoordinate(row - 1, column + 1) && !isMine(row - 1, column + 1))
                {
                    ++gameGrid[row - 1][column + 1];
                }
                if (isValidCoordinate(row, column - 1) && !isMine(row, column - 1))
                {
                    ++gameGrid[row][column - 1];
                }
                if (isValidCoordinate(row, column + 1) && !isMine(row, column + 1))
                {
                    ++gameGrid[row][column + 1];
                }
                if (isValidCoordinate(row + 1, column - 1) && !isMine(row + 1, column - 1))
                {
                    ++gameGrid[row + 1][column - 1];
                }
                if (isValidCoordinate(row + 1, column) && !isMine(row + 1, column))
                {
                    ++gameGrid[row + 1][column];
                }
                if (isValidCoordinate(row + 1, column + 1) && !isMine(row + 1, column + 1))
                {
                    ++gameGrid[row + 1][column + 1];
                }
            }
        }
        while (numMines < Constants.numMines);
    }

    private boolean isValidCoordinate(int row, int column)
    {
        return row >= 0 && row < Constants.numRows &&
                column >= 0 && column < Constants.numColumns;
    }

    public int getNumber(int row, int column)
    {
        return gameGrid[row][column];
    }

    public int getClickedNumbers()
    {
        return clickedNumbers;
    }

    public void setClickedNumbers(int clickedNumbers)
    {
        this.clickedNumbers = clickedNumbers;
    }

    private boolean hasBeenClicked(int row, int column)
    {
        return isValidCoordinate(row, column) && gameGrid[row][column] == -2;
    }

    public void setClicked(int row, int column)
    {
        if (isValidCoordinate(row, column))
        {
            gameGrid[row][column] = -2;
        }
    }

    public PairList<Integer, Integer> getMines()
    {
        PairList<Integer, Integer> mines = new PairList<>();

        for (int row = 0; row < Constants.numRows; ++row)
        {
            for (int column = 0; column < Constants.numColumns; ++column)
            {
                if (isMine(row, column))
                {
                    mines.add(new Pair<>(row, column));
                }
            }
        }

        return mines;
    }

    public PairList<Integer, Integer> getCellsToShow(int row, int column)
    {
        PairList<Integer, Integer> cellsToClear = new PairList<>();
        searchCellsToClear(row, column, cellsToClear);
        cellsToClear.remove(0);
        return cellsToClear;
    }

    private void searchCellsToClear(int row, int column, PairList<Integer, Integer> cellsToClear)
    {
        if (!isValidCoordinate(row, column))
        {
            return;
        }

        Pair<Integer, Integer> cell = new Pair<>(row, column);

        if (cellsToClear.containsPair(cell))
        {
            return;
        }

        if (hasBeenClicked(row, column))
        {
            return;
        }

        cellsToClear.add(cell);

        if (gameGrid[row][column] == 0)
        {
            searchCellsToClear(row - 1, column - 1, cellsToClear);
            searchCellsToClear(row - 1, column, cellsToClear);
            searchCellsToClear(row - 1, column + 1, cellsToClear);
            searchCellsToClear(row, column - 1, cellsToClear);
            searchCellsToClear(row, column + 1, cellsToClear);
            searchCellsToClear(row + 1, column - 1, cellsToClear);
            searchCellsToClear(row + 1, column, cellsToClear);
            searchCellsToClear(row + 1, column + 1, cellsToClear);
        }
    }

    public boolean gameWon()
    {
        return getClickedNumbers() == Constants.numRows * Constants.numColumns - Constants.numMines;
    }
}
