package main;


import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utils.direction.Direction;
import utils.player.PlayerAnimation;
import utils.player.PlayerAnimationType;

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
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;

    private PlayerAnimationType playerAnimationType = PlayerAnimationType.IDLE;
    private Direction playerDirection = Direction.NONE;
    private int xDelta, yDelta;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImage();
        loadAnimations();

        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImage() {
        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");

        try {
            image = ImageIO.read(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                int width = (int) spriteFragmentDimension.getWidth();
                int height = (int) spriteFragmentDimension.getHeight();

                animations[i][j] = image.getSubimage(j * width, i * height, width, height);
            }
        }
    }

    private void setPanelSize() {
        setPreferredSize(dimension);
    }

    public void setPlayerDirection(Direction direction) {
        playerDirection = direction;
    }

    private void setAnimationType() {
        if (playerDirection.equals(Direction.NONE)) {
            playerAnimationType = PlayerAnimationType.IDLE;
        } else {
            playerAnimationType = PlayerAnimationType.RUNNING;
        }
    }

    private void updatePosition() {
        if (playerDirection.equals(Direction.NONE)) {
            return;
        }

        switch (playerDirection) {
            case UP -> yDelta -= 5;
            case RIGHT -> xDelta += 5;
            case DOWN -> yDelta += 5;
            case LEFT -> xDelta -= 5;
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        updateAnimationTick();
        setAnimationType();
        updatePosition();

        graphics.drawImage(animations[playerAnimationType.getValue()][animationIndex], xDelta, yDelta, 256, 160, null);
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            int spriteMaxCount = PlayerAnimation.getSpriteAnimationCount(playerAnimationType);
            int spriteMaxIndex = spriteMaxCount - 1;

            animationTick = 0;

            animationIndex = animationIndex >= spriteMaxIndex ? 0 : animationIndex + 1;
        }
    }
}
