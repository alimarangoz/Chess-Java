
package midterm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Scanner reader = new Scanner(System.in);
        //Create a ChessBoard instance
        ChessBoard board = new ChessBoard();
        board.initialize();
        //print the board
        System.out.println(board);

        //while game not ended request moves from the user
        while (!board.isGameEnded()){
            System.out.println("It is " + (board.isWhitePlaying() ? "White" : "Black") + "'s turn");
            Piece piece = null;

            //While the given coordinate does not contain the piece of the current player
            //request a coordinate
            do {
                System.out.print("Enter the location of the piece:");
                String from = reader.next().toUpperCase(); //do not neccasary to use lower or upper letter.
                //get the piece at the given location
                // null means there is the Square is empty
                piece = board.getPieceAt(from);
                //piece color and current player's color should be consistent
            }while(piece == null || piece.getColor()!=(board.isWhitePlaying() ? ChessBoard.WHITE : ChessBoard.BLACK));


            String to = null;
            //while the target coordinate is not valid Square to move, request new coordinate
            do {
                System.out.print("Enter the new location of the piece:");
                to = reader.next().toUpperCase(); //do not neccasary to use lower or upper letter.
            }while(!piece.canMove(to));

            //move the piece to the target location
            piece.move(to);

            //print the board after the move
            System.out.println(board);
        }
        System.out.println((ChessBoard.turn == 0 ? "Black":"White") + " is the winner!!!" );
        reader.close();
    }
}

