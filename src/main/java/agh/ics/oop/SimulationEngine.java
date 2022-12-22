package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.SimulationStage;
import javafx.application.Platform;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import java.lang.Thread;

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

            TreeSet<Animal> set = new TreeSet<>((a1, a2) -> {
                if (a1.equals(a2))
                    return 0;
                if (a1.getEnergy() != a2.getEnergy())
                    return a2.getEnergy() - a1.getEnergy();
                if (a1.getAge() != a2.getAge())
                    return a2.getAge() - a1.getAge();
                if (a1.getChildrenAmount() != a2.getChildrenAmount())
                    return a2.getChildrenAmount() - a1.getChildrenAmount();
                return a1.toString().compareTo(a2.toString());
            });

            for (Animal a : animals)
            {
                if (map.isPlantAt(a.getPosition())) {
                    set.clear();
                    set.addAll(map.animalsAt(a.getPosition()));
                    set.first().addEnergy(config.plantEnergy);
                    map.plantAt(a.getPosition()).die();
                    toUpdate.add(a.getPosition());
                }
            }

            for (int y = 0; y <= map.getUpperRight().subtract(map.getLowerLeft()).y; y++){
                for (int x = 0; x <= map.getUpperRight().subtract(map.getLowerLeft()).x; x++) {
                    Vector2d v = new Vector2d(x, y);
                    if (map.animalsAt(v) != null) {
                        set.clear();
                        set.addAll(map.animalsAt(v));
                    }
                    set.removeIf(a -> a.getEnergy() < config.fedAnimalEnergy);
                    if (set.size() >= 2) {
                        Animal a1 = set.first();
                        set.remove(a1);
                        Animal child = map.spawnBredAnimal(a1,set.first());
                        animals.add(child);
                        child.addPosObserver(this);
                        child.addDeathObserver(this);
                        toUpdate.add(v);
                        //System.out.println("New animal!");
                    }
                }
            }

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
