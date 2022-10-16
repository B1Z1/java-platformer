package game.states;

import main.Game;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class State implements KeyListener, MouseListener, MouseMotionListener {
    protected Game game;

    public State(
            Game game
    ) {
        this.game = game;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);
}
