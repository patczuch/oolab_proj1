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
import java.util.Random;

public class SimulationEngine implements Runnable{
    private MoveDirection[] moves;
    public final AbstractWorldMap map;
    private final ArrayList<Animal> animals;
    public final int moveDelay;
    private final SimulationStage stage;
    private boolean stop = false;
    Random random;
    public SimulationEngine(SimulationConfig config, Random random, SimulationStage stage)
    {
        this.map = new Earth(config.mapWidth,config.mapHeight,random);
        this.stage = stage;
        this.moveDelay = config.moveDelay;
        this.random = random;
        animals = new ArrayList<>();
        moves = OptionsParser.parse("f f f f f f f f f f f r r f f f f l l r r r r f f f f f f f f f l f f f f f f".split(" "));
        Vector2d[] initialPositions = {new Vector2d(1,1), new Vector2d(2,2)};
        for (Vector2d pos : initialPositions) {
            Animal a = new Animal(map, pos);
            animals.add(a);
        }
        for (int i = 0; i < config.plantNumber; i++) {
            Plant plant = map.createRandomPlant();
            Platform.runLater(() -> {stage.update(plant.getPosition());});
        }
    }

    @Override
    public void run() {
        int i = 0;
        for (MoveDirection moveDir : moves)
        {
            Vector2d previousPos = animals.get(i).getPosition();
            animals.get(i).move(moveDir);
            update(previousPos, animals.get(i).getPosition());
            i++;
            if (i >= animals.size())
                i = 0;
            if (stop)
                return;
        }
    }

    private void update(Vector2d prevPos, Vector2d currPos)
    {
        Platform.runLater(() -> {stage.update(prevPos);stage.update(currPos);});
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted");
            stop = true;
        }
    }
}
