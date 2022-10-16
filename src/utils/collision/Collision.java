package utils.collision;

import main.Game;

import java.awt.geom.Rectangle2D;

public class Collision {
    public static boolean canMove(float x, float y, float width, float height, int[][] levelData) {
        return !isSolid(x, y, levelData)
                && !isSolid(x + width, y, levelData)
                && !isSolid(x + width, y + height, levelData)
                && !isSolid(x, y + height, levelData);
    }

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) (hitBox.x / Game.TILES_SIZE);

        if (xSpeed > 0) {
            int tileXPosition = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitBox.width);

            return tileXPosition + xOffset - 1;
        }

        return currentTile * Game.TILES_SIZE;
    }

    public static float getEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) (hitBox.y / Game.TILES_SIZE);

        if (airSpeed > 0) {
            int tileYPosition = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitBox.height);

            return tileYPosition + yOffset - 1;
        }

        return currentTile * Game.TILES_SIZE;
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        return isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData)
                && isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData);
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if ((x <= 0 || x >= Game.GAME_WIDTH) || (y <= 0 || y >= Game.GAME_HEIGHT)) {
            return true;
        }

        int xIndex = (int) x / Game.TILES_SIZE;
        int yIndex = (int) y / Game.TILES_SIZE;
        int value = levelData[yIndex][xIndex];

        return value >= 48 || value < 0 || value != 11;
    }
}
