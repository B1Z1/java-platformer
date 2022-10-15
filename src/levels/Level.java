package levels;

public class Level {
    private int[][] data;

    public Level(
            int[][] data
    ) {
        this.data = data;
        System.out.println("Size" + data.length);
    }

    public int getSpriteIndex(int x, int y) {
        return data[y][x];
    }

    public int[][] getData() {
        return data;
    }
}
