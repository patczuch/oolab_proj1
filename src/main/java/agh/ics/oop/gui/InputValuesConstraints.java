package agh.ics.oop.gui;

import agh.ics.oop.NumberConstraint;

public class InputValuesConstraints {
    public final NumberConstraint mapHeightInputConstraint;
    public final NumberConstraint mapWidthInputConstraint;
    public final NumberConstraint plantNumberInputConstraint;
    public final NumberConstraint plantEnergyInputConstraint;
    public final NumberConstraint plantGrowingNumberInputConstraint;
    public final NumberConstraint startAnimalNumberInputConstraint;
    public final NumberConstraint startAnimalEnergyInputConstraint;
    public final NumberConstraint fedAnimalEnergyInputConstraint;
    public final NumberConstraint breedingEnergyUsedAnimalInputConstraint;
    public final NumberConstraint minAnimalMutationsNumberInputConstraint;
    public final NumberConstraint maxAnimalMutationsNumberInputConstraint;
    public final NumberConstraint animalGenesLengthConstraint;
    public final NumberConstraint moveDelayConstraint;
    public InputValuesConstraints(NumberConstraint mapHeightInputConstraint,NumberConstraint mapWidthInputConstraint,
                                  NumberConstraint plantNumberInputConstraint,NumberConstraint plantEnergyInputConstraint,
                                  NumberConstraint plantGrowingNumberInputConstraint,NumberConstraint startAnimalNumberInputConstraint,
                                  NumberConstraint startAnimalEnergyInputConstraint,NumberConstraint fedAnimalEnergyInputConstraint,
                                  NumberConstraint breedingEnergyUsedAnimalInputConstraint,NumberConstraint minAnimalMutationsNumberInputConstraint,
                                  NumberConstraint maxAnimalMutationsNumberInputConstraint,NumberConstraint animalGenesLengthConstraint, NumberConstraint moveDelayConstraint) {
        this.mapHeightInputConstraint = mapHeightInputConstraint;
        this.mapWidthInputConstraint = mapWidthInputConstraint;
        this.plantNumberInputConstraint = plantNumberInputConstraint;
        this.plantEnergyInputConstraint = plantEnergyInputConstraint;
        this.plantGrowingNumberInputConstraint = plantGrowingNumberInputConstraint;
        this.startAnimalNumberInputConstraint = startAnimalNumberInputConstraint;
        this.startAnimalEnergyInputConstraint = startAnimalEnergyInputConstraint;
        this.fedAnimalEnergyInputConstraint = fedAnimalEnergyInputConstraint;
        this.breedingEnergyUsedAnimalInputConstraint = breedingEnergyUsedAnimalInputConstraint;
        this.minAnimalMutationsNumberInputConstraint = minAnimalMutationsNumberInputConstraint;
        this.maxAnimalMutationsNumberInputConstraint = maxAnimalMutationsNumberInputConstraint;
        this.animalGenesLengthConstraint = animalGenesLengthConstraint;
        this.moveDelayConstraint = moveDelayConstraint;
    }
}
