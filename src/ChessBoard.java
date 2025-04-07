public class ChessBoard {
    final String WHITE_PLAYER = "White";
    final String BLACK_PLAYER = "Black";

    public ChessPiece[][] board = new ChessPiece[8][8];
    private String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && checkPos(endLine) && checkPos(endColumn)) {

            // The color of the selected piece does not match the current player
            if(!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if(board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn];

                //If a pawn reaches the other border of the playing field, it turns into a queen
                if(board[endLine][endColumn].getSymbol().equals("P")) {
                    int lineTurned = board[endLine][endColumn].getColor().equals(WHITE_PLAYER) ? 7 : 0;
                    if (endLine == lineTurned) {
                        board[endLine][endColumn] = new Queen(nowPlayer);
                    }
                }

                board[endLine][endColumn].isFirstMove = false;
                board[startLine][startColumn] = null;

                nowPlayer = nowPlayerColor().equals(WHITE_PLAYER) ? BLACK_PLAYER : WHITE_PLAYER;

                isCheck();  //Checkamte check

                return true;

            } else return false;

        } else return false;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println(" \t  \u001B[33mA   B   C   D   E   F   G   H\u001B[0m");
        System.out.println();

        for (int i = 7; i > -1; i--) {
            System.out.print("\u001B[33m" + (i + 1) + "\t \u001B[0m");
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == null) {
                    System.out.print("[ ] ");
                } else {
                    System.out.print(" " + board[i][j].getSymbol() + "  ");
                }
            }
            System.out.print("\u001B[33m \t" + (i + 1) + "\u001B[0m");
            System.out.println();
            System.out.println();
        }

        System.out.println(" \t  \u001B[33mA   B   C   D   E   F   G   H\u001B[0m");
        System.out.println();
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    /**
     * The parameter column can only be 0 or 7.
     * This number indicates on which side castling will be performed
     * */
    public boolean castling(int column) {
        int lineKing = this.nowPlayerColor().equals(WHITE_PLAYER) ? 0 : 7;
        if(board[lineKing][column] == null || board[lineKing][4] == null) return false;

        //Проверка, что между королем и ладьей никого нет
        int start = column == 0 ? 1 : 5;
        int end = column == 0 ? 3 : 6;
        for(int i = start; i <= end; i++) {
            if(board[lineKing][i] != null) return false;
        }

        int endPosKing = column == 0 ? 2 : 6;
        int endPosRook = column == 0 ? 3 : 5;
        //Заменил equals на contains, потому что добавил цвет фигур
        if (board[lineKing][column].getSymbol().contains("R") &&
                board[lineKing][4].getSymbol().contains("K") &&
                board[lineKing][column].isFirstMove && board[lineKing][4].isFirstMove && !new King(WHITE_PLAYER).isUnderAttack(this, lineKing, endPosKing)) {

            // Move King
            board[lineKing][endPosKing] = board[lineKing][4];
            board[lineKing][endPosKing].isFirstMove = false;
            board[lineKing][4] = null;

            // Move Rook
            board[lineKing][endPosRook] = board[lineKing][0];
            board[lineKing][endPosRook].isFirstMove = false;
            board[lineKing][0] = null;

            this.nowPlayer = this.nowPlayerColor().equals(WHITE_PLAYER) ? BLACK_PLAYER : WHITE_PLAYER;
            return true;
        } else return false;
    }

    // Метод проверяет, поставили ли данным ходом любому из королей шах
    private void isCheck() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == null) continue;

                if(board[i][j].getSymbol().equals("K")) {
                    ((King) board[i][j]).isUnderAttack(this, i, j);
                }
            }
        }
    }

}
