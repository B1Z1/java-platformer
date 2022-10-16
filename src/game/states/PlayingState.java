package game.states;

import entities.player.Player;
import levels.LevelManager;
import main.Game;
import utils.direction.Direction;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayingState extends State {
    private Player player;
    private LevelManager levelManager;

    public PlayingState(Game game) {
        super(game);

        initClasses();
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void render(Graphics graphics) {
        levelManager.render(graphics);
        player.render(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_SPACE -> {
                player.setJump(true);
            }
            case KeyEvent.VK_A -> {
                player.setDirection(Direction.LEFT, true);
            }
            case KeyEvent.VK_S -> {
            }
            case KeyEvent.VK_D -> {
                player.setDirection(Direction.RIGHT, true);
            }
            case KeyEvent.VK_ESCAPE -> {
                GameState.state = GameState.MENU;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_SPACE -> {
                player.setJump(false);
            }
            case KeyEvent.VK_A -> {
                player.setDirection(Direction.LEFT, false);
            }
            case KeyEvent.VK_S -> {
            }
            case KeyEvent.VK_D -> {
                player.setDirection(Direction.RIGHT, false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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

    }

    public Player getPlayer() {
        return player;
    }

    private void initClasses() {
        player = new Player(
                200,
                200,
                (int) (64 * Game.TILES_DEFAULT_SCALE),
                (int) (40 * Game.TILES_DEFAULT_SCALE)
        );
        levelManager = new LevelManager(game);

        player.loadLevelData(levelManager.getCurrentLevel().getData());
    }
}
