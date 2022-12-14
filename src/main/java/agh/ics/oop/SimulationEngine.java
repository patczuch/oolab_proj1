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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SimulationEngine implements Runnable{
    public final AbstractWorldMap map;
    private final ArrayList<Animal> animals;
    private final ArrayList<Plant> plants;
    private final SimulationConfig config;
    private final SimulationStage stage;
    private boolean stop = false;
    Random rand;
    public SimulationEngine(SimulationConfig config, Random rand, SimulationStage stage)
    {
        this.config = config;
        this.map = new Earth(config.mapWidth,config.mapHeight,rand);
        this.stage = stage;
        this.rand = rand;

        animals = new ArrayList<>();
        for (int i = 0; i < config.startAnimalNumber; i++)
            animals.add(createRandomAnimal());

        plants = new ArrayList<>();
        for (int i = 0; i < config.plantNumber; i++)
            plants.add(createRandomPlant());

        Platform.runLater(() -> {
            for (Animal a: animals)
                stage.update(a.getPosition());
            for (Plant p: plants)
                stage.update(p.getPosition());
        });
    }

    private Plant createRandomPlant()
    {
        Vector2d newPosition = map.randomPosition();

        if (this.config.plantGrowingType == SimulationTypes.PlantGrowingType.FORESTYEQUATORS) {
            boolean preferable = Math.random() <= 0.8;
            while (map.isPlantAt(newPosition) || map.isPreferableForPlants(newPosition) != preferable)
                newPosition = map.randomPosition();
        }
        while (map.isPlantAt(newPosition))
            newPosition = map.randomPosition();
        return new Plant(map,newPosition);
    }

    private Animal createRandomAnimal() {
        return new Animal(map, map.randomPosition(),rand,config.animalGenesLength);
    }

    @Override
    public void run() {
        HashSet<Vector2d> toUpdate = new HashSet<>();
        while (true) {
            toUpdate.clear();
            for (Animal a : animals) {
                toUpdate.add(a.getPosition());
                a.move();
                toUpdate.add(a.getPosition());
            }
            if (stop)
                return;
            Platform.runLater(() -> {
                for (Vector2d v : toUpdate)
                    stage.update(v);
            });
            try {
                Thread.sleep(config.moveDelay);
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted");
                stop = true;
            }
        }
    }
}
