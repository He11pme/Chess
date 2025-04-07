public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color, "H");
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        //Если цвет фигур совпадают, то хода нет
        if (checkColor(chessBoard.board, toLine, toColumn)) return false;

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

        //Проверка строится на том, что относительная сумма координат для коня всегда равна 3
        //Но при этом ни одна из относительных координат не может быть 0 (тут суть в том, что 0 + 3 = 3)
        if (relativeLine == 0 || relativeColumn == 0) return false;
        return relativeLine + relativeColumn == 3;

    }
}
