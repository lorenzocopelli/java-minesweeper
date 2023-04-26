package com.lorenzocopelli.minesweeper.utils;

public class Utils
{
    public static String zeroFill(String string, int length)
    {
        if (string.length() >= length)
        {
            return string;
        }

        StringBuilder stringBuilder = new StringBuilder();

        while (stringBuilder.length() < length - string.length())
        {
            stringBuilder.append('0');
        }

        stringBuilder.append(string);

        return stringBuilder.toString();
    }
}
