package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;

import java.lang.Thread;

public class SimulationEngine implements Runnable{
    private MoveDirection[] moves;
    private final AbstractWorldMap map;
    private final ArrayList<Animal> animals;
    public final int moveDelay;
    private final App app;
    private final Vector2d[] initialPositions;
    private boolean stop = false;

    public SimulationEngine(AbstractWorldMap map, Vector2d[] initialPositions, App app, int moveDelay)
    {
        this.map = map;
        this.app = app;
        this.initialPositions = initialPositions;
        this.moveDelay = moveDelay;
        animals = new ArrayList<>();
    }
    public SimulationEngine(MoveDirection[] moves, AbstractWorldMap map, Vector2d[] initialPositions, App app, int moveDelay)
    {
        this(map,initialPositions,app, moveDelay);
        this.moves = moves;
        setInitialPositions();
    }

    private void setInitialPositions()
    {
        animals.clear();
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
        Platform.runLater(app::update);
        //System.out.println(map);
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted");
            stop = true;
        }
    }

    public void changeMoves(MoveDirection[] moves)
    {
        setInitialPositions();
        this.moves = moves;
    }

}
