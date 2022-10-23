package ui.button;

import utils.load.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SoundButton extends PauseButton {
    private BufferedImage[][] images;
    private boolean isMouseOver, isMousePressed;
    private boolean muted;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        loadImages();
    }

    public void update() {

    }

    public void render(Graphics graphics) {
        graphics.drawImage(images[0][0], x, y, width, height, null);
    }

    private void loadImages() {
        BufferedImage spriteAtlas = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTON_ATLAS);

        images = new BufferedImage[2][3];

        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                images[i][j] = spriteAtlas.getSubimage(
                        j * PauseButton.DEFAULT_SIZE,
                        i * PauseButton.DEFAULT_SIZE,
                        PauseButton.DEFAULT_SIZE,
                        PauseButton.DEFAULT_SIZE
                );
            }
        }
    }
}
