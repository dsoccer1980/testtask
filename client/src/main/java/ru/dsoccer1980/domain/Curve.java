package ru.dsoccer1980.domain;

import java.awt.*;

public class Curve {
    public final float x1;
    public final float y1;
    public final float x2;
    public final float y2;
    public final float x3;
    public final float y3;
    public final Color color;

    public Curve(float x1, float y1, float x2, float y2, float x3, float y3, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.color = color;
    }
}
