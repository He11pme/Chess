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

        //Если цвет фигур совпадают, то хода нет
        if (chessBoard.board[toLine][toColumn] != null) {
            if (chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return false;
            }
        }

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

        if (relativeLine + relativeColumn == 1 || (relativeColumn == relativeLine && relativeColumn + relativeLine == 2)) {
            return true;
        }

        return false;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece inspectedChessPiece = board.board[i][j];
                if (inspectedChessPiece == null) continue;
                if (!(inspectedChessPiece.getColor().equals(getColor())) && inspectedChessPiece.canMoveToPosition(board, i, j, line, column)) {
                    System.out.println("ШАХ для " + getColor());
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
