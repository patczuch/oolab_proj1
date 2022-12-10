package agh.ics.oop;

public class InputValuesConstraints {
    public NumberConstraint mapHeightInputConstraint;
    public NumberConstraint mapWidthInputConstraint;
    public NumberConstraint plantNumberInputConstraint;
    public NumberConstraint plantEnergyInputConstraint;
    public NumberConstraint plantGrowingNumberInputConstraint;
    public NumberConstraint startAnimalNumberInputConstraint;
    public NumberConstraint startAnimalEnergyInputConstraint;
    public NumberConstraint fedAnimalEnergyInputConstraint;
    public NumberConstraint breedingEnergyUsedAnimalInputConstraint;
    public NumberConstraint minAnimalMutationsNumberInputConstraint;
    public NumberConstraint maxAnimalMutationsNumberInputConstraint;
    public NumberConstraint animalGenesLengthConstraint;
    public InputValuesConstraints(NumberConstraint mapHeightInputConstraint,NumberConstraint mapWidthInputConstraint,
                                  NumberConstraint plantNumberInputConstraint,NumberConstraint plantEnergyInputConstraint,
                                  NumberConstraint plantGrowingNumberInputConstraint,NumberConstraint startAnimalNumberInputConstraint,
                                  NumberConstraint startAnimalEnergyInputConstraint,NumberConstraint fedAnimalEnergyInputConstraint,
                                  NumberConstraint breedingEnergyUsedAnimalInputConstraint,NumberConstraint minAnimalMutationsNumberInputConstraint,
                                  NumberConstraint maxAnimalMutationsNumberInputConstraint,NumberConstraint animalGenesLengthConstraint) {
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
    }
}
