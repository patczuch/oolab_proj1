package agh.ics.oop;

public class AverageCalculator {
    private int sum = 0;
    private int number = 0;

    public void add(int value) {
        sum += value;
        number++;
    }

    public double getAverage() {
        if (number == 0)
            return 0;
        return (double) sum / number;
    }

    public void clear() {
        sum = 0;
        number = 0;
    }
}
