abstract public class ChessPiece {

    // Methods getColor() and getSymbol() can be defined in this class.
    // But first, I'll do the course
    // Можно добавить проверку на тот же ход. А лучше наверное ChessBoard
    public String color;

    // I'd rename as isFirstMove
    public boolean check = true;

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
