public class ChessBoard {
    final String WHITE_PLAYER = "White";
    final String BLACK_PLAYER = "Black";

    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            //Цвет фигуры не совпадает с текущим игроком
            if(!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;
            if(board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn];

                //Если пешка добралась до другой границы превратить в ферзя
                if(board[endLine][endColumn].getSymbol().equals("P")) {
                    int lineTurned = board[endLine][endColumn].getColor().equals(WHITE_PLAYER) ? 7 : 0;
                    if (endLine == lineTurned) {
                        board[endLine][endColumn] = new Queen(nowPlayer);
                    }
                }

                board[endLine][endColumn].isFirstMove = false;
                board[startLine][startColumn] = null;



                this.nowPlayer = this.nowPlayerColor().equals(WHITE_PLAYER) ? BLACK_PLAYER : WHITE_PLAYER;

                //Проверка на шах
                for(int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(board[i][j] == null) continue;
//                        if(board[i][j].getSymbol().equals("K") && board[i][j].getColor().equals(nowPlayerColor())) {
//                            ((King) board[i][j]).isUnderAttack(this, i, j);
//                        }
                        if(board[i][j].getSymbol().equals("K")) {
                            ((King) board[i][j]).isUnderAttack(this, i, j);
                        }
                    }
                }

                return true;
            } else return false;

        } else return false;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }

        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        int lineKing = this.nowPlayerColor().equals(WHITE_PLAYER) ? 0 : 7;
        if(board[lineKing][0] == null || board[lineKing][4] == null) return false;
        //Проверка, что между королем и ладьей никого нет
        for(int i = 1; i <= 3; i++) {
            if(board[lineKing][i] != null) return false;
        }
        if (board[lineKing][0].getSymbol().equals("R") &&
            board[lineKing][4].getSymbol().equals("K") &&
            board[lineKing][0].isFirstMove && board[lineKing][4].isFirstMove && !new King(WHITE_PLAYER).isUnderAttack(this, lineKing, 2)) {

            // Move King
            board[lineKing][2] = board[lineKing][4];
            board[lineKing][2].isFirstMove = false;
            board[lineKing][4] = null;

            // Move Rook
            board[lineKing][3] = board[lineKing][0];
            board[lineKing][3].isFirstMove = false;
            board[lineKing][0] = null;

            this.nowPlayer = this.nowPlayerColor().equals(WHITE_PLAYER) ? BLACK_PLAYER : WHITE_PLAYER;
            return true;
        } else return false;
    }

    public boolean castling7() {
        int lineKing = this.nowPlayerColor().equals(WHITE_PLAYER) ? 0 : 7;
        if(board[lineKing][7] == null || board[lineKing][4] == null) return false;
        //Проверка, что между королем и ладьей никого нет
        for(int i = 5; i <= 6; i++) {
            if(board[lineKing][i] != null) return false;
        }
        if (board[lineKing][7].getSymbol().equals("R") &&
                board[lineKing][4].getSymbol().equals("K") &&
                board[lineKing][7].isFirstMove && board[lineKing][4].isFirstMove && !new King(WHITE_PLAYER).isUnderAttack(this, lineKing, 6)) {

            // Move King
            board[lineKing][6] = board[lineKing][4];
            board[lineKing][6].isFirstMove = false;
            board[lineKing][4] = null;

            // Move Rook
            board[lineKing][5] = board[lineKing][7];
            board[lineKing][5].isFirstMove = false;
            board[lineKing][7] = null;

            this.nowPlayer = this.nowPlayerColor().equals(WHITE_PLAYER) ? BLACK_PLAYER : WHITE_PLAYER;
            return true;
        } else return false;
    }

}
