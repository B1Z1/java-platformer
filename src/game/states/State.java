package game.states;

import main.Game;
import ui.button.MenuButton;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
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

    public boolean isInButton(MouseEvent mouseEvent, MenuButton menuButton) {
        return menuButton.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
    }
}
