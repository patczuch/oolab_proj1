package agh.ics.oop.gui;

import agh.ics.oop.NumberConstraint;
import javafx.scene.control.TextField;
import java.math.BigInteger;

public class NumberField extends TextField {

    public final int defaultValue;
    public NumberField()
    {
        this(0);
    }
    public NumberField(int startingValue)
    {
        this(startingValue,new NumberConstraint(Integer.MIN_VALUE,Integer.MAX_VALUE));
    }
    public NumberField(NumberConstraint constraint) {
        this(constraint.min,constraint);
    }
    public NumberField(int startingValue, NumberConstraint constraint) {
        super(Integer.toString(startingValue));
        if (startingValue < constraint.min || startingValue > constraint.max)
            throw new RuntimeException("Starting value of number field doesn't fit in the constraints!");
        defaultValue = startingValue;
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^-?\\d*$"))
                setText(newValue.replaceAll("[^-\\d]", ""));
            if (newValue.length() > 0 && newValue.charAt(0) == '-')
                setText('-'+newValue.replaceAll("[^\\d]", ""));
            if (getText().length() > 0 && !getText().equals("-"))
                if (new BigInteger(getText()).compareTo(new BigInteger(Integer.toString(Integer.MAX_VALUE))) > 0)
                    setText(Integer.toString(Integer.MAX_VALUE));
                else if (new BigInteger(getText()).compareTo(new BigInteger(Integer.toString(Integer.MIN_VALUE))) < 0)
                    setText(Integer.toString(Integer.MIN_VALUE));
            if (getValue() < constraint.min)
                setText(Integer.toString(constraint.min));
            if (getValue() > constraint.max)
                setText(Integer.toString(constraint.max));
        });
    }
    public int getValue()
    {
        if (getText().equals("") || getText().equals("-"))
            return defaultValue;
        return Integer.parseInt(getText());
    }

    public void setNumber(int n)
    {
        setText(String.valueOf(n));
    }
}
