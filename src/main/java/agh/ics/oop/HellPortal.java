package agh.ics.oop;

import java.util.Random;

public class HellPortal extends AbstractWorldMap {
    public HellPortal(SimulationConfig config, Random rand) {
        super(config, rand);
    }

    protected void outOfMap(Vector2d oldPos, Animal a) {
        a.takeEnergy(config.breedingEnergyUsedAnimal);
        a.setPosition(randomPosition());
    }
}
