package entities.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle hitBox;

    public Entity(
            float x,
            float y,
            int width,
            int height
    ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initHitBox();
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    protected void updateHitBox() {
        hitBox.setLocation((int) x, (int) y);
    }

    protected void renderHitBox(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    private void initHitBox() {
        hitBox = new Rectangle((int) x, (int) y, width, height);
    }
}
