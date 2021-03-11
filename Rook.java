package midterm;

public class Rook extends Piece {


    public Rook(int color, Square location) {
        super(color, location);
    }

    @Override
    public void move(String destination) {
        super.move(destination);
    }

    @Override
    public boolean canMove(String destination) {
        Square targetLocation = location.getBoard().getSquareAt(destination);
        boolean validMove = false;

        if (!targetLocation.isEmpty()&&targetLocation.getPiece().getColor()==this.color){ //Don't attack yourself
            return validMove;
        }
        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);

        if (this.location.isAtSameColumn(targetLocation) || this.location.isAtSameRow(targetLocation)) {
            for(int i = 0 ; i<between.length ; i++){
                if(!between[i].isEmpty()){
                    return validMove;
                }
            }
            return true;
        }

        return validMove;
    }
    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "R" : "r";
    }
}

