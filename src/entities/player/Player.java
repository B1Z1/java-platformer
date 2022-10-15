package entities.player;

import entities.entity.Entity;
import entities.entity.EntityDimension;
import utils.direction.Direction;
import utils.player.PlayerAnimation;
import utils.player.PlayerAnimationType;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Player extends Entity {
    private final int moveSpeed = 2;
    private final int animationSpeed = 15;
    private BufferedImage[][] animations;
    private HashMap<Direction, Boolean> currentDirection = new HashMap<Direction, Boolean>() {{
        put(Direction.UP, false);
        put(Direction.RIGHT, false);
        put(Direction.DOWN, false);
        put(Direction.LEFT, false);
    }};
    private boolean attacking = false;
    private PlayerAnimationType animationType = PlayerAnimationType.IDLE;
    private int animationTick, animationIndex;
    private int xDelta, yDelta;

    public Player(float x, float y) {
        super(x, y);

        loadAnimations();
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimationType();
    }

    public void render(Graphics graphics) {
        graphics.drawImage(animations[animationType.getValue()][animationIndex], xDelta, yDelta, 256, 160, null);
    }

    public void setDirection(Direction direction, boolean active) {
        this.currentDirection.put(direction, active);
    }

    public void resetDirections() {
        this.currentDirection.put(Direction.UP, false);
        this.currentDirection.put(Direction.RIGHT, false);
        this.currentDirection.put(Direction.DOWN, false);
        this.currentDirection.put(Direction.LEFT, false);
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    private void loadAnimations() {
        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");

        try {
            BufferedImage image = ImageIO.read(inputStream);

            animations = new BufferedImage[9][6];

            for (int i = 0; i < animations.length; i++) {
                for (int j = 0; j < animations[i].length; j++) {
                    int width = (int) EntityDimension.ENTITY_DIMENSION.getWidth();
                    int height = (int) EntityDimension.ENTITY_DIMENSION.getHeight();

                    animations[i][j] = image.getSubimage(j * width, i * height, width, height);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setAnimationType() {
        PlayerAnimationType previousAnimationType = animationType;

        if (attacking) {
            animationType = PlayerAnimationType.ATTACK_1;
        } else if (isMoving()) {
            animationType = PlayerAnimationType.RUNNING;
        } else {
            animationType = PlayerAnimationType.IDLE;
        }

        if (previousAnimationType != animationType) {
            resetAnimation();
        }
    }

    private void updatePosition() {
        if (isCurrentDirectionActive(Direction.LEFT) && !isCurrentDirectionActive(Direction.RIGHT)) {
            xDelta -= moveSpeed;
        } else if (isCurrentDirectionActive(Direction.RIGHT) && !isCurrentDirectionActive(Direction.LEFT)) {
            xDelta += moveSpeed;
        }

        if (isCurrentDirectionActive(Direction.UP) && !isCurrentDirectionActive(Direction.DOWN)) {
            yDelta -= moveSpeed;
        } else if (isCurrentDirectionActive(Direction.DOWN) && !isCurrentDirectionActive(Direction.UP)) {
            yDelta += moveSpeed;
        }
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            int spriteMaxCount = PlayerAnimation.getSpriteAnimationCount(animationType);
            int spriteMaxIndex = spriteMaxCount - 1;
            boolean isLastAnimationIndex = animationIndex >= spriteMaxIndex;

            animationTick = 0;

            if (isLastAnimationIndex) {
                attacking = false;
                animationIndex = 0;
            } else {
                animationIndex++;
            }
        }
    }

    private void resetAnimation() {
        animationTick = 0;
        animationIndex = 0;
    }

    private boolean isMoving() {
        return isCurrentDirectionActive(Direction.UP) || isCurrentDirectionActive(Direction.RIGHT) || isCurrentDirectionActive(Direction.DOWN) || isCurrentDirectionActive(Direction.LEFT);
    }

    private boolean isCurrentDirectionActive(Direction direction) {
        return currentDirection.get(direction);
    }
}
