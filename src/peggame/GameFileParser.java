package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Testable
public class GameFileParser {

    public static PegGame readGameFromFile(String filename) throws IOException {
        // Function takes a filename and returns a PegGame<Interface> implementation
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int size = Integer.parseInt(reader.readLine());
            char[][] board = new char[size][size];
            for (int i = 0; i < size; i++) {
                String line = reader.readLine().substring(0,size);
                board[i] = line.toCharArray();
            }
            // TODO: Assume no variations implemented yet so we return a SquarePegGame Object
            return new SquarePegGame(board);
        }
    }

    @Test
    public void testReadGameFromFile() throws IOException {
        String testFilePath = "gm1.txt";
        PegGame game = GameFileParser.readGameFromFile(testFilePath);

        assertNotNull(game, "Game should not be null");
        assertTrue(game instanceof SquarePegGame, "Game should be instance of SquarePegGame");
        SquarePegGame squareGame = (SquarePegGame) game;
        char[][] board = squareGame.getBoard();
        assertEquals(3, board.length, "Board should have 3 rows");

        assertEquals('o', board[0][0], "Expected peg at (0,0)");
        assertEquals('o', board[0][1], "Expected peg at (0,1)");
        assertEquals('.', board[0][2], "Expected empty space at (0,2)");
        assertEquals('.', board[1][0], "Expected empty space at (1,0)");
        assertEquals('.', board[1][1], "Expected empty space at (1,1)");
        assertEquals('.', board[1][2], "Expected empty space at (1,2)");
        assertEquals('.', board[2][0], "Expected empty space at (2,0)");
        assertEquals('.', board[2][1], "Expected empty space at (2,1)");
        assertEquals('.', board[2][2], "Expected empty space at (2,2)");
    }

}
