package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.SimulationStage;
import javafx.application.Platform;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import java.lang.Thread;

public class SimulationEngine implements Runnable, IPositionChangeObserver, IDeathObserver{
    public final AbstractWorldMap map;
    // Only alive animals are stored
    private final ArrayList<Animal> animals;
    private final ArrayList<Plant> plants;
    private final SimulationConfig config;
    private final SimulationStage stage;
    private boolean stop = false;
    private boolean paused = true;
    private final ArrayList<Vector2d> toUpdate;
    private int currentDay = 0;
    private int totalAnimalCounter = 0; // Including the dead ones
    private final HashMap<MoveDirection, Integer> genesPopularity = new HashMap<>();
    private String mostPopularGenes = null;
    AverageCalculator averageLifeSpan = new AverageCalculator();
    AverageCalculator averageEnergyLevel = new AverageCalculator();
    Random rand;

    public SimulationEngine(SimulationConfig config, Random rand, SimulationStage stage)
    {
        toUpdate = new ArrayList<>();
        this.config = config;
        switch(config.mapType)
        {
//            case EARTH -> this.map = new Earth(config,rand);
            case HELLPORTAL -> this.map = new HellPortal(config,rand);
            default -> this.map = new Earth(config,rand);
        }
        this.stage = stage;
        this.rand = rand;

        for (MoveDirection dir : MoveDirection.values())
            genesPopularity.put(dir, 0);

        animals = new ArrayList<>();
        for (int i = 0; i < config.startAnimalNumber; i++) {
            Animal a = map.spawnRandomAnimal();
            placeAnimal(a);
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
    }

    @Override
    public void run() {
        while (true) {
            if (stop)
                return;
            currentDay++;

            Platform.runLater(() -> {
                ArrayList<Vector2d> updated = new ArrayList<>();
                for (int i = 0; i<toUpdate.size(); i++) {
                    Vector2d v = toUpdate.get(i);
                    stage.update(v);
                    updated.add(v);
                }
                toUpdate.removeAll(updated);
                stage.updateBackground();
            });

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

            averageEnergyLevel.clear();
            for (Animal a : animals)
            {
                if (map.isPlantAt(a.getPosition())) {
                    set.clear();
                    set.addAll(map.animalsAt(a.getPosition()));
                    set.first().addEnergy(config.plantEnergy);
                    map.plantAt(a.getPosition()).die();
                    toUpdate.add(a.getPosition());
                }
                averageEnergyLevel.add(a.getEnergy());
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
                        Animal a = set.first();
                        set.remove(a);
                        Animal child = map.spawnBredAnimal(a,set.first());
                        placeAnimal(child);
                    }
                }
            }

            for(int i = 0; i < config.plantGrowingNumber; i++) {
                Plant p = map.spawnRandomPlant();
                if (p != null) {
                    plants.add(p);
                    p.addDeathObserver(this);
                    toUpdate.add(p.getPosition());
                }
            }

            try {
                while (paused)
                    Thread.sleep(500);
                Thread.sleep(config.moveDelay);
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted");
                stop = true;
            }
        }
    }

    public void placeAnimal(Animal animal) {
        animals.add(animal);
        animal.addPosObserver(this);
        animal.addDeathObserver(this);

        totalAnimalCounter++;
        for (MoveDirection gene : animal.moves)
            genesPopularity.put(gene, genesPopularity.get(gene) + 1);
        mostPopularGenes = null;
    }

    public void died(Animal a) {
        animals.remove(a);
        toUpdate.add(a.getPosition());

        averageLifeSpan.add(a.getAge());

        for (MoveDirection gene : a.moves)
            genesPopularity.put(gene, genesPopularity.get(gene) - 1);
        mostPopularGenes = null;
    }

    public void died(Plant p) {
        plants.remove(p);
        toUpdate.add(p.getPosition());
    }

    public void positionChanged(Animal a, Vector2d oldPosition) {
        toUpdate.add(oldPosition);
        toUpdate.add(a.getPosition());
    }

    public void flipPaused() {
        this.paused = !this.paused;
    }

    public int getLivingAnimalNumber() {
        return animals.size();
    }
    public int getAllAnimalNumber() {
        return totalAnimalCounter;
    }
    public int getPlantNumber() {
        return plants.size();
    }

    public String getMostPopularGenes(int howMany) {
        if (mostPopularGenes == null) {
            class GeneHolder {
                MoveDirection gene;
                int counter;
                GeneHolder(MoveDirection gene, int counter) {
                    this.gene = gene;
                    this.counter = counter;
                }
            }
            ArrayList<GeneHolder> genes = new ArrayList<>();
            for (MoveDirection direction : MoveDirection.values())
                genes.add(new GeneHolder(direction, genesPopularity.get(direction)));
            genes.sort(
                    (GeneHolder g1, GeneHolder g2) -> g2.counter - g1.counter
            );

            mostPopularGenes = "";
            for (int i = 0; i < howMany; i++)
                mostPopularGenes += genes.get(i).gene.humanReadable() + " (" + genes.get(i).counter + ")  ";
            mostPopularGenes.trim();
        }
        return mostPopularGenes;
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
