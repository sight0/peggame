package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Testable
public class SquarePegGame implements PegGame{

    private GameState state;
    private char[][] board; // 2D Array

    public SquarePegGame(char[][] board) {
        this.state = GameState.NOT_STARTED;
        this.board = board;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        List<Move> possibleMoves = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'o') {
                    // Check for horizontal and vertical jumps
                    checkDirection(possibleMoves, row, col, -2, 0); // Up
                    checkDirection(possibleMoves, row, col, 2, 0);  // Down
                    checkDirection(possibleMoves, row, col, 0, -2); // Left
                    checkDirection(possibleMoves, row, col, 0, 2);  // Right
                    // Check for diagonal jumps
                    checkDirection(possibleMoves, row, col, -2, -2); // Up-Left
                    checkDirection(possibleMoves, row, col, -2, 2);  // Up-Right
                    checkDirection(possibleMoves, row, col, 2, -2);  // Down-Left
                    checkDirection(possibleMoves, row, col, 2, 2);   // Down-Right
                }
            }
        }
        return possibleMoves;
    }

    private void checkDirection(List<Move> moves, int r1, int c1, int verticalOffset, int horizontalOffset) {
        int targetRow = r1 + verticalOffset;
        int targetCol = c1 + horizontalOffset;
        int overRow = r1 + verticalOffset / 2;
        int overCol = c1 + horizontalOffset / 2;

        if (targetRow >= 0 && targetRow < board.length &&
                targetCol >= 0 && targetCol < board[0].length &&
                board[targetRow][targetCol] == '.' && board[overRow][overCol] == 'o') {
            moves.add(new Move(new Location(r1, c1), new Location(targetRow, targetCol)));
        }
    }

    @Override
    public GameState getGameState() {
        return this.state;
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
        Location from = move.getFrom();
        Location to = move.getTo();
        Location over = new Location((from.getRow() + to.getRow()) / 2, (from.getCol() + to.getCol()) / 2);

        if (!getPossibleMoves().contains(move)) {
            throw new PegGameException("Invalid move!");
        }

        board[from.getRow()][from.getCol()] = '.';
        board[to.getRow()][to.getCol()] = 'o';
        board[over.getRow()][over.getCol()] = '.';

        if (getPossibleMoves().isEmpty()) {
            if(countPegs() == 1){
                this.state = GameState.WON;
            }else{
                this.state = GameState.STALEMATE;
            }
        } else {
            state = GameState.IN_PROGRESS;
        }
    }

    private int countPegs() {
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'o') {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        String boardString = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == '.')
                    boardString += '-' + " ";
                else
                    boardString += 'o' + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }

    @Test
    public void testInitialState() {
        char[][] board = {
                {'o', 'o', '.'},
                {'.', '.', '.'},
                {'.', '.', 'o'}
        };
        SquarePegGame game = new SquarePegGame(board);
        assertEquals(GameState.NOT_STARTED, game.getGameState(), "Initial state should be NOT_STARTED");
    }

    @Test
    public void testPossibleMoves() {
        char[][] board = {
                {'o', 'o', '.'},
                {'.', '.', '.'},
                {'.', '.', 'o'}
        };
        SquarePegGame game = new SquarePegGame(board);
        Collection<Move> moves = game.getPossibleMoves();
        assertNotNull(moves, "Possible moves should not be null");
        assertFalse(moves.isEmpty(), "There should be possible moves");
    }

}
