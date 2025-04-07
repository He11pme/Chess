public class Pawn extends ChessPiece {
    /** Почему-то Online выдает ошибку */

    @Override
    public String getColor() {
        return color;
    }

    public Pawn(String color) {
        super(color);
    }

//    public boolean isFirstMove = true;

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isAte = false;
        if (chessBoard.board[toLine][toColumn] != null) {
            isAte = !chessBoard.board[toLine][toColumn].getColor().equals(getColor());
        }

        if (!(range(toLine) && range(toColumn))) return false;

        // Relative coordinate
        int relativeLine = toLine - line;
        if (color.equals("Black")) relativeLine *= -1;
        // Maybe this will come in handy later
        int relativeColumn = Math.abs(toColumn - column);
        // If a pawn ate another pawn, it can move diagonally
        if (relativeColumn == 1 && relativeLine == 1 && isAte) {
            return true;
        }

        //Not quite correct variant. Pawn can move in diagonal if it kills another pawn. But later
        if (column != toColumn) return false;
        if (isFirstMove && relativeLine == 2) {
            int direction = getColor().equals("White") ? 1 : -1;
            if (chessBoard.board[line + direction][toColumn] != null) return false;
            else if (!isAte) return true;
        }
        if (relativeLine == 1 && !isAte) {
            return true;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }

    private boolean range(int i) {
        return (i >= 0) && (i <= 7);
    }


}
