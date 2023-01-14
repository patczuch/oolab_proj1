package agh.ics.oop;

import agh.ics.oop.SimulationTypes.*;
import org.json.simple.JSONObject;

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
    public final String name;

    public SimulationConfig(String name, int mapHeight, int mapWidth, MapType mapType, int plantNumber, int plantEnergy, int plantGrowingNumber, PlantGrowingType plantGrowingType,
                            int startAnimalNumber, int startAnimalEnergy, int fedAnimalEnergy, int breedingEnergyUsedAnimal, int minAnimalMutationsNumber,
                            int maxAnimalMutationsNumber, AnimalMutationType animalMutationType, int animalGenesLength, AnimalBehaviourType animalBehaviourType, int moveDelay) {
        this.name = name;
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

    public SimulationConfig(String name, JSONObject jsonObject) {
        this.name = name;
        this.mapHeight = (int) (long) jsonObject.get("mapHeight");  // to int czy long?
        this.mapWidth = (int) (long) jsonObject.get("mapWidth");
        this.mapType = MapType.values()[(int) (long) jsonObject.get("mapType")];
        this.plantNumber = (int) (long) jsonObject.get("plantNumber");
        this.plantEnergy = (int) (long) jsonObject.get("plantEnergy");
        this.plantGrowingNumber = (int) (long) jsonObject.get("plantGrowingNumber");
        this.plantGrowingType = PlantGrowingType.values()[(int) (long) jsonObject.get("plantGrowingType")];
        this.startAnimalNumber = (int) (long) jsonObject.get("startAnimalNumber");
        this.startAnimalEnergy = (int) (long) jsonObject.get("startAnimalEnergy");
        this.fedAnimalEnergy = (int) (long) jsonObject.get("fedAnimalEnergy");
        this.breedingEnergyUsedAnimal = (int) (long) jsonObject.get("breedingEnergyUsedAnimal");
        this.minAnimalMutationsNumber = (int) (long) jsonObject.get("minAnimalMutationsNumber");
        this.maxAnimalMutationsNumber = (int) (long) jsonObject.get("maxAnimalMutationsNumber");
        this.animalMutationType = AnimalMutationType.values()[(int) (long) jsonObject.get("animalMutationType")];
        this.animalGenesLength = (int) (long) jsonObject.get("animalGenesLength");
        this.animalBehaviourType = AnimalBehaviourType.values()[(int) (long) jsonObject.get("animalBehaviourType")];
        this.moveDelay = (int) (long) jsonObject.get("moveDelay");
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mapHeight", mapHeight);
        jsonObject.put("mapWidth", mapWidth);
        jsonObject.put("mapType", mapType.ordinal());
        jsonObject.put("plantNumber", plantNumber);
        jsonObject.put("plantEnergy", plantEnergy);
        jsonObject.put("plantGrowingNumber", plantGrowingNumber);
        jsonObject.put("plantGrowingType", plantGrowingType.ordinal());
        jsonObject.put("startAnimalNumber", startAnimalNumber);
        jsonObject.put("startAnimalEnergy", startAnimalEnergy);
        jsonObject.put("fedAnimalEnergy", fedAnimalEnergy);
        jsonObject.put("breedingEnergyUsedAnimal", breedingEnergyUsedAnimal);
        jsonObject.put("minAnimalMutationsNumber", minAnimalMutationsNumber);
        jsonObject.put("maxAnimalMutationsNumber", maxAnimalMutationsNumber);
        jsonObject.put("animalMutationType", animalMutationType.ordinal());
        jsonObject.put("animalGenesLength", animalGenesLength);
        jsonObject.put("animalBehaviourType", animalBehaviourType.ordinal());
        jsonObject.put("moveDelay", moveDelay);
        return jsonObject;
    }

    public String toString() {
        return name;
    }
}
