package ui.button;

import main.Game;

import java.awt.Rectangle;

public class PauseButton {
    public static final int DEFAULT_SIZE = 42;
    public static final int SIZE = (int) (DEFAULT_SIZE * Game.TILES_DEFAULT_SCALE);

    protected int x, y, width, height;
    protected Rectangle bounds;

    public PauseButton(
            int x,
            int y,
            int width,
            int height
    ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBounds();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private void initBounds() {
        bounds = new Rectangle(x, y, width, height);
    }
}
