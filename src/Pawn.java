public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color, "P");
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isAte = false;
        if (chessBoard.board[toLine][toColumn] != null) {
            isAte = !chessBoard.board[toLine][toColumn].getColor().equals(getColor());
        }

        // Relative coordinate
        int relativeLine = getColor().equals("White") ? toLine - line : line - toLine;
//        if (getColor().equals("Black")) relativeLine *= -1;
        int relativeColumn = Math.abs(toColumn - column);

        // If a pawn ate another pawn, it can move diagonally
        if (relativeColumn == 1 && relativeLine == 1 && isAte) {
            return true;
        }

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
}
