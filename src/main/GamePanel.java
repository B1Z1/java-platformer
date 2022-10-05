package main;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Random random = new Random();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void createRectangle(int x, int y) {
        rectangles.add(new Rectangle(x, y));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Rectangle rectangle : rectangles) {
            rectangle.updateRect();
            rectangle.draw(graphics);
        }
    }

    public class Rectangle {
        private int x, y, width, height;
        private int xDir = 1, yDir = 1;
        private Color color;

        public Rectangle(int x, int y) {
            this.x = x;
            this.y = y;

            width = random.nextInt(50);
            height = width;
            color = getRandomColor();
        }

        public void updateRect() {
            x += xDir;
            y += yDir;

            if ((x + width) > 400 || x < 0) {
                xDir *= -1;
                color = getRandomColor();
            }

            if ((y + height) > 400 || y < 0) {
                yDir *= -1;
                color = getRandomColor();
            }
        }

        public void draw(Graphics graphics) {
            graphics.setColor(color);
            graphics.fillRect(x, y, width, height);
        }

        private Color getRandomColor() {
            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);

            return new Color(r, b, g);
        }
    }
}
