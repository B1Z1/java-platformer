package levels;

import main.Game;
import utils.load.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level firstLevel;

    public LevelManager(
            Game game
    ) {
        this.game = game;
        this.firstLevel = new Level(LoadSave.getLevelData());

        importSprites();
    }

    public void render(Graphics graphics) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                int index = firstLevel.getSpriteIndex(j, i);
                int x = Game.TILES_SIZE * j;
                int y = Game.TILES_SIZE * i;

                graphics.drawImage(levelSprite[index], x, y, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    private void importSprites() {
        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);

        levelSprite = new BufferedImage[48];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                int index = i * 12 + j;

                levelSprite[index] = image.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }
}
