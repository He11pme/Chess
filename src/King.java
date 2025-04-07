public class King extends ChessPiece{
    public King(String color) {
        super(color, "K");
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        //Если цвет фигур совпадают, то хода нет
        if (checkColor(chessBoard.board, toLine, toColumn)) return false;

        //Относительные координаты от начальной точки хода
        int relativeLine = Math.abs(toLine - line);
        int relativeColumn = Math.abs(toColumn - column);

        if (relativeLine + relativeColumn == 1 || (relativeColumn == relativeLine && relativeColumn + relativeLine == 2)) {
            if (!isUnderAttack(chessBoard, toLine, toColumn)) {
                return true;
            }
        }

        return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece inspectedChessPiece = board.board[i][j];
                if (inspectedChessPiece == null || inspectedChessPiece.getColor().equals(getColor())) continue;
                if (inspectedChessPiece.canMoveToPosition(board, i, j, line, column)) {
                    System.out.println("ШАХ для " + getColor());
                    return true;
                }
            }
        }

        return false;
    }
}
