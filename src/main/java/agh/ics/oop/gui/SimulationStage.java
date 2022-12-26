package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SimulationStage extends Stage {
    private final GridPane gridPane;
    public final double cellSize;
    ImageDictionary imageDictionary;
    AbstractWorldMap map;
    private final HashMap<Vector2d, ImageView> objects;
    private final HashMap<Vector2d, Rectangle> background;

    SimulationStage(SimulationConfig config, ImageDictionary imageDictionary, Random rand)
    {
        super();
        this.imageDictionary = imageDictionary;
        objects = new HashMap<>();
        background = new HashMap<>();
        gridPane = new GridPane();
        SimulationEngine engine = new SimulationEngine(config,rand,this);
        map = engine.map;
        Thread simulationThread = new Thread(engine);
        setTitle("Symulacja");
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        cellSize = Math.min(screenBounds.getWidth()/config.mapWidth*0.9,screenBounds.getHeight()/config.mapHeight*0.9);
        createBackground();
        BorderPane pane = new BorderPane();
        //pane.setLeft(new Label("TODO Controls"));
        pane.setCenter(gridPane);
        setScene(new Scene(pane, config.mapWidth*cellSize, config.mapHeight*cellSize));
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
        if (map.animalsAt(pos) != null && map.animalsAt(pos).size() > 0)
            el = map.animalsAt(pos).get(map.animalsAt(pos).size()-1);
        if (el != null) {
            ImageView imgV = new ImageView(imageDictionary.getImage(el.getTexturePath()));
            imgV.setFitWidth(cellSize);
            imgV.setFitHeight(cellSize);
            imgV.setEffect(el.getColorAdjust());
            gridPane.add(imgV, pos.x, map.getUpperRight().subtract(map.getLowerLeft()).y - pos.y);
            objects.put(pos, imgV);
        }
    }

    public void updateBackground()
    {
        for (Vector2d v: map.getPreferredFields())
            background.get(v).setFill(Color.DARKGREEN);
        for (Vector2d v: map.getNotPreferredFields())
            background.get(v).setFill(Color.GREEN);
    }
}
