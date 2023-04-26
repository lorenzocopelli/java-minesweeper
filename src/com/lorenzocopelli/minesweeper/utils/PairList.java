package com.lorenzocopelli.minesweeper.utils;

import java.util.ArrayList;

public class PairList<T1, T2> extends ArrayList<Pair<T1, T2>>
{
    public boolean containsPair(Pair<T1, T2> pair)
    {
        for (Pair<T1, T2> item : this)
        {
            if (item.first().equals(pair.first()) &&
                item.second().equals(pair.second()))
            {
                return true;
            }
        }

        return false;
    }
}
