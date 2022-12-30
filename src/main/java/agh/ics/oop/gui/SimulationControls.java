package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2d;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class SimulationControls extends BorderPane{
    private final int width;
    private Animal followedAnimal = null;
    private final SimulationEngine engine;

    public SimulationControls(int width, SimulationEngine engine) {
        super();
        this.width = width;
        this.engine = engine;

        Button pauseButton = new Button("Zatrzymaj");
        pauseButton.setOnAction(e -> {engine.togglePaused(); pauseButton.setText(pauseButton.getText().equals("Zatrzymaj") ? "Wznów" : "Zatrzymaj");});


        setMinWidth(width);
        setMaxWidth(width);
        setPrefWidth(width);
        setPadding(new Insets(40, 20, 40, 20));

        setTop(pauseButton);
    }

    public int _getWidth() {
        return width;
    }

    public void setFollowedAnimal(Animal animal, SimulationStage simulationStage) {
        Vector2d toUpdate = (followedAnimal == null) ? null : followedAnimal.getPosition();
        this.followedAnimal = animal;
        updateInfo();

        if (simulationStage != null) {
            simulationStage.update(animal.getPosition());
            if (toUpdate != null)
                simulationStage.update(toUpdate);
        }
    }

    public Animal getFollowedAnimal() {
        return followedAnimal;
    }

    public void updateInfo() {
        String message;
        if (followedAnimal == null)
            message="\n".repeat(9);
        else {
            message =
                    "Wybrane zwierzę" +
                    "\nGenom: \n" + movesToString(followedAnimal.moves);

            if (followedAnimal.isAlive())
                message +=
                        "\nWiek: " + followedAnimal.getAge() +
                        "\nEnergia: " + followedAnimal.getEnergy() +
                        "\nPozycja: " + followedAnimal.getPosition().toString();
            else
                message +=
                        "\nZmarło w wieku: " + followedAnimal.getAge() +
                        "\nNa pozycji: " + followedAnimal.getPosition().toString()+"\n";
            message +=
                    "\nAktualny ruch: " + followedAnimal.getCurrentMove().humanReadable() +
                    "\nDzieci: " + followedAnimal.getChildrenAmount() +
                    "\nZjedzone rośliny: " + followedAnimal.getPlantsEaten();
        }
        Label newLabel = new Label(message);
        newLabel.setFont(new Font(15));
        setBottom(newLabel);
    }

    public void updateStats() {
        String message =
                "Dzień " + engine.getCurrentDay() +
                "\nŻywe zwierzęta: " + engine.getLivingAnimalNumber() +
                "\nRośliny: " + engine.getPlantNumber() +
                "\nWolne pola: " + engine.map.countFreeFields() +
                "\nPopularne geny: \n" + engine.getMostPopularGenes(3, true) +
                "\nŚredni poziom energii: " + String.format("%.1f", engine.averageEnergyLevel.getAverage()) +
                "\nŚrednia długość życia: " + String.format("%.1f", engine.averageLifeSpan.getAverage()) +
                "\nWszystkie zwierzęta: " + engine.getAllAnimalNumber();

        Label newLabel = new Label(message);
        newLabel.setFont(new Font(15));
        setCenter(newLabel);
//        pane.setLeft(newLabel);
    }

    private String movesToString(MoveDirection moves[]) {
        StringBuilder result = new StringBuilder();
        for (MoveDirection move : moves)
            result.append(move.humanReadable());

        return result.toString();
    }
}
