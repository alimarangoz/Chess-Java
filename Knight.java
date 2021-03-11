package midterm;

public class Knight extends Piece  {
    public Knight(int color, Square location) {
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

        if(!targetLocation.isEmpty()&&targetLocation.getPiece().color == this.color){
            return validMove;
        }
        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);

        if(this.location.KnightMovements(targetLocation)){
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
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}
