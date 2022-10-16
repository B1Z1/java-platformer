package inputs;

import entities.player.Player;
import main.GamePanel;
import utils.direction.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private Player player;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.player = this.gamePanel.getGame().getPlayer();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
            }
            case KeyEvent.VK_A -> {
                player.setDirection(Direction.LEFT, true);
            }
            case KeyEvent.VK_S -> {
            }
            case KeyEvent.VK_D -> {
                player.setDirection(Direction.RIGHT, true);
            }
            case KeyEvent.VK_SPACE -> {
                player.setJump(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
            }
            case KeyEvent.VK_A -> {
                player.setDirection(Direction.LEFT, false);
            }
            case KeyEvent.VK_S -> {
            }
            case KeyEvent.VK_D -> {
                player.setDirection(Direction.RIGHT, false);
            }
            case KeyEvent.VK_SPACE -> {
                player.setJump(false);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
