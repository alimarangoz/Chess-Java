
package midterm;

public abstract class  Piece {

    public int getColor() {
        return color;
    }

    public Square getLocation() {
        return location;
    }

    protected int color;
    protected Square location;

    public Piece(int color, Square location) {
        this.color = color;
        this.location = location;
    }

    public void move (String destination){
        Square targetLocation = location.getBoard().getSquareAt(destination);
        targetLocation.setPiece(location.piece);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    public abstract boolean  canMove(String destination); // We dont need to implement in this class so we can use abstract
}

