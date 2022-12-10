package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.SimulationStage;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import java.lang.Thread;

public class SimulationEngine implements Runnable{
    private MoveDirection[] moves;
    private final AbstractWorldMap map;
    private final ArrayList<Animal> animals;
    public final int moveDelay;
    private final SimulationStage stage;
    private boolean stop = false;
    public SimulationEngine(MoveDirection[] moves, AbstractWorldMap map, Vector2d[] initialPositions, int moveDelay, SimulationStage stage)
    {
        this.map = map;
        this.stage = stage;
        this.moveDelay = moveDelay;
        this.moves = moves;
        animals = new ArrayList<>();
        for (Vector2d pos : initialPositions) {
            Animal a = new Animal(map, pos);
            animals.add(a);
        }
    }

    @Override
    public void run() {
        update();
        int i = 0;
        for (MoveDirection moveDir : moves)
        {
            animals.get(i).move(moveDir);
            i++;
            if (i >= animals.size())
                i = 0;
            update();
            if (stop)
                return;
        }
    }

    private void update()
    {
        Platform.runLater(stage::update);
        //System.out.println(map);
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted");
            stop = true;
        }
    }
}
