package agh.ics.oop;

import javafx.scene.effect.ColorAdjust;

public interface IMapElement {
    String getTexturePath();

    Vector2d getPosition();

    ColorAdjust getColorAdjust();
}
