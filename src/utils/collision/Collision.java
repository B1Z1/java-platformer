package utils.collision;

import main.Game;

public class Collision {
    public static boolean canMove(float x, float y, float width, float height, int[][] levelData) {
        return !isSolid(x, y, levelData)
                && !isSolid(x + width, y, levelData)
                && !isSolid(x + width, y + height, levelData)
                && !isSolid(x, y + height, levelData);
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
