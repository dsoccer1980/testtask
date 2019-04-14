package ru.dsoccer1980.drawing;

import ru.dsoccer1980.Client;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();
    private JButton button;
    private LinesComponent linesComponent;
    private JPanel buttonsPanel;

    public Frame() {
        initButton();
        initLinesComponent();
        initButtonsPanel();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(buttonsPanel, BorderLayout.EAST);
        add(linesComponent, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void initButton() {
        button = new JButton("Connect with server");
        button.addActionListener((listener) -> {
            new Client(this).start();
        });
    }

    private void initLinesComponent() {
        linesComponent = new LinesComponent();
        linesComponent.setPreferredSize(new Dimension(width, height));
    }

    private void initButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.add(button);
    }

    public void paintCurve(float x1, float y1, float x2, float y2, float x3, float y3) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        linesComponent.addCurve(
                x1 * width, y1 * height,
                x2 * width, y2 * height,
                x3 * width, y3 * height);
    }
}
