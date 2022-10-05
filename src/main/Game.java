package main;

public class Game implements Runnable {
    private final int FPS_SET = 120;
    private final double NANO_SECOND = 1000000000.0;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double nanoTimePerFrame = NANO_SECOND / FPS_SET;
        long lastNanoTimeFrame = System.nanoTime();
        long currentNanoTime;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            currentNanoTime = System.nanoTime();

            if (currentNanoTime - lastNanoTimeFrame >= nanoTimePerFrame) {
                gamePanel.repaint();

                lastNanoTimeFrame = currentNanoTime;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}
