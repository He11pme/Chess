abstract public class ChessPiece {

    private String color;
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
