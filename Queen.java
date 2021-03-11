package midterm;

public class Queen extends Piece {
    public Queen(int color, Square location) {
        super(color, location);
    }

    @Override
    public void move(String destination) {
        super.move(destination);
    }

    @Override
    public boolean canMove(String destination) {
        boolean validMove = false;
        Square targetLocation = location.getSquareAt(destination);
        if(!targetLocation.isEmpty() && targetLocation.getPiece().color == this.color){
            return validMove;
        }
        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);

        if (this.location.isDiagonal(targetLocation) || this.location.isAtSameRow(targetLocation) || this.location.isAtSameColumn(targetLocation)){
            for(int i = 0 ; i<between.length;i++){
                if(!between[i].isEmpty()){
                    return validMove;
                }
            }
            return true;
        }
        return validMove;
     }
    public String toString() {
        return color == ChessBoard.WHITE ? "Q" : "q";
    }

}


