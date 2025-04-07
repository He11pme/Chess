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

        //Если цвет фигур совпадают, то хода нет
        if (chessBoard.board[toLine][toColumn] != null) {
            if (chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return false;
            }
        }

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

        int directionLine = line < toLine ? 1 : -1;
        int directionColumn = column < toColumn ? 1 : -1;

        //Проверка на прямолинейное движение
        // Одна из координат обязательна должна быть равно 0, но при этом обе не могут.
        if (relativeLine == 0 ^ relativeColumn == 0) {
            //Проверка на наличие фигур на пути
            int direction = line < toLine || column < toColumn ? 1 : -1;
            if (relativeColumn == 0) {
                for (int i = line + direction; i != toLine; i += direction) {
                    if (chessBoard.board[i][column] != null) return false;
                }
            } else {
                for (int i = column + direction; i != toColumn - 1; i += direction) {
                    if (chessBoard.board[line][i] != null) return false;
                }
            }
            return true;
        }
        //Проверка на движение по диагонали
        // При ходе по диагонали относительные координаты равны. Но надо проверить на то, чтобы они не были нулевыми
        if (relativeLine == relativeColumn && relativeLine != 0) {
            // Проверка на наличие фигур на пути. Цвет фигур не имеет значение. При этом фигура в конце не учитывается.
            // Она в любом случае уже будет противоположного цвета.
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
//        return checkDiagonalMove(relativeLine, relativeColumn) || checkStraightMove(relativeLine, relativeColumn);
    }

    @Override
    String getSymbol() {
        return "Q";
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }
}
