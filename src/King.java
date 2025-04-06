public class King extends ChessPiece{
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Проверка находится ли конечная точка хода на доске
        if (!(range(toLine) && range(toColumn))) return false;

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

        if (relativeLine + relativeColumn == 1 || (relativeColumn == relativeLine && relativeColumn + relativeLine == 2)) {
            check = false;
            return true;
        }

//        if (relativeLine + relativeColumn == 1 || ((relativeLine + relativeColumn == 2) && (relativeLine != 0 || relativeColumn != 0))) return true;
        return false;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                ChessPiece inspectedChessPiece = board.board[i][j];
                if (!(inspectedChessPiece.getColor().equals(getColor())) && inspectedChessPiece.canMoveToPosition(board, i, j, line, column)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }
}
