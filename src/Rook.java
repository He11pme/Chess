public class Rook extends ChessPiece{
    @Override
    public String getColor() {
        return color;
    }

    public Rook(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Проверка находится ли конечная точка хода на доске
        if (!(range(toLine) && range(toColumn))) return false;

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

        if ((relativeLine == 0 || relativeColumn == 0) && relativeColumn + relativeLine != 0) {
            check = false;
            return true;
        }
        return false;
//        return (relativeLine == 0 || relativeColumn == 0) && relativeColumn + relativeLine != 0;

    }

    @Override
    String getSymbol() {
        return "R";
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }
}
