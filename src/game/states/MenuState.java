package game.states;

import main.Game;
import ui.button.MenuButton;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState extends State {
    private MenuButton[] buttons = new MenuButton[3];

    public MenuState(Game game) {
        super(game);

        initButtons();
    }

    @Override
    public void update() {
        for (MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void render(Graphics graphics) {
        for (MenuButton button : buttons) {
            button.render(graphics);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameState.state = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (isInButton(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (isInButton(e, button) && button.isMousePressed()) {
                button.applyGameState();
                break;
            }
        }

        resetButtons();
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
        for (MenuButton button : buttons) {
            button.setMouseOver(false);
        }

        for (MenuButton button : buttons) {
            if (isInButton(e, button)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    private void initButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.TILES_DEFAULT_SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.TILES_DEFAULT_SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.TILES_DEFAULT_SCALE), 2, GameState.QUIT);
    }

    private void resetButtons() {
        for (MenuButton button : buttons) {
            button.resetMouseStates();
        }
    }
}
