package main;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(
            Game game
    ) {
        this.game = game;
        this.mouseInputs = new MouseInputs(this);

        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(this.mouseInputs);
        addMouseMotionListener(this.mouseInputs);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        game.render(graphics);
    }

    public Game getGame() {
        return game;
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT));
        System.out.println("Game Size: " + Game.GAME_WIDTH + ":" + Game.GAME_HEIGHT);
    }
}
