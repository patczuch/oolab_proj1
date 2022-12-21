package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.SimulationStage;
import javafx.application.Platform;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import java.lang.Thread;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SimulationEngine implements Runnable, IPositionChangeObserver, IDeathObserver{
    public final AbstractWorldMap map;
    private final ArrayList<Animal> animals;
    private final ArrayList<Plant> plants;
    private final SimulationConfig config;
    private final SimulationStage stage;
    private boolean stop = false;
    private final HashSet<Vector2d> toUpdate;
    Random rand;
    public SimulationEngine(SimulationConfig config, Random rand, SimulationStage stage)
    {
        toUpdate = new HashSet<>();
        this.config = config;
        switch(config.mapType)
        {
            case EARTH -> this.map = new Earth(config,rand);
            case HELLPORTAL -> this.map = new HellPortal(config,rand);
            default -> this.map = new Earth(config,rand);
        }
        this.stage = stage;
        this.rand = rand;

        animals = new ArrayList<>();
        for (int i = 0; i < config.startAnimalNumber; i++) {
            Animal a = map.spawnRandomAnimal();
            animals.add(a);
            a.addPosObserver(this);
            a.addDeathObserver(this);
            toUpdate.add(a.getPosition());
        }

        plants = new ArrayList<>();
        for (int i = 0; i < config.plantNumber; i++){
            Plant p = map.spawnRandomPlant();
            if (p != null) {
                plants.add(p);
                p.addDeathObserver(this);
                toUpdate.add(p.getPosition());
            }
        }

        Platform.runLater(() -> {
            for (Vector2d v : toUpdate)
                stage.update(v);
            toUpdate.clear();
            stage.updateBackground();
        });
    }

    @Override
    public void run() {
        while (true) {
            if (stop)
                return;

            for (int i = 0; i<animals.size(); i++)
                if (animals.get(i).getEnergy() <= 0) {
                    animals.get(i).die();
                    i--;
                }

            for (Animal a : animals)
                a.move();

            for (Animal a : animals)
            {
                if (map.isPlantAt(a.getPosition())) {
                    a.addEnergy(config.plantEnergy);
                    map.plantAt(a.getPosition()).die();
                }
            }

            //TODO BREEDING

            for(int i=0; i<config.plantGrowingNumber; i++) {
                Plant p = map.spawnRandomPlant();
                if (p != null) {
                    plants.add(p);
                    p.addDeathObserver(this);
                    toUpdate.add(p.getPosition());
                }
            }

            Platform.runLater(() -> {
                for (Vector2d v : toUpdate)
                    stage.update(v);
                toUpdate.clear();
                stage.updateBackground();
            });

            try {
                Thread.sleep(config.moveDelay);
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted");
                stop = true;
            }
        }
    }

    public void died(Animal a) {
        animals.remove(a);
        toUpdate.add(a.getPosition());
    }

    public void died(Plant p) {
        plants.remove(p);
        toUpdate.add(p.getPosition());
    }

    public void positionChanged(Animal a, Vector2d oldPosition) {
        toUpdate.add(oldPosition);
        toUpdate.add(a.getPosition());
    }
}
