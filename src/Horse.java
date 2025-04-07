public class Horse extends ChessPiece {

    // Methods getColor() and getSymbol() can be defined in the parent class.
    // But first, I'll do the course
    @Override
    public String getColor() {
        return color;
    }

    @Override
    String getSymbol() {
        return "H";
    }

    public Horse(String color) {
        super(color);
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

        //Проверка строится на том, что относительная сумма координат для коня всегда равна 3
        //Но при этом ни одна из относительных координат не может быть 0 (тут суть в том, что 0 + 3 = 3)
        if (relativeLine == 0 || relativeColumn == 0) return false;
        return relativeLine + relativeColumn == 3;
    }

    //В принципе это общие функции для всех фигур, можно перенести в ChessPiece
    //Или вообще сделать эту проверку в ChessBoard
    //Там как раз есть функция checkPos() аналогичная range
    private boolean range(int i, int start, int end) {
        return (i >= start) && (i <= end);
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }


}
