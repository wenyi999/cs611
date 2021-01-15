package com.company;

public class Board {//a game board, contains arrays of tiles, having methods to change and access one tile.
    private Tiles[][] chessList;
    public Board(int width, int length){//not invoked in this program, made for future game which may have board that width not equal to height
        chessList=new Tiles[width][length];
    }
    public Board(int dimension){
        chessList=new Tiles[dimension][dimension];
        for(int i=0;i<dimension;i++){
            for (int j=0;j<dimension;j++){
                chessList[i][j]=new Tiles();
            }
        }
    }
    public Board(){
        chessList=new Tiles[3][3];// default value for tic tac toe
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                chessList[i][j]=new Tiles();
            }
        }
    }
    public Tiles[][]  getBoard(){
        return chessList;
    }
    public void initializeBoard(){//make sure the board is empty
        for (Tiles[] tiles:chessList){
            for (Tiles tile:tiles){
                tile.setChess(" ");
            }
        }
    }
    public void placeChess(int x, int y,String chess){//x y is the coordinate of the chess
        chessList[x][y].setChess(chess);
    }
    public void resize(int nw,int nl){//change the size of the board
        chessList=new Tiles[nw][nl];
    }
    public String toString(){//show the board current situation
        StringBuilder ret= new StringBuilder("This is how the board looks like now.\n");
        int width=chessList.length;
        int length=chessList[0].length;
        for (Tiles[] tiles : chessList) {
            StringBuilder temp= new StringBuilder();//to reduce loops
            for (int j = 0; j < length; j++) {
                ret.append("+--");
                temp.append("| ").append(tiles[j].getChess());
            }
            ret.append("+\n");
            temp.append("|\n");
            ret.append(temp);
        }
        ret.append("+--".repeat(length));
        return ret+"+\n";
    }
}
