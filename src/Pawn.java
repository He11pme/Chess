public class Pawn extends ChessPiece {
    /** Почему-то Online выдает ошибку */

    @Override
    public String getColor() {
        return color;
    }

    public Pawn(String color) {
        super(color);
    }

    public boolean isFirstMove = true;

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!(range(toLine) && range(toColumn))) return false;

        // Relative coordinate
        int relativeLine = toLine - line;
        if (color.equals("Black")) relativeLine *= -1;
        // Maybe this will come in handy later
        int relativeColumn = Math.abs(toColumn - column);

        //Not quite correct variant. Pawn can move in diagonal if it kills another pawn. But later
        if (column != toColumn) return false;
        if ((isFirstMove && relativeLine == 2) || relativeLine == 1) {
            //Стоит ли каждый раз присваивать false, даже если уже false?
            isFirstMove = false;
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
