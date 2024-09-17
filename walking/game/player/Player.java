package walking.game.player;

import walking.game.util.Direction;

public class Player {
    private int score;
    protected Direction direction;

    public Player() {
        direction = Direction.UP;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public Direction getDirection() {
        return direction;
    }

    public void turn() {
        if (direction == Direction.UP) {
            direction = Direction.RIGHT;
        } else if (direction == Direction.RIGHT) {
            direction = Direction.DOWN;
        } else if (direction == Direction.DOWN) {
            direction = Direction.LEFT;
        } else if (direction == Direction.LEFT) {
            direction = Direction.UP;
        }
    }
}