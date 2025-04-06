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
                board[startLine][startColumn] = null;
                this.nowPlayer = this.nowPlayerColor().equals(WHITE_PLAYER) ? BLACK_PLAYER : WHITE_PLAYER;

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

    public boolean casting0() {
        if(board[0][0] == null && board[0][4] == null) return false;
        //Проверка, что между королем и ладьей никого нет
        for(int i = 1; i <= 3; i++) {
            if(board[0][i] != null) return false;
        }



        return false;
    }

}
