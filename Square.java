package midterm;

import java.util.ArrayList;

public class Square extends ChessBoard {

    public Piece piece = null;

    public Piece getPiece() {
        return piece;
    }

    public String getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }

    private String row;
    private String column;

    public Square(String column , String row ){
        this.row = row;
        this.column = column;

    }


    public int getRowDistance(Square location) {
        Integer currentRow = Integer.parseInt(this.row); //They was string now they are integer and now we can do operations.
        Integer targetRow = Integer.parseInt(location.getRow());
        return currentRow - targetRow;
    }

    public boolean isAtSameColumn(Square targetLocation) {
        int columnLocation = SquareLocation(targetLocation.getColumn() + targetLocation.getRow())[1];
        int newLocation = SquareLocation(column + row)[1];
        return columnLocation == newLocation;
    }

    public boolean isAtSameRow(Square targetLocation){
        int rowLocation = SquareLocation(targetLocation.getColumn() + getRow())[0];
        int newLocation = SquareLocation(column+row)[0];
        return rowLocation == newLocation;
    }

    @Override
    public int[] SquareLocation(String to) {
        return super.SquareLocation(to);
    }


    public boolean isEmpty() {
        return this.piece == null;
    }

    public boolean isNeighborColumn(Square targetLocation) {
        int[] locationTarget = SquareLocation(targetLocation.getColumn() + targetLocation.getRow());
        int[] locationThis = SquareLocation(column+row);
        int targetRow = locationTarget[0];
        int targetColumn = locationTarget[1];
        int thisRow = locationThis[0];
        int thisColumn = locationThis[1];

        return Math.abs(targetRow - thisRow) == 1 && (Math.abs(targetColumn - thisColumn) == 1 || Math.abs(targetColumn - thisColumn) == 2); // to check all diagonals
    }

    public boolean isAtLastRow(int color) {
        color = turn;
        if (turn == 0){
            return getRow().equals("8");
        }else{
            return getRow().equals("1");
        }
    }

    public void putNewQueen(int color) {
        this.piece = new Queen(color,this);
    }

    public void clear() {
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    //in this method we need to access all diagonals so chessboard has a pattern for this when you want to move just 1 square diagonal
    //you should increase/decrease your row and column +1/-1
    //this is for Bishop and Queen
    public boolean isDiagonal(Square targetLocation){
        ArrayList<Square> diagonalList = new ArrayList<>();
        int[] squareLocation = SquareLocation(this.getColumn() + this.getRow());
        int diaRow = squareLocation[0];
        int diaCol = squareLocation[1];

        int newDiaRow = diaRow +1;
        int newDiaCol = diaCol +1;
        while (isValid(newDiaRow,newDiaCol)){

            diagonalList.add(board[newDiaRow][newDiaCol]);
            newDiaRow++;
            newDiaCol++;
        }
        newDiaRow = diaRow - 1;
        newDiaCol = diaCol - 1;
        while (isValid(newDiaRow,newDiaCol)){

            diagonalList.add(board[newDiaRow][newDiaCol]);
            newDiaRow--;
            newDiaCol--;
        }
        newDiaRow = diaRow - 1;
        newDiaCol = diaCol + 1;
        while (isValid(newDiaRow,newDiaCol)){

            diagonalList.add(board[newDiaRow][newDiaCol]);
            newDiaRow--;
            newDiaCol++;
        }
        newDiaRow = diaRow + 1;
        newDiaCol = diaCol - 1;
        while (isValid(newDiaRow,newDiaCol)){

            diagonalList.add(board[newDiaRow][newDiaCol]);
            newDiaRow++;
            newDiaCol--;
        }

        return diagonalList.contains(targetLocation);
    }

    public ArrayList<Square> KingMovements(){

        ArrayList<Square> moves = new ArrayList<>();
        int[] arrayLocation = SquareLocation(column+row);
        int currentRow = arrayLocation[0];
        int currentColumn = arrayLocation[1];


        if((currentColumn + 1) < 8) {
            moves.add(board[currentRow][currentColumn + 1]);
            if(currentRow - 1 >= 0) {
                moves.add(board[currentRow - 1][currentColumn + 1]);
            }
            if(currentRow + 1 < 8) {
                moves.add(board[currentRow + 1][currentColumn + 1]);
            }
        }
        if((currentColumn - 1) >= 0) {
            moves.add(board[currentRow][currentColumn - 1]);
            if(currentRow - 1 >= 0) {
                moves.add(board[currentRow - 1][currentColumn - 1]);
            }
            if(currentRow + 1 < 8) {
                moves.add(board[currentRow + 1][currentColumn - 1]);
            }
        }
        if((currentRow - 1) >= 0) {
            moves.add(board[currentRow - 1][currentColumn]);
        }
        if((currentRow + 1) < 8) {
            moves.add(board[currentRow + 1][currentColumn]);
        }

        return moves;
    }



    public boolean KnightMovements(Square targetLocation){
        ArrayList<Square> knightList = new ArrayList<>();
        int [] arrayLocation = SquareLocation(this.getColumn() + this.getRow());
        int knightRow = arrayLocation[0];
        int knightCol = arrayLocation[1];

        for (int row=-2; row <= 2; row++) {
            for(int column=-2; column<=2; column++) {
                if(Math.abs(row * column) == 2 && isValid(row+knightRow,column+knightCol)) {
                    knightList.add(board[row+knightRow][column+knightCol]);
                }
            }
        }

        return knightList.contains(targetLocation);


    }



    //in this method we check valid squares.from beginning to last square.
    public boolean isValid(int row , int column){
        return column >= 0 && column<=7 && row>=0 && row<=7;
    }
}
