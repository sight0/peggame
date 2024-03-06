package peggame;

import java.util.Collection;
import java.util.Scanner;

public interface PegGame {
    public Collection<Move> getPossibleMoves();
    public GameState getGameState();
    public void makeMove(Move move) throws PegGameException;

    public static void playGame(PegGame game, Scanner scanner) throws PegGameException // Added to start/play a game of any type/variation
    {

    }
}
