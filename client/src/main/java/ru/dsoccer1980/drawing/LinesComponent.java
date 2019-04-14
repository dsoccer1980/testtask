package ru.dsoccer1980.drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.LinkedList;

public class LinesComponent extends JComponent {

    private final LinkedList<Curve> curves = new LinkedList<>();

    public void addCurve(float x1, float y1, float x2, float y2, float x3, float y3) {
        curves.add(new Curve(x1, y1, x2, y2, x3, y3, Color.black));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Curve curve : curves) {
            g.setColor(curve.color);
            QuadCurve2D quadcurve = new QuadCurve2D.Float(
                    curve.x1, curve.y1,
                    curve.x2, curve.y2,
                    curve.x3, curve.y3);
            g2d.draw(quadcurve);
        }
    }

    private static class Curve {
        final float x1;
        final float y1;
        final float x2;
        final float y2;
        final float x3;
        final float y3;
        final Color color;

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

}
