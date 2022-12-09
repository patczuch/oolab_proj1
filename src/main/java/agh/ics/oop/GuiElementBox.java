package agh.ics.oop;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox extends VBox{
    public GuiElementBox(IMapElement el)
    {
        super();
        Label l = new Label(el.getLabel());
        ImageView imgV = new ImageView(new Image(el.getTexturePath()));
        imgV.setFitWidth(20);
        imgV.setFitHeight(20);
        getChildren().add(imgV);
        getChildren().add(l);
        setAlignment(Pos.CENTER);
    }
}
