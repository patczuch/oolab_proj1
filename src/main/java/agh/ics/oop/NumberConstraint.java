package agh.ics.oop;

public class NumberConstraint {
    public final int min;
    public final int max;
    public NumberConstraint(int min, int max)
    {
        if (min > max)
            throw new RuntimeException("Minimum value higher than maximum in number constraint!");
        this.min = min;
        this.max = max;
    }
}
