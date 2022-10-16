package entities.player;

import entities.entity.Entity;
import entities.entity.EntityDimension;
import main.Game;
import utils.collision.Collision;
import utils.direction.Direction;
import utils.load.LoadSave;
import utils.player.PlayerAnimation;
import utils.player.PlayerAnimationType;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Entity {
    private final int moveSpeed = 2;
    private final int animationSpeed = 15;
    private final HashMap<Direction, Boolean> currentDirection = new HashMap<Direction, Boolean>() {{
        put(Direction.RIGHT, false);
        put(Direction.LEFT, false);
    }};

    private final float xDrawOffset = 21 * Game.TILES_DEFAULT_SCALE;
    private final float yDrawOffset = 4 * Game.TILES_DEFAULT_SCALE;

    private BufferedImage[][] animations;
    private boolean attacking = false;
    private PlayerAnimationType animationType = PlayerAnimationType.IDLE;
    private int animationTick, animationIndex;
    private int[][] levelData;

    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.TILES_DEFAULT_SCALE;
    private float jumpSpeed = -2.25f * Game.TILES_DEFAULT_SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.TILES_DEFAULT_SCALE;
    private boolean inAir = false;
    private boolean jump = false;

    public Player(
            float x,
            float y,
            int width,
            int height
    ) {
        super(x, y, width, height);

        loadAnimations();
        initHitBox(x, y, 20 * Game.TILES_DEFAULT_SCALE, 28 * Game.TILES_DEFAULT_SCALE);
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimationType();
    }

    public void render(Graphics graphics) {
        BufferedImage image = animations[animationType.getValue()][animationIndex];

        int xWithOffset = (int) (hitBox.x - xDrawOffset);
        int yWithOffset = (int) (hitBox.y - yDrawOffset);

        graphics.drawImage(image, xWithOffset, yWithOffset, width, height, null);
        renderHitBox(graphics);
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public void setDirection(Direction direction, boolean active) {
        this.currentDirection.put(direction, active);
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void resetDirections() {
        this.currentDirection.put(Direction.RIGHT, false);
        this.currentDirection.put(Direction.LEFT, false);
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    private void loadAnimations() {
        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                int width = (int) EntityDimension.ENTITY_DIMENSION.getWidth();
                int height = (int) EntityDimension.ENTITY_DIMENSION.getHeight();

                animations[i][j] = image.getSubimage(j * width, i * height, width, height);
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
        if (jump) {
            resolveJump();
        }

        if (!isMoving() && !inAir) {
            return;
        }

        int xSpeed = 0;

        if (isCurrentDirectionActive(Direction.LEFT)) {
            xSpeed -= moveSpeed;
        }

        if (isCurrentDirectionActive(Direction.RIGHT)) {
            xSpeed += moveSpeed;
        }

        if (!inAir && !Collision.isEntityOnFloor(hitBox, levelData)) {
            inAir = true;
        }

        updateXPos(xSpeed);

        if (!inAir) {
            return;
        }

        if (Collision.canMove(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
            hitBox.y += airSpeed;
            airSpeed += gravity;
        } else {
            hitBox.y = Collision.getEntityYPositionUnderRoofOrAboveFloor(hitBox, airSpeed);

            if (airSpeed > 0) {
                resetInAir();
            } else {
                airSpeed = fallSpeedAfterCollision;
            }
        }
    }

    private void resolveJump() {
        if (inAir) {
            return;
        }

        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(int xSpeed) {
        boolean canMove = Collision.canMove(
                hitBox.x + xSpeed,
                hitBox.y,
                hitBox.width,
                hitBox.height,
                levelData
        );

        hitBox.x = canMove
                ? hitBox.x + xSpeed
                : Collision.getEntityXPositionNextToWall(hitBox, xSpeed);
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
        return isCurrentDirectionActive(Direction.RIGHT) || isCurrentDirectionActive(Direction.LEFT);
    }

    private boolean isCurrentDirectionActive(Direction direction) {
        return currentDirection.get(direction);
    }
}
