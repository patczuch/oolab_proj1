package agh.ics.oop;

public class NumberConstraint {
    public final int min;
    public final int max;  // ta klasa nic nie wnosi

    public NumberConstraint(int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("Minimum value higher than maximum in number constraint!");  // typ wyjątku
        this.min = min;
        this.max = max;
    }
}
