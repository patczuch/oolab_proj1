package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class Main {
    public static void main(String[] args)
    {
        System.out.println(
                MapDirection.EAST.previous()
        );
        System.out.println(
                MapDirection.values()[1]
        );
        Application.launch(App.class, args);
    }
}
