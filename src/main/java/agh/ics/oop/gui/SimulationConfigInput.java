package agh.ics.oop.gui;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SimulationConfigInput extends GridPane {

    SimulationConfigInput()
    {
        TextField mapHeightInput = new TextField();
        TextField mapWidthInput = new TextField();
        ComboBox mapTypeInput = new ComboBox(
                FXCollections.observableArrayList(
                        "Kula ziemska",
                        "Piekielny portal"
                ));
        TextField plantNumberInput = new TextField();
        TextField plantEnergyInput = new TextField();
        TextField plantGrowingNumberInput = new TextField();
        ComboBox plantGrowingType = new ComboBox(
                FXCollections.observableArrayList(
                        "Zalesione równiki",
                        "Toksyczne trupy"
                ));
        TextField startAnimalNumberInput = new TextField();
        TextField startAnimalEnergyInput = new TextField();
        TextField fedAnimalEnergyInput = new TextField();
        TextField breedingEnergyUsedAnimalInput = new TextField();
        TextField minAnimalMutationsNumberInput = new TextField();
        TextField maxAnimalMutationsNumberInput = new TextField();
        ComboBox animalMutationTypeInput = new ComboBox(
                FXCollections.observableArrayList(
                        "Pełna losowość",
                        "Lekka korekta"
                ));
        TextField animalGenesLength = new TextField();
        ComboBox animalBehaviourTypeInput = new ComboBox(
                FXCollections.observableArrayList(
                        "Pełna predestynacja",
                        "Nieco szaleństwa"
                ));
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
        this.add(animalGenesLength,1,14);
        this.add(new Label("Wariant zachowania zwierząt:"),0,15);
        this.add(animalBehaviourTypeInput,1,15);
    }
}
