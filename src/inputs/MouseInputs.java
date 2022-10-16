package inputs;

import game.states.GameState;
import game.states.MenuState;
import game.states.PlayingState;
import main.Game;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private PlayingState playingState;
    private MenuState menuState;

    public MouseInputs(GamePanel gamePanel) {
        Game game = gamePanel.getGame();

        this.playingState = game.getPlayingState();
        this.menuState = game.getMenuState();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> playingState.mouseClicked(e);
            case MENU -> menuState.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> playingState.mousePressed(e);
            case MENU -> menuState.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> playingState.mouseReleased(e);
            case MENU -> menuState.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> playingState.mouseMoved(e);
            case MENU -> menuState.mouseMoved(e);
        }
    }
}
