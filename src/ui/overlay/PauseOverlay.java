package ui.overlay;

import main.Game;
import ui.button.PauseButton;
import ui.button.SoundButton;
import utils.load.LoadSave;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PauseOverlay {
    private BufferedImage backgroundImage;
    private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;

    private SoundButton musicButton, sfxButton;

    public PauseOverlay() {
        initBackground();
        initSoundButtons();
    }

    public void update() {

    }

    public void render(Graphics graphics) {
        graphics.drawImage(backgroundImage, backgroundX, backgroundY, backgroundWidth, backgroundHeight, null);

        musicButton.render(graphics);
        sfxButton.render(graphics);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    private void initBackground() {
        backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PAUSE_MENU_BACKGROUND);
        backgroundWidth = (int) (backgroundImage.getWidth() * Game.TILES_DEFAULT_SCALE);
        backgroundHeight = (int) (backgroundImage.getHeight() * Game.TILES_DEFAULT_SCALE);
        backgroundX = Game.GAME_WIDTH / 2 - backgroundWidth / 2;
        backgroundY = Game.GAME_HEIGHT / 2 - backgroundHeight / 2;
    }

    private void initSoundButtons() {
        int soundX = (int) (450 * Game.TILES_DEFAULT_SCALE);
        int musicY = (int) (145 * Game.TILES_DEFAULT_SCALE);
        int sfxY = (int) (190 * Game.TILES_DEFAULT_SCALE);

        musicButton = new SoundButton(soundX, musicY, PauseButton.SIZE, PauseButton.SIZE);
        sfxButton = new SoundButton(soundX, sfxY, PauseButton.SIZE, PauseButton.SIZE);
    }
}
