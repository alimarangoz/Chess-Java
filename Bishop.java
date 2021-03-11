package midterm;

public class Bishop extends Piece {
    public Bishop(int color, Square location) {
        super(color, location);
    }

    @Override
    public void move(String destination) {
        super.move(destination);
    }

    @Override
    public boolean canMove(String destination) { //Bishop just move diagonal

        boolean validMove = false;

        Square targetLocation = location.getBoard().getSquareAt(destination);

        if(!targetLocation.isEmpty() && targetLocation.getPiece().color == this.color){

            return validMove;
        }
        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);

        if (this.location.isDiagonal(targetLocation)){
            for(int i = 0; i < between.length ; i++){
                if(!between[i].isEmpty()){
                    return validMove;
                }
            }
            return true;
        }
        return validMove;
    }
    public String toString() {
        return color == ChessBoard.WHITE ? "B" : "b";
    }
}

