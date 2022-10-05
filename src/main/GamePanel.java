package main;


import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private final Dimension spriteFragmentDimension = new Dimension(64, 40);
    private final Dimension dimension = new Dimension(1280, 720);

    private MouseInputs mouseInputs;

    private BufferedImage image;
    private BufferedImage[] idleAnimation;
    private int animationTick, animationIndex, animationSpeed = 15;

    private int xDelta = 0, yDelta = 0;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImage();
        loadAnimations();

        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        idleAnimation = new BufferedImage[5];

        for (int i = 0; i < idleAnimation.length; i++) {
            int width = (int) spriteFragmentDimension.getWidth();
            int height = (int) spriteFragmentDimension.getHeight();

            idleAnimation[i] = image.getSubimage(i * width, 0, width, height);
        }
    }

    public void setPosition(int x, int y) {
        xDelta = x;
        yDelta = y;
    }

    public void increaseXDelta(int x) {
        xDelta += x;
    }

    public void increaseYDelta(int y) {
        yDelta += y;
    }

    private void importImage() {
        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");

        try {
            image = ImageIO.read(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void setPanelSize() {
        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        updateAnimationTick();

        graphics.drawImage(idleAnimation[animationIndex], xDelta, yDelta, 256, 160, null);
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            animationTick = 0;

            animationIndex = animationIndex >= idleAnimation.length - 1 ? 0 : animationIndex + 1;
        }
    }
}
