package walking.game;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import check.*;
import walking.game.util.Direction;

public class WalkingBoardTest {
    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4", "5"})
    public void testSimpleInit(int size) {
        WalkingBoard board = new WalkingBoard(size);
        int[][] tiles = board.getTiles();

        assertEquals(size, tiles.length);
        assertEquals(size, tiles[size - 1].length);
        assertEquals(WalkingBoard.BASE_TILE_SCORE, tiles[0][0]);
        assertEquals(WalkingBoard.BASE_TILE_SCORE, tiles[size - 1][size - 1]);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 5", "0, 1, 7",
            "1, 0, 10", "1, 1, 6"
    })
    public void testCustomInit(int x, int y, int expected) {
        int[][] myTiles = {
                {5, 7},
                {10, 6}
        };

        var board = new WalkingBoard(myTiles);
        if (myTiles[x][y] < WalkingBoard.BASE_TILE_SCORE) {
            assertEquals(WalkingBoard.BASE_TILE_SCORE, expected);
            assertNotEquals(myTiles[x][y], expected);
        } else {
            assertEquals(myTiles[x][y], expected);
        }

        int temp = Math.max(myTiles[x][y], WalkingBoard.BASE_TILE_SCORE);
        myTiles[x][y] = 7;
        assertEquals(temp, board.getTile(x, y));

        int[][] tiles = board.getTiles();
        int temp2 = tiles[x][y];
        tiles[x][y] = 10;
        assertEquals(temp2, board.getTile(x, y));
    }

    @Test
    public void testMoves() {
        int[][] tiles = {
                {1, 2, 4},
                {9, 8, 7},
                {4, 6, 0}
        };

        WalkingBoard board = new WalkingBoard(tiles);

        assertEquals(0, board.moveAndSet(Direction.DOWN, 2));
        assertEquals(0, board.getPosition()[0]);
        assertEquals(3, board.getTile(0, 0));
        assertEquals(9, board.moveAndSet(Direction.RIGHT, 5));
        assertEquals(5, board.getTile(1, 0));
        assertEquals(8, board.moveAndSet(Direction.UP, 13));
        assertEquals(13, board.getTile(1, 1));
        assertEquals(3, board.moveAndSet(Direction.LEFT, 6));
        assertEquals(6, board.getTile(0, 1));
        assertEquals(3, board.moveAndSet(Direction.DOWN, 1));
        assertEquals(1, board.getTile(0, 0));
    }
}