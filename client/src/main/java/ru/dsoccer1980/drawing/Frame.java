package ru.dsoccer1980.drawing;

import ru.dsoccer1980.Client;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth();
    private final int height = (int) screenSize.getHeight();
    private JButton button;
    private CurvesComponent curvesComponent;
    private JPanel buttonsPanel;

    public Frame() {
        initButton();
        initCurvesComponent();
        initButtonsPanel();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);

        add(buttonsPanel, BorderLayout.EAST);
        add(curvesComponent, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void initButton() {
        button = new JButton("Connect with server");
        button.addActionListener((listener) -> new Client(this).start());
    }

    private void initCurvesComponent() {
        curvesComponent = new CurvesComponent();
        curvesComponent.setPreferredSize(new Dimension(width, height));
    }

    private void initButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.add(button);
    }

    public void paintCurve(float x1, float y1, float x2, float y2, float x3, float y3, Color color) {
        curvesComponent.addCurve(
                x1 * width, y1 * height,
                x2 * width, y2 * height,
                x3 * width, y3 * height,
                color);
    }
}
