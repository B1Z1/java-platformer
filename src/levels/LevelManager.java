package levels;

import main.Game;
import utils.load.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private BufferedImage levelSprite;

    public LevelManager(
            Game game
    ) {
        this.game = game;
        levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(levelSprite, 0, 0, null);
    }

    public void update() {

    }
}
