public class Bishop extends ChessPiece {

    public Bishop(String color) {
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

        // При ходе по диагонали относительные координаты равны. Но надо проверить на то, чтобы они не были нулевыми
        return relativeLine == relativeColumn && relativeLine != 0;
    }

    @Override
    String getSymbol() {
        return "B";
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }
}
