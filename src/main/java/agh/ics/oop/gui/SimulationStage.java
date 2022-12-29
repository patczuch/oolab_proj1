package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SimulationStage extends Stage {
    private final GridPane gridPane;
    public final double cellSize;
    ImageDictionary imageDictionary;
    AbstractWorldMap map;
    private final HashMap<Vector2d, VBox> objects;
    private final HashMap<Vector2d, Rectangle> background;
    private SimulationControls controls;

    SimulationStage(SimulationConfig config, ImageDictionary imageDictionary, Random rand, boolean saveStats)
    {
        super();
        this.imageDictionary = imageDictionary;
        objects = new HashMap<>();
        background = new HashMap<>();
        gridPane = new GridPane();
        gridPane.setSnapToPixel(false);
        gridPane.setStyle("-fx-background-color: GREEN;");
        SimulationEngine engine = new SimulationEngine(config,rand,this, saveStats);
        map = engine.map;
        controls = new SimulationControls(300, engine);
        Thread simulationThread = new Thread(engine);
        setTitle("Symulacja");
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        cellSize = (int)Math.min((screenBounds.getWidth()/config.mapWidth)*0.9,(screenBounds.getHeight()/config.mapHeight)*0.9);
        createBackground();

        BorderPane pane = new BorderPane();
        pane.setLeft(controls.getPane());
        pane.setCenter(gridPane);

        setScene(new Scene(pane, config.mapWidth*cellSize + controls.getWidth(), config.mapHeight*cellSize));

        setResizable(false);
        setOnCloseRequest(e -> simulationThread.interrupt());
        show();
        simulationThread.start();
    }

    private void createBackground()
    {
        for (int x = 0; x <= map.getUpperRight().subtract(map.getLowerLeft()).x; x++)
            gridPane.getRowConstraints().add(new RowConstraints(cellSize));
        for (int y = 0; y <= map.getUpperRight().subtract(map.getLowerLeft()).y; y++)
            gridPane.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int y = 0; y <= map.getUpperRight().subtract(map.getLowerLeft()).y; y++)
            for (int x = 0; x <= map.getUpperRight().subtract(map.getLowerLeft()).x; x++) {
                Rectangle r = new Rectangle(cellSize, cellSize);
                background.put(new Vector2d(x,y),r);
                gridPane.add(r, x, map.getUpperRight().subtract(map.getLowerLeft()).y - y);
            }
        updateBackground();
    }

    public void update(Vector2d pos)
    {
        if (objects.containsKey(pos)) {
            gridPane.getChildren().remove(objects.get(pos));
            objects.remove(pos);
        }
        IMapElement el = null;
        if (map.isPlantAt(pos))
            el = map.plantAt(pos);
        ArrayList<Animal> temp = map.animalsAt(pos);
        if (temp != null) {
            int temp_size = temp.size();
            if (temp_size > 0)
                el = temp.get(temp_size - 1);
        }
        if (el != null) {
            ImageView imgV = new ImageView(imageDictionary.getImage(el.getTexturePath()));
            imgV.setFitWidth(cellSize);
            imgV.setFitHeight(cellSize);
            imgV.setEffect(el.getColorAdjust());

            VBox box = new VBox(imgV);
            // Black background for a followed animal
            if (el == controls.getFollowedAnimal())
                box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            gridPane.add(box, pos.x, map.getUpperRight().subtract(map.getLowerLeft()).y - pos.y);
            objects.put(pos, box);

            if (el instanceof Animal) {
                final Animal animal = (Animal) el;
                box.setOnMouseClicked(e -> {
                    controls.setFollowedAnimal(animal, this);
                });
            }
        }
    }

    public void updateBackground()
    {
        ArrayList<Vector2d> temp = map.getPreferredFields();
        for (int i = 0; i < temp.size(); i++)
            background.get(temp.get(i)).setFill(Color.DARKGREEN);
        ArrayList<Vector2d> temp2 = map.getNotPreferredFields();
        for (int i = 0; i < temp2.size(); i++)
            background.get(temp2.get(i)).setFill(Color.GREEN);

        controls.updateStats();
        controls.updateInfo();
    }
}
