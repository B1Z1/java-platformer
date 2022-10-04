package main;


import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    public GamePanel() {
        super();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);

        graphics.fillRect(100, 100, 200, 50);
    }
}
