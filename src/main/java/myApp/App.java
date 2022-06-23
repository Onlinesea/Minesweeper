package myApp;

import java.io.Console;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   
        Console cons = System.console();
        Mines mines = new Mines();
        mines.start();

        while(!mines.gameover && !mines.win){
            mines.win=printMap(mines);
            String input = cons.readLine("Where would you like to dig? Input as row,col: ");
            String[] inputs = input.split(",",2);
            int row = Integer.parseInt(inputs[0].trim());
            int column = Integer.parseInt(inputs[1].trim());
            mines.dig(row, column);
        }
    }

    public static boolean printMap(Mines mine){
        int counter = 0;
        System.out.print(" ");
        for(int i =0; i<10; i++){
            System.out.printf(" %d", i);
        }

        System.out.println(" ");

        for(int i =0; i<10; i++){
            System.out.print("--");
        }
        System.out.println("--");

        for(int i =0; i<10; i++){
            System.out.print(i);
            for(int j = 0; j< 10 ; j++){
                //print visble field
                System.out.print("|");
                if(mine.visibleField[i][j] == 50){
                System.out.print(" ");
                } else {
                System.out.print(mine.visibleField[i][j]);
                counter++;
                }
            }
            System.out.println(" ");
        }
        for(int i =0; i<10; i++){
            System.out.print("--");
        }
        System.out.println("--");
        if(counter >= (100-6)){
            System.out.println("*************Congrats, you won*************");
            return true;
        }else {
            return false;
        }
    }
}
// didnt manage to print the last set 