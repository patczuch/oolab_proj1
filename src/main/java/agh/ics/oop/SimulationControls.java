package agh.ics.oop;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SimulationControls {
    private final int width;
    private Animal followedAnimal = null;
    private BorderPane pane;
    public SimulationControls(int width, SimulationEngine engine) {
        this.width = width;

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> {engine.flipPaused();});

//        pane = new VBox(pauseButton);
        pane = new BorderPane();
        pane.setMinWidth(width);
        pane.setMaxWidth(width);
        pane.setPrefWidth(width);

        pane.setTop(pauseButton);

//        Label info = new Label("  ");
//        info.
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
        if (followedAnimal == null)
            return;
        String message = "Selected animal" +
                "\nGenome: \n" + movesToString(followedAnimal.moves);
        if (followedAnimal.isAlive())
            message += "\nAge: " + followedAnimal.getAge() +
                    "\nEnergy: " + followedAnimal.getEnergy() +
                    "\nPosition: " + followedAnimal.getPosition().toString();
        else
            message += "\nDied at age: " + followedAnimal.getAge() +
                    "\nAt position: " + followedAnimal.getPosition().toString();
        message += "\nCurrent move: " + followedAnimal.getCurrentMove().humanReadable() +
                "\nChlidren: " + followedAnimal.getChildrenAmount() +
                "\nPlants eaten: " + followedAnimal.getPlantsEaten();

        Label newLabel = new Label(message);
        newLabel.setFont(new Font(15));
        pane.setCenter(newLabel);
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
