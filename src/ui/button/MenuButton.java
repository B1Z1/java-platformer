package ui.button;

import game.states.GameState;
import main.Game;
import utils.load.LoadSave;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MenuButton {
    private static final int DEFAULT_WIDTH = 140;
    private static final int DEFAULT_HEIGHT = 56;
    private static final int WIDTH = (int) (DEFAULT_WIDTH * Game.TILES_DEFAULT_SCALE);
    private static final int HEIGHT = (int) (DEFAULT_HEIGHT * Game.TILES_DEFAULT_SCALE);

    private int x, y, rowIndex, stateIndex;
    private int xOffsetCenter = WIDTH / 2;
    private GameState state;
    private BufferedImage[] images;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(
            int x,
            int y,
            int rowIndex,
            GameState state
    ) {
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        this.state = state;

        loadImages();
        initBounds();
    }

    public void render(Graphics graphics) {
        graphics.drawImage(images[stateIndex], x - xOffsetCenter, y, WIDTH, HEIGHT, null);
    }

    public void update() {
        stateIndex = 0;

        if (mouseOver) {
            stateIndex = 1;
        }

        if (mousePressed) {
            stateIndex = 2;
        }
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        GameState.state = state;
    }

    public void resetMouseStates() {
        setMouseOver(false);
        setMousePressed(false);
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage spriteAtlas = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTON_ATLAS);

        for (int i = 0; i < images.length; i++) {
            images[i] = spriteAtlas.getSubimage(i * DEFAULT_WIDTH, rowIndex * DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }

    private void initBounds() {
        bounds = new Rectangle(x - xOffsetCenter, y, WIDTH, HEIGHT);
    }
}
