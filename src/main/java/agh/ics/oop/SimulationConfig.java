package agh.ics.oop;

import agh.ics.oop.SimulationTypes.*;

public class SimulationConfig {
    public final int mapHeight;
    public final int mapWidth;
    public final MapType mapType;
    public final int plantNumber;
    public final int plantEnergy;
    public final int plantGrowingNumber;
    public final PlantGrowingType plantGrowingType;
    public final int startAnimalNumber;
    public final int startAnimalEnergy;
    public final int fedAnimalEnergy;
    public final int breedingEnergyUsedAnimal;
    public final int minAnimalMutationsNumber;
    public final int maxAnimalMutationsNumber;
    public final AnimalMutationType animalMutationType;
    public final int animalGenesLength;
    public final AnimalBehaviourType animalBehaviourType;
    public final int moveDelay;
    public SimulationConfig(int mapHeight, int mapWidth, MapType mapType, int plantNumber, int plantEnergy, int plantGrowingNumber, PlantGrowingType plantGrowingType,
                             int startAnimalNumber, int startAnimalEnergy, int fedAnimalEnergy, int breedingEnergyUsedAnimal, int minAnimalMutationsNumber,
                             int maxAnimalMutationsNumber, AnimalMutationType animalMutationType, int animalGenesLength, AnimalBehaviourType animalBehaviourType, int moveDelay)
    {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.mapType = mapType;
        this.plantNumber = plantNumber;
        this.plantEnergy = plantEnergy;
        this.plantGrowingNumber = plantGrowingNumber;
        this.plantGrowingType = plantGrowingType;
        this.startAnimalNumber = startAnimalNumber;
        this.startAnimalEnergy = startAnimalEnergy;
        this.fedAnimalEnergy = fedAnimalEnergy;
        this.breedingEnergyUsedAnimal = breedingEnergyUsedAnimal;
        this.minAnimalMutationsNumber = minAnimalMutationsNumber;
        this.maxAnimalMutationsNumber = maxAnimalMutationsNumber;
        this.animalMutationType = animalMutationType;
        this.animalGenesLength = animalGenesLength;
        this.animalBehaviourType = animalBehaviourType;
        this.moveDelay = moveDelay;
    }
}
