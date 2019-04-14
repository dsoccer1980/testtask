package ru.dsoccer1980.drawing;

import ru.dsoccer1980.Client;
import ru.dsoccer1980.model.Curve;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth();
    private final int height = (int) screenSize.getHeight();
    private DrawingPanel drawingPanel;
    private JButton button;
    private Client client;

    public Frame(Client client) {
        this.client = client;
        initDrawingPanel();
        initFrame();
    }

    public void paintCurve(float x1, float y1, float x2, float y2, float x3, float y3, Color color) {
        drawingPanel.addCurve(new Curve(
                x1 * width, y1 * height,
                x2 * width, y2 * height,
                x3 * width, y3 * height,
                color));
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(drawingPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void initDrawingPanel() {
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.white);
        drawingPanel.setPreferredSize(new Dimension(width, height));
        initButton();
        drawingPanel.add(button);
    }

    private void initButton() {
        button = new JButton("Connect with server");
        button.addActionListener((listener) -> {
            client.start();
            drawingPanel.remove(button);
        });
    }


}
