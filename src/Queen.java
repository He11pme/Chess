public class Queen extends ChessPiece{
    public Queen(String color) {
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

        return checkDiagonalMove(relativeLine, relativeColumn) || checkStraightMove(relativeLine, relativeColumn);
    }

    @Override
    String getSymbol() {
        return "Q";
    }

    private boolean checkDiagonalMove(int relativeLine, int relativeColumn) {
        return relativeLine == relativeColumn && relativeLine != 0;
    }
    private boolean checkStraightMove(int relativeLine, int relativeColumn) {
        return (relativeLine == 0 || relativeColumn == 0) && relativeColumn + relativeLine != 0;
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }
}
