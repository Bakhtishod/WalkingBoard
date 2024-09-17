package walking.game.player;

public class MadlyRotatingBuccaneer extends Player {
    private int turnCount;

    public MadlyRotatingBuccaneer() {
    }

    @Override
    public void turn() {
        turnCount++;
    }
}