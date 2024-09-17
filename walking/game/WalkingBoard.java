package walking.game;

import walking.game.util.Direction;

import java.util.Arrays;

public class WalkingBoard {
    private int[][] tiles;
    private int x;
    private int y;
    public static final int BASE_TILE_SCORE = 3;

    public WalkingBoard(int size) {
        tiles = new int[size][size];

        for (int i = 0; i < tiles.length; i++)
            Arrays.fill(tiles[i], BASE_TILE_SCORE);
    }

    public WalkingBoard(int[][] tiles) {
        this.tiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = Arrays.copyOf(tiles[i], tiles[i].length);
            for (int j = 0; j < tiles[i].length; j++)
                this.tiles[i][j] = BASE_TILE_SCORE > this.tiles[i][j] ? BASE_TILE_SCORE : this.tiles[i][j];
        }
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && y >= 0 && x < tiles.length && y < tiles[x].length;
    }

    public int getTile(int x, int y) {
        if (isValidPosition(x, y)) {
            return tiles[x][y];
        }
        throw new IllegalArgumentException("Invalid position");
    }

    public int[][] getTiles() {
        int[][] allTiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++)
            allTiles[i] = Arrays.copyOf(tiles[i], tiles[i].length);
        return allTiles;
    }

    public static int getXStep(Direction direction) {
        if (direction == Direction.RIGHT) {
            return 1;
        } else if (direction == Direction.LEFT) {
            return -1;
        } else {
            return 0;
        }
    }

    public static int getYStep(Direction direction) {
        if (direction == Direction.UP) {
            return 1;
        } else if (direction == Direction.DOWN) {
            return -1;
        } else {
            return 0;
        }
    }

    public int moveAndSet(Direction direction, int value) {
        int x = this.x + getXStep(direction);
        int y = this.y + getYStep(direction);

        if (isValidPosition(x, y)) {
            int oldVal = tiles[x][y];
            tiles[x][y] = value;
            this.x = x;
            this.y = y;

            return oldVal;
        } else {
            return 0;
        }
    }
}
