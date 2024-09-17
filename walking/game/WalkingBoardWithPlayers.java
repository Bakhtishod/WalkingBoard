package walking.game;

import walking.game.player.*;
import walking.game.WalkingBoard;

public class WalkingBoardWithPlayers extends WalkingBoard {
    private Player[] players;
    private int round;
    public static final int SCORE_EACH_STEP = 13;

    public WalkingBoardWithPlayers(int[][] board, int playerCount) {
        super(board);
        initPlayers(playerCount);
    }

    public WalkingBoardWithPlayers(int size, int playerCount) {
        super(size);
        initPlayers(playerCount);
    }

    private void initPlayers(int playerCount) {
        if (playerCount < 2)
            throw new IllegalArgumentException("Error in number of players!");
        players = new Player[playerCount];
        players[0] = new MadlyRotatingBuccaneer();
        for (int i = 1; i < playerCount; i++)
            players[i] = new Player();
    }

    public int[] walk(int... stepCounts) {
        int[] results = new int[players.length];
        int totalSteps = 0;
        int index = 0;

        for (int steps : stepCounts) {
            Player currentPlayer = players[index];
            
            currentPlayer.turn();
            totalSteps += steps;
            int score = super.moveAndSet(currentPlayer.getDirection(), Math.min(SCORE_EACH_STEP, totalSteps));
            currentPlayer.addToScore(score);
            results[index] = currentPlayer.getScore();
            index = (index + 1) % players.length;
        }
        return results;
    }
}
