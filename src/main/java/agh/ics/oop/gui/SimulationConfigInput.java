package agh.ics.oop.gui;

import agh.ics.oop.SimulationConfig;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import agh.ics.oop.SimulationTypes.*;
public class SimulationConfigInput extends GridPane {
    NumberField mapHeightInput;
    NumberField mapWidthInput;
    ComboBox<MapType> mapTypeInput;
    NumberField plantNumberInput;
    NumberField plantEnergyInput;
    NumberField plantGrowingNumberInput;
    ComboBox<PlantGrowingType> plantGrowingType;
    NumberField startAnimalNumberInput;
    NumberField startAnimalEnergyInput;
    NumberField fedAnimalEnergyInput;
    NumberField breedingEnergyUsedAnimalInput;
    NumberField minAnimalMutationsNumberInput;
    NumberField maxAnimalMutationsNumberInput;
    ComboBox<AnimalMutationType> animalMutationTypeInput;
    NumberField animalGenesLengthInput;
    ComboBox<AnimalBehaviourType> animalBehaviourTypeInput;
    NumberField moveDelayInput;
    SimulationConfigInput(SimulationConfig simulationConfig, InputValuesConstraints inputValuesConstraints)
    {
        mapHeightInput = new NumberField(simulationConfig.mapHeight,inputValuesConstraints.mapHeightInputConstraint);
        mapWidthInput = new NumberField(simulationConfig.mapWidth,inputValuesConstraints.mapWidthInputConstraint);
        mapTypeInput = new ComboBox<>(
                FXCollections.observableArrayList(
                        MapType.EARTH,
                        MapType.HELLPORTAL
                ));
        mapTypeInput.getSelectionModel().select(simulationConfig.mapType);
        plantNumberInput = new NumberField(simulationConfig.plantNumber,inputValuesConstraints.plantNumberInputConstraint);
        plantEnergyInput = new NumberField(simulationConfig.plantEnergy,inputValuesConstraints.plantEnergyInputConstraint);
        plantGrowingNumberInput = new NumberField(simulationConfig.plantGrowingNumber,inputValuesConstraints.plantGrowingNumberInputConstraint);
        plantGrowingType = new ComboBox<>(
                FXCollections.observableArrayList(
                        PlantGrowingType.TOXICDEAD,
                        PlantGrowingType.FORESTYEQUATORS
                ));
        plantGrowingType.getSelectionModel().select(simulationConfig.plantGrowingType);
        startAnimalNumberInput = new NumberField(simulationConfig.startAnimalNumber,inputValuesConstraints.startAnimalNumberInputConstraint);
        startAnimalEnergyInput = new NumberField(simulationConfig.startAnimalEnergy,inputValuesConstraints.startAnimalEnergyInputConstraint);
        fedAnimalEnergyInput = new NumberField(simulationConfig.fedAnimalEnergy,inputValuesConstraints.fedAnimalEnergyInputConstraint);
        breedingEnergyUsedAnimalInput = new NumberField(simulationConfig.breedingEnergyUsedAnimal,inputValuesConstraints.breedingEnergyUsedAnimalInputConstraint);
        minAnimalMutationsNumberInput = new NumberField(simulationConfig.minAnimalMutationsNumber,inputValuesConstraints.minAnimalMutationsNumberInputConstraint);
        maxAnimalMutationsNumberInput = new NumberField(simulationConfig.maxAnimalMutationsNumber,inputValuesConstraints.maxAnimalMutationsNumberInputConstraint);
        animalMutationTypeInput = new ComboBox<>(
                FXCollections.observableArrayList(
                        AnimalMutationType.FULLYRANDOM,
                        AnimalMutationType.LESSRANDOM
                ));
        animalMutationTypeInput.getSelectionModel().select(simulationConfig.animalMutationType);
        animalGenesLengthInput = new NumberField(simulationConfig.animalGenesLength,inputValuesConstraints.animalGenesLengthConstraint);
        animalBehaviourTypeInput = new ComboBox<>(
                FXCollections.observableArrayList(
                        AnimalBehaviourType.FULLYDETERMINED,
                        AnimalBehaviourType.SLIGHTLYCRAZY
                ));
        animalBehaviourTypeInput.getSelectionModel().select(simulationConfig.animalBehaviourType);
        moveDelayInput = new NumberField(simulationConfig.moveDelay,inputValuesConstraints.moveDelayConstraint);
        this.add(new Label("Wysokość mapy:"),0,0);
        this.add(mapHeightInput,1,0);
        this.add(new Label("Szerokość mapy:"),0,1);
        this.add(mapWidthInput,1,1);
        this.add(new Label("Wariant mapy:"),0,2);
        this.add(mapTypeInput,1,2);
        this.add(new Label("Startowa liczba roślin:"),0,3);
        this.add(plantNumberInput,1,3);
        this.add(new Label("Energia roślin:"),0,4);
        this.add(plantEnergyInput,1,4);
        this.add(new Label("Liczba roślin rosnących każdego dnia:"),0,5);
        this.add(plantGrowingNumberInput,1,5);
        this.add(new Label("Wariant wzrostu roślin:"),0,6);
        this.add(plantGrowingType,1,6);
        this.add(new Label("Początkowa liczba zwierząt:"),0,7);
        this.add(startAnimalNumberInput,1,7);
        this.add(new Label("Początkowa energia zwierząt:"),0,8);
        this.add(startAnimalEnergyInput,1,8);
        this.add(new Label("Energia najedzenia:"),0,9);
        this.add(fedAnimalEnergyInput,1,9);
        this.add(new Label("Energia zużywana na stworzenie potomka:"),0,10);
        this.add(breedingEnergyUsedAnimalInput,1,10);
        this.add(new Label("Minimalna liczba mutacji:"),0,11);
        this.add(minAnimalMutationsNumberInput,1,11);
        this.add(new Label("Maksymalna liczba mutacji:"),0,12);
        this.add(maxAnimalMutationsNumberInput,1,12);
        this.add(new Label("Wariant mutacji:"),0,13);
        this.add(animalMutationTypeInput,1,13);
        this.add(new Label("Długość genomu zwierząt:"),0,14);
        this.add(animalGenesLengthInput,1,14);
        this.add(new Label("Wariant zachowania zwierząt:"),0,15);
        this.add(animalBehaviourTypeInput,1,15);
        this.add(new Label("Opóźnienie pomiędzy ruchami:"),0,16);
        this.add(moveDelayInput,1,16);
    }

    public SimulationConfig getSimulationConfig()
    {
        return new SimulationConfig(mapHeightInput.getValue(),mapWidthInput.getValue(),mapTypeInput.getValue(),plantNumberInput.getValue(),
                plantEnergyInput.getValue(),plantGrowingNumberInput.getValue(),plantGrowingType.getValue(),startAnimalNumberInput.getValue(),
                startAnimalEnergyInput.getValue(),fedAnimalEnergyInput.getValue(),breedingEnergyUsedAnimalInput.getValue(),
                minAnimalMutationsNumberInput.getValue(), maxAnimalMutationsNumberInput.getValue(),
                animalMutationTypeInput.getValue(), animalGenesLengthInput.getValue(), animalBehaviourTypeInput.getValue(), moveDelayInput.getValue());
    }
}
