public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color, "B");
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //Если цвет фигур совпадают, то хода нет
        if (checkColor(chessBoard.board, toLine, toColumn)) return false;

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

        // При ходе по диагонали относительные координаты равны. Но надо проверить на то, чтобы они не были нулевыми
        if (relativeLine == relativeColumn && relativeLine != 0) {
            // Проверка на наличие фигур на пути. Цвет фигур не имеет значение. При этом фигура в конце не учитывается.
            // Она в любом случае уже будет противоположного цвета.
            int directionLine = line < toLine ? 1 : -1;
            int directionColumn = column < toColumn ? 1 : -1;
            int i = line + directionLine;
            int j = column + directionColumn;
            while (i != toLine && j != toColumn) {
                // Если позиция хода не равна null, то хода нет
                if (chessBoard.board[i][j] != null) return false;

                i += directionLine;
                j += directionColumn;
            }
            return true;
        }
        return false;
    }
}
