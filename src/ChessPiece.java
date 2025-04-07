abstract public class ChessPiece {

    // Methods getColor() and getSymbol() can be defined in this class.
    // But first, I'll do the course
    public String color;

    public boolean isFirstMove = true;

//    public String getColor() {
//        return color;
//    }

    abstract public String getColor();

    public ChessPiece(String color) {
        this.color = color;
    }

    abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
    abstract String getSymbol();

}
