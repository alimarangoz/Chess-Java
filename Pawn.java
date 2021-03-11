
package midterm;

public class Pawn extends Piece  {

    //piece has not been moved yet
    boolean initialLocation = true;

    public Pawn(int color, Square location) {
        super(color, location);
    }

    /**
     * Check whether the piece can move to the given coordinate
     * @param to
     * @return
     */
    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        //Get the row difference between the current location and target location.
        int rowDistance = targetLocation.getRowDistance(location);
        //if the target coordinate is at the same column

        if (this.location.isAtSameColumn(targetLocation)) {
            //for white check pawn is moving forward at most 2 Squares
            if (color == ChessBoard.WHITE && rowDistance > 0 && rowDistance <= 2) {
                if (rowDistance == 2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
                return validMove;
                //for black check pawn is moving forward at most 2 Squares
            } else if (color == ChessBoard.BLACK && rowDistance < 0 && rowDistance >= -2) {
                if (rowDistance == -2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
            }
            // if the target column is not at the same column, it should be a neighbour column
        } else if (this.location.isNeighborColumn(targetLocation)) {
            //pawn can only move to forward diagonals ig there is an opponen there(attack)
            if (color == ChessBoard.WHITE && rowDistance == 1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.BLACK;
            } else if (color == ChessBoard.BLACK && rowDistance == -1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.WHITE;
            }

        }
        return validMove;
    }

    @Override
    public void move(String to) {
        initialLocation = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
//promoteToQueen
        if (targetLocation.isAtLastRow(color)) {
            targetLocation.putNewQueen(color);
        } else {
            targetLocation.setPiece(this);
        }
//clear previous location
        location.clear();
//update current location
        location = targetLocation;
        location.getBoard().nextPlayer();
    }
    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "P" : "p";
    }
}

