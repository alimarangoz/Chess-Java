package midterm;

import java.util.ArrayList;

public class ChessBoard  {

    protected static int turn = 0;
    protected static int WHITE = 0;
    protected static int BLACK = 1;

    protected static Square[][] board = new Square[8][8];

    public ChessBoard(){        //For super() error

    }

    public void initialize(){
        for(int i = 0 ; i<8 ; i++){
            char ch = 65; // ASCII TABLE
            for(int j = 0 ; j<8 ; j++){
                Square square = new Square(""+ ch ,""+(8-i));
                ChessBoard.board[i][j] = square;
                ch++;

            }
        }
        //Here I'm putting the pieces at their locations and color.
        board[7][0].piece = new Rook(0,board[7][0]);
        board[7][1].piece = new Knight(0,board[7][1]);
        board[7][2].piece = new Bishop(0,board[7][2]);
        board[7][3].piece = new Queen(0,board[7][3]);
        board[7][4].piece = new King(0,board[7][4]);
        board[7][5].piece = new Bishop(0,board[7][5]);
        board[7][6].piece = new Knight(0,board[7][6]);
        board[7][7].piece = new Rook(0,board[7][7]);

        board[0][0].piece = new Rook(1,board[0][0]);
        board[0][1].piece = new Knight(1,board[0][1]);
        board[0][2].piece = new Bishop(1,board[0][2]);
        board[0][3].piece = new Queen(1,board[0][3]);
        board[0][4].piece = new King(1,board[0][4]);
        board[0][5].piece = new Bishop(1,board[0][5]);
        board[0][6].piece = new Knight(1,board[0][6]);
        board[0][7].piece = new Rook(1,board[0][7]);

        //For pawns we can use loop because they are in same rows .

        for(int i = 0 ; i<8 ; i++){
            board[6][i].piece = new Pawn(0,board[6][i]);
        }

        for (int j = 0 ; j<8 ; j++){
            board[1][j].piece = new Pawn(1,board[1][j]);
        }


    }

    public String toString() {
        StringBuilder createBoard = new StringBuilder();

        createBoard.append("     A     B     C     D     E     F     G     H   \n");
        createBoard.append("   -------------------------------------------------\n");
        int x = 8;
        for (int i=0; i<8; i++) {
            createBoard.append(x);

            for (int j=0; j<8; j++) {
                createBoard.append(" |  " + (board[i][j].piece == null ? " " : board[i][j].piece)
                        + " ");
            }
            createBoard.append(" |  " + x);
            x--;
            createBoard.append("\n   -------------------------------------------------\n");
        }
        createBoard.append("     A     B     C     D     E     F     G     H\n");

        return createBoard.toString();
    }

    public ChessBoard getBoard() {
        return this;
    }


    public boolean isWhitePlaying() {
        return turn == WHITE;
    }


    public Square getSquareAt(String to) {
        int[] SquareLocation = SquareLocation(to);
        int SquareRow = SquareLocation[0];
        int SquareColumn = SquareLocation[1];
        System.out.println(board[SquareRow][SquareColumn]);
        return board[SquareRow][SquareColumn];
    }

    public int[] SquareLocation(String to) {

        String strColumn = to.substring(0,1);
        String strRow = to.substring(1);

        int SquareRow = 0;
        int SquareColumn = 0;

        Integer intRow = Integer.parseInt(strRow); // reverse string to integer

        // We change to constraction of array. When we type B5 it will return {4,1} = {row,column}

        if (strColumn.equals("A")) {
            SquareColumn = 0;
            SquareRow = 8 - intRow;
        } else if (strColumn.equals("B")) {
            SquareColumn = 1;
            SquareRow = 8 - intRow;
        } else if (strColumn.equals("C")) {
            SquareColumn = 2;
            SquareRow = 8 - intRow;
        } else if (strColumn.equals("D")) {
            SquareColumn = 3;
            SquareRow = 8 - intRow;
        } else if (strColumn.equals("E")) {
            SquareColumn = 4;
            SquareRow = 8 - intRow;
        } else if (strColumn.equals("F")) {
            SquareColumn = 5;
            SquareRow = 8 - intRow;
        } else if (strColumn.equals("G")) {
            SquareColumn = 6;
            SquareRow = 8 - intRow;
        } else if (strColumn.equals("H")) {
            SquareColumn = 7;
            SquareRow = 8 - intRow;
        }

        int[] newArray = {SquareRow,SquareColumn};

        return newArray;



    }

    public Square[] getSquaresBetween(Square location, Square targetLocation) {         //We need to check two array for moving //B3 equals {2,1} -- B5 eqauls {4,1} example
        int[] arrayLocation1 = SquareLocation(location.getColumn() + location.getRow());
        int[] arrayLocation2 = SquareLocation(targetLocation.getColumn() + targetLocation.getRow());
        int row1 = arrayLocation1[0]; // 2
        int row2 = arrayLocation2[0]; // 4
        int column1 = arrayLocation1[1]; // 1
        int column2 = arrayLocation2[1]; // 1

        ArrayList<Square> arrayList = new ArrayList<>();

        if(column1 == column2) {
            if (row1 >= row2) {
                for (int i = row2 + 1; i < row1; i++) {
                    arrayList.add(board[i][column1]);
                }
            } else {
                for (int i = row1 + 1; i < row2; i++) {
                    arrayList.add(board[i][column1]);
                }
            }
        } else if(row1 == row2) {
            if (column1 > column2) {
                for (int i = column2 + 1; i < column1; i++) {
                    arrayList.add(board[row1][i]);
                }
            } else {
                for (int i = column1 + 1; i < column2; i++) {
                    arrayList.add(board[row1][i]);
                }
            }
            //If it is diagonal;
        }  else {
            int newRow = row1 - 1;//left upper
            int newCol = column1 - 1;//left upper

            while (newRow >= 0 && newCol >= 0) {

                if(board[newRow][newCol].getRow().equals(targetLocation.getRow()) && board[newRow][newCol].getColumn().equals(targetLocation.getColumn())) {

                    return arrayList.toArray(Square[]::new);
                }
                arrayList.add(board[newRow][newCol]);
                newRow--;
                newCol--;
            }
            arrayList = new ArrayList<>();
            newRow = row1 + 1;//left lower
            newCol = column1 -1;//left lower
            while (newRow < 8 && newCol >= 0) {

                if(board[newRow][newCol].getRow().equals(targetLocation.getRow()) && board[newRow][newCol].getColumn().equals(targetLocation.getColumn())) {

                    return arrayList.toArray(Square[]::new);
                }
                arrayList.add(board[newRow][newCol]);
                newCol--;
                newRow++;
            }
            arrayList = new ArrayList<>();

            newRow = row1 + 1;//right lower
            newCol = column1 + 1;//right lower
            while (newRow < 8 && newCol < 8) {

                if(board[newRow][newCol].getRow().equals(targetLocation.getRow()) && board[newRow][newCol].getColumn().equals(targetLocation.getColumn())) {

                    return arrayList.toArray(Square[]::new);
                }
                arrayList.add(board[newRow][newCol]);
                newCol++;
                newRow++;
            }
            arrayList = new ArrayList<>();

            newRow = row1 - 1;//right upper
            newCol = column1 + 1;//right upper
            while (newRow >= 0 && newCol < 8) {

                if(board[newRow][newCol].getRow().equals(targetLocation.getRow()) && board[newRow][newCol].getColumn().equals(targetLocation.getColumn())) {

                    return arrayList.toArray(Square[]::new);
                }
                arrayList.add(board[newRow][newCol]);
                newCol++;
                newRow--;
            }
            arrayList = new ArrayList<>();
        }

        Square[] squares = new Square[arrayList.size()];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = arrayList.get(i);
        }

        return squares;

    }


    public static void nextPlayer() { //It passes the player
        if (turn == 0){
            turn = 1;
        }else if (turn==1){
            turn = 0;
        }

    }


    public boolean isGameEnded() { // it checks if there is any pieces left on the board by checking all squares.
        int White = 0;
        int Black = 0;
        for(int i = 0 ; i<8;i++){
            for(int j = 0 ; j<8 ; j++){

                if(!board[i][j].isEmpty() && board[i][j].getPiece().getColor()==0){
                    White++;

                }else if (!board[i][j].isEmpty()&&board[i][j].getPiece().getColor()==1){
                    Black++;
                }

            }
        }

        return White == 0 || Black == 0; // it returns True when White or Black has no pieces on board.

    }

    public Piece getPieceAt(String from) {
        int[] pieceLocation = SquareLocation(from);
        int pieceRow = pieceLocation[0];
        int pieceColumn = pieceLocation[1];
        System.out.println( board[pieceRow][pieceColumn].getPiece() == null ? "empty":board[pieceRow][pieceColumn].getPiece().toString());
        return board[pieceRow][pieceColumn].getPiece();
    }
}
