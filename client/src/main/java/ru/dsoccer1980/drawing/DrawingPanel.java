package ru.dsoccer1980.drawing;

import ru.dsoccer1980.model.Curve;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {

    private final List<Curve> curves = new ArrayList<>();

    protected void addCurve(Curve curve) {
        curves.add(curve);
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

}
