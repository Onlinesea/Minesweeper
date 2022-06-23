package myApp;

import java.util.Random;

/*This code will generate a random 2d array that contains the the mines
* generate 6 bombs
*/
public class Mines {
    String[][] mineLocation = new String[10][10];
    
    int[][] bombNumber = new int[10][10];
    
    int[][]visibleField = new int[10][10];

    boolean gameover = false;

    boolean win = false;

    public void start(){ 
        Random rand = new Random();
        int upperbound = 10;
        //Random generate 5 coordinates for bomb. if the coordinate already have a bomb, generate another one 
        for(int i = 0; i< 10; i++){
            for( int j=0; j< 10; j++){
                this.mineLocation[i][j] = " ";
                this.bombNumber[i][j] = 0;
                this.visibleField[i][j]= 50;  // 50 means it is not visble 
            }
        }

        for(int i = 0; i<6; i++){
            int row = rand.nextInt(upperbound);
            int column = rand.nextInt(upperbound);
            System.out.println("row and column : " + row + column);
            if(!this.mineLocation[row][column].equals("bomb")){
                this.mineLocation[row][column] = "bomb";
                // all the surrounding bombNumber increase by one
                //for the edge case of first row, extreme left and right
                if(row == 0){
                    for(int r=0; r < (row+2); r++){
                        if(column == 0){
                            for (int c = (column); c <(column+2);c++){
                                this.bombNumber[r][c]+=1;
                            }
                        }else if(column==9){
                            for (int c = (column-1); c <(column+1);c++){
                                this.bombNumber[r][c]+=1;
                            }
                        }else{                                                      //normal middle part
                            for (int c = (column-1); c <(column+2);c++){
                                this.bombNumber[r][c]+=1;
                            }
                        }
                    }

                }else if(row == 9){
                    for(int r=(row-1); r < (row+1); r++){
                        if(column == 0){
                            for (int c = (column); c <(column+2);c++){
                                this.bombNumber[r][c]+=1;
                            }
                        }else if(column==9){
                            for (int c = (column-1); c <(column+1);c++){
                                this.bombNumber[r][c]+=1;
                            }
                        }else{                                                      //normal middle part
                            for (int c = (column-1); c <(column+2);c++){
                                this.bombNumber[r][c]+=1;
                            }
                        }
                    }
                }else if (row != 0 && row != 9 && column != 0 && column != 9){
                    for(int r= (row-1); r < (row+2); r++){
                        for (int c = (column-1); c <(column+2);c++){            //if non edge case then all 9 square will plus one
                            this.bombNumber[r][c]+=1;
                        }
                    }
                }

            }else{                                                          //else if there is a bomb, regenerate and the count not counted
                i--;
            }
        }

    }

    public int checkBomb( int row, int column){
        return (this.bombNumber[row][column]);
    }

    public void dig(int row , int column ){
        if(this.mineLocation[row][column].equals("bomb")){
            System.out.println("GAME OVER");
            this.gameover = true;
        }else if(this.bombNumber[row][column]==0){
            checkZero(row,column);
        }else{
        this.visibleField[row][column]= this.bombNumber[row][column];
        }   
    }

    public void checkZero(int row, int column){
        // If the area is not reveal, reveal it
        if(this.visibleField[row][column] == 50){
            this.visibleField[row][column]=this.bombNumber[row][column];
            // If the area reveal is 0, contiune to checkZero for the surrounding areas
            if (this.bombNumber[row][column]==0 ){
            if(row == 9 && column == 9 ){
                checkZero(row-1,column-1);
                checkZero(row-1,column);
                checkZero(row,column-1);

            }else if(row ==0 && column ==0 ){
                checkZero(row+1,column+1);
                checkZero(row,column+1);
                checkZero(row+1,column);

            }else if( row == 0 && column == 9){
                checkZero(row,column-1);
                checkZero(row+1,column-1);
                checkZero(row+1,column);

            }else if( row == 9 && column == 0){
                checkZero(row,column+1);
                checkZero(row-1,column+1);
                checkZero(row-1,column);

            }else if( row == 0){
                checkZero(row,column-1);
                checkZero(row+1,column-1);
                checkZero(row+1,column);
                checkZero(row+1,column+1);
                checkZero(row,column+1);

            }else if(row == 9) {
                checkZero(row,column-1);
                checkZero(row-1,column-1);
                checkZero(row-1,column);
                checkZero(row-1,column+1);
                checkZero(row,column+1);

            }else if(column == 0){
                checkZero(row,column+1);
                checkZero(row-1,column+1);
                checkZero(row-1,column);
                checkZero(row+1,column);
                checkZero(row+1,column+1);

            }else if(column == 9){
                checkZero(row,column-1);
                checkZero(row-1,column-1);
                checkZero(row-1,column);
                checkZero(row+1,column);
                checkZero(row+1,column-1);
            }else{
                checkZero(row,column-1);
                checkZero(row-1,column-1);
                checkZero(row-1,column);
                checkZero(row+1,column);
                checkZero(row+1,column-1);
                checkZero(row+1,column+1);
                checkZero(row,column+1);
                checkZero(row-1,column+1); 
            }
        }
    }
        


/* 
        if(row == 0){
            for(int r=0; r < (row+2); r++){
                if(column == 0){
                    for (int c = (column); c <(column+2);c++){
                        if(this.bombNumber[r][c]==0){
                            this.visibleField[r][c]=this.bombNumber[r][c];
                            //checkZero(r,c);
                        }
                    }
                }else if(column==9){
                    for (int c = (column-1); c <(column+1);c++){
                        this.visibleField[r][c]=this.bombNumber[r][c];
                        //checkZero(r,c);      
                    }
                }else{                                                      //normal middle part
                    for (int c = (column-1); c <(column+2);c++){
                        this.visibleField[r][c]=this.bombNumber[r][c];
                        //checkZero(r,c);
                    }
                }
            }

        }else if(row == 9){
            for(int r=(row-1); r < (row+1); r++){
                if(column == 0){
                    for (int c = (column); c <(column+2);c++){
                        this.visibleField[r][c]=this.bombNumber[r][c];
                        //checkZero(r,c);
                    }
                }else if(column==9){
                    for (int c = (column-1); c <(column+1);c++){
                        this.visibleField[r][c]=this.bombNumber[r][c];
                        //checkZero(r,c);
                    }
                }else{                                                      //normal middel part
                    for (int c = (column-1); c <(column+2);c++){
                        this.visibleField[r][c]=this.bombNumber[r][c];
                        //checkZero(r,c);
                    }
                }
            }
        }else if (row != 0 && row != 9 && column != 0 && column != 9){
            for(int r= (row-1); r < (row+2); r++){
                for (int c = (column-1); c <(column+2);c++){            //if non edge case then all 9 square will plus one
                    this.visibleField[r][c]=this.bombNumber[r][c];
                    if(row >= 0 || row < 10 || column >=0 || column<10){
                        checkZero(r,c);
                    }
                }
                
            }
        }
*/
    }
}
    

