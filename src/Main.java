public class Main {
    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("Black");
        for (int i = 0; i < 8; i++) {
            board.board[1][i] = new Pawn("White");
            board.board[6][i] = new Pawn("Black");
        }

        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        board.printBoard();
        if(board.moveToPosition(6, 2, 7, 2)) board.printBoard();
        else System.out.println("FALSE");
        if(board.moveToPosition(1, 2, 3, 2)) board.printBoard();
        else System.out.println("FALSE");
        if(board.moveToPosition(6, 3, 5, 3)) board.printBoard();
        else System.out.println("FALSE");


    }
}

