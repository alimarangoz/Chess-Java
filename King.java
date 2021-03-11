package midterm;

public class King extends Piece {

    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public void move(String destination) {
        super.move(destination);

    }

    @Override
    public boolean canMove(String destination) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(destination);
        if(!targetLocation.isEmpty() && targetLocation.getPiece().color==this.color){
            return validMove;
        }


        if(this.location.KingMovements().contains(targetLocation)){

            return true;
        }
        return validMove;
    }
    public String toString() {
        return color == ChessBoard.WHITE ? "K" : "k";
    }
}
