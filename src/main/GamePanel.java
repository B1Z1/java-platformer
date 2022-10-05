package main;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private float xDir = .8f;
    private float yDir = .8f;
    private Color color;
    private Random random = new Random();

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        updateRectangle();
        graphics.setColor(color);
        graphics.fillRect((int) xDelta, (int) yDelta, 200, 50);
    }

    private void updateRectangle() {
        if (xDelta + 200 >= 400 || xDelta <= 0) {
            xDir *= -1;
            color = getRandomColor();
        }

        if (yDelta + 50 >= 400 || yDelta <= 0) {
            yDir *= -1;
            color = getRandomColor();
        }

        xDelta += xDir;
        yDelta += yDir;
    }

    private Color getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, b, g);
    }
}
