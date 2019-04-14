package ru.dsoccer1980.drawing;

import ru.dsoccer1980.Client;
import ru.dsoccer1980.domain.Curve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth();
    private final int height = (int) screenSize.getHeight();
    private CurvesComponent curvesComponent;
    private Client client;

    public Frame(Client client) {
        this.client = client;
        initCurvesComponent();
        initFrame();
    }

    public void paintCurve(float x1, float y1, float x2, float y2, float x3, float y3, Color color) {
        curvesComponent.addCurve(new Curve(
                x1 * width, y1 * height,
                x2 * width, y2 * height,
                x3 * width, y3 * height,
                color));
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);

        add(curvesComponent, BorderLayout.CENTER);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getClickCount() == 2) && !client.isStarted()) {
                    client.start();
                }
            }
        });

        pack();
        setVisible(true);
    }

    private void initCurvesComponent() {
        curvesComponent = new CurvesComponent();
        curvesComponent.setPreferredSize(new Dimension(width, height));
    }
}
