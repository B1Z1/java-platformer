package main;

import entities.player.Player;

import java.awt.Graphics;

public class Game implements Runnable {
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final double NANO_SECOND = 1000000000.0;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    public void render(Graphics graphics) {
        player.render(graphics);
    }

    private void update() {
        player.update();
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirections();
    }

    private void initClasses() {
        player = new Player(200, 200);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double nanoTimePerFrame = NANO_SECOND / FPS_SET;
        double nanoTimePerUpdate = NANO_SECOND / UPS_SET;
        int frames = 0;
        int updates = 0;
        double deltaUpdate = 0;
        double deltaFrame = 0;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();

            deltaUpdate += (currentTime - previousTime) / nanoTimePerUpdate;
            deltaFrame += (currentTime - previousTime) / nanoTimePerFrame;

            previousTime = currentTime;

            if (deltaUpdate >= 1) {
                update();

                updates++;
                deltaUpdate--;
            }

            if (deltaFrame >= 1) {
                gamePanel.repaint();

                frames++;
                deltaFrame--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
