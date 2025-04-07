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

        //Если цвет фигур совпадают, то хода нет
        if (chessBoard.board[toLine][toColumn] != null) {
            if (chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return false;
            }
        }

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

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
        return false;

    }

    @Override
    String getSymbol() {
        return "R";
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }
}
