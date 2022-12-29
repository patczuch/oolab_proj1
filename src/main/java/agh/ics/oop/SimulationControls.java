package agh.ics.oop;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class SimulationControls {
    private final int width;
    private Animal followedAnimal = null;
    private final BorderPane pane;
    private final SimulationEngine engine;

    public SimulationControls(int width, SimulationEngine engine) {
        this.width = width;
        this.engine = engine;

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> {engine.flipPaused();});

        pane = new BorderPane();
        pane.setMinWidth(width);
        pane.setMaxWidth(width);
        pane.setPrefWidth(width);
        pane.setPadding(new Insets(40, 20, 40, 20));

        pane.setTop(pauseButton);
    }

    public int getWidth() {
        return width;
    }

    public void setFollowedAnimal(Animal animal) {
        this.followedAnimal = animal;
        System.out.print("Following: ");
        System.out.println(animal.getPosition());

        updateInfo();
    }

    public void updateInfo() {
        String message;
        if (followedAnimal == null)
            message="\n".repeat(9);
        else {
            message =
                    "Selected animal" +
                    "\nGenome: \n" + movesToString(followedAnimal.moves);

            if (followedAnimal.isAlive())
                message +=
                        "\nAge: " + followedAnimal.getAge() +
                        "\nEnergy: " + followedAnimal.getEnergy() +
                        "\nPosition: " + followedAnimal.getPosition().toString();
            else
                message +=
                        "\nDied at age: " + followedAnimal.getAge() +
                        "\nAt position: " + followedAnimal.getPosition().toString()+"\n";
            message +=
                    "\nCurrent move: " + followedAnimal.getCurrentMove().humanReadable() +
                    "\nChildren: " + followedAnimal.getChildrenAmount() +
                    "\nPlants eaten: " + followedAnimal.getPlantsEaten();
        }
        Label newLabel = new Label(message);
        newLabel.setFont(new Font(15));
        pane.setBottom(newLabel);
    }

    public void updateStats() {
        String message =
                "Day " + engine.getCurrentDay() +
                "\nLiving animals: " + engine.getLivingAnimalNumber() +
                "\nPlants on the map: " + engine.getPlantNumber() +
                "\nFree fields: " + engine.map.countFreeFields() +
                "\nPopular genes: " + engine.getMostPopularGenes(3) +
                "\nAverage energy level: " + String.format("%.1f", engine.averageEnergyLevel.getAverage()) +
                "\nAverage life span: " + String.format("%.1f", engine.averageLifeSpan.getAverage()) +
                "\nAnimals ever lived: " + engine.getAllAnimalNumber();

        Label newLabel = new Label(message);
        newLabel.setFont(new Font(15));
        pane.setCenter(newLabel);
//        pane.setLeft(newLabel);
    }

    private String movesToString(MoveDirection moves[]) {
        StringBuilder result = new StringBuilder();
        for (MoveDirection move : moves)
            result.append(move.humanReadable());

        return result.toString();
    }

    public BorderPane getPane() {
        return pane;
    }
}
