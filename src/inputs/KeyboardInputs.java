package inputs;

import game.states.GameState;
import game.states.MenuState;
import game.states.PlayingState;
import main.Game;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private PlayingState playingState;
    private MenuState menuState;

    public KeyboardInputs(GamePanel gamePanel) {
        Game game = gamePanel.getGame();

        this.playingState = game.getPlayingState();
        this.menuState = game.getMenuState();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {
            case PLAYING -> playingState.keyPressed(e);
            case MENU -> menuState.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {
            case PLAYING -> playingState.keyReleased(e);
            case MENU -> menuState.keyReleased(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
