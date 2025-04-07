abstract public class ChessPiece {

    private final String color;
    private final String symbol;

    public boolean isFirstMove = true;

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        if(color.equals("Black")) return "\u001B[31m" + symbol + "\u001B[0m";
        else return symbol;
    }

    public ChessPiece(String color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    boolean checkColor(ChessPiece[][] board, int toLine, int toColumn) {
        if (board[toLine][toColumn] != null) {
            if (board[toLine][toColumn].getColor().equals(getColor())) {
                return true;
            }
        }
        return false;
    }

    abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

}
