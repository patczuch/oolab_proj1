package agh.ics.oop;

import java.util.HashMap;
import java.util.Random;

public class Earth extends AbstractWorldMap{

    public Earth(int width, int height, int grassAmount)
    {
        this(width, height, grassAmount, new Random());
    }

    public Earth(int width, int height, int grassAmount, Random rand)
    {
        super(width, height,grassAmount,rand);

    }
}
