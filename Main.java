//FOUR IN A ROW [java]

import java.util.*;
import java.awt.*;

class Main {
    public static final int ROWS = 6;
	public static final int COLUMNS = 7;
    private static int player = 1;  //spremeni se v metodi drop
    
    // main
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board = new int[ROWS+2][COLUMNS+2];
        board = makeBoard(board);
        System.out.println("It is Player 1's turn.");
        int cnt = 0;
        boolean continues = true;
        while(cnt < ROWS*COLUMNS && continues) {  // && gameNotOver(board)
            int column = sc.nextInt();  // izbrani stolpec
            boolean dropHappens = false;

            while (!dropHappens) {
                // Preveri, ce je stolpec veljaven - ce je, se izvede met
                if(canDrop(board, column)) {
                    drop(board, column);
                    dropHappens = true;
                    // Prikaz tabele
                    for (int i = 1; i < board.length-1; i++) {
                        for (int j = 1; j < board[i].length-1; j++) {
                            System.out.printf("%2d ",board[i][j]);
                        }
                        System.out.printf("%n");
                    }
                    System.out.printf("%n");
                    for (int j = 1; j < board[0].length-1; j++) {
                        System.out.printf("%2d ",j);
                    }
                    System.out.printf("%nIt is Player %d's turn.%n",player);
                }
                else {
                    // Prikaz tabele
                    for (int i = 1; i < board.length-1; i++) {
                        for (int j = 1; j < board[i].length-1; j++) {
                            System.out.printf("%2d ",board[i][j]);
                        }
                        System.out.printf("%n");
                    }
                    System.out.printf("%n");
                    for (int j = 1; j < board[0].length-1; j++) {
                        System.out.printf("%2d ",j);
                    }
                    System.out.printf("%n%n");
                    System.out.println("Invalid field, please try again.");
                    System.out.println();
                    System.out.printf("It is Player %d's turn.%n",player);
                    column = sc.nextInt();
                }
            }
            cnt++;    
            continues = gameNotOver(board);  
        }    
        player = (player == 1) ? 2 : 1;	
        System.out.println();
        System.out.println("THE WINNER IS PLAYER "+player+"!");   
    }


    //igra
    
	
	public static int[][] makeBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(i == 0 || j == 0 || i == board.length-1 || j == board[i].length -1) {
                    board[i][j] = -5;
                }
                else {
                    board[i][j] = -1;
                }
			}
        }
        return board;
    }

    public static boolean canDrop(int board[][], int column) {
        boolean can = false;
        if (column > 0 && column < board[0].length-1) { 
            if (board[1][column] == -1) {
                can = true;
            } 
        }
        return can;
    }
    
	public static int[][] drop(int[][] board, int column){
				
		for(int i = 1; i < board.length ; i++) {
			if(board[i+1][column] != -1) {
				board[i][column] = player;
				break;
			}
		}
		player = (player == 1) ? 2 : 1;				
		return board;
	}
    
    public static boolean gameNotOver(int[][] board) {
        boolean notOver = true;
        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board[i].length-1; j++) {
                int vertical = 1;
                int horizontal = 1;
                int diagonal = 1;
                int r=i;
                int c=j;
                if(board[r][c] != -1 ) {
                    // vertical
                    while(board[r][c] == board[r][c+1]) {
                        //if(board[r][c] != board[r][c+1]) break;
                        vertical++;
                        c++;
                    }
                    r=i;
                    c=j;
                    while(board[r][c] == board[r][c-1]) {
                        //if(board[r][c] != board[r][c-1]) break;
                        vertical++;
                        c--;
                    }
                    r=i;
                    c=j;
                    //horizontal
                    while(board[r][c] == board[r+1][c]) {
                        //if(board[r][c] != board[r+1][c]) break;
                        horizontal++;
                        r++;
                    }
                    r=i;
                    c=j;
                    while(board[r][c] == board[r-1][c]) {
                        //if(board[r][c] != board[r-1][c]) break;
                        horizontal++;
                        r--;
                    }
                    r=i;
                    c=j;
                    //diagonal
                    while(board[r][c] == board[r+1][c+1]) {
                        //if(board[r][c] != board[r+1][c+1]) break;
                        diagonal++;
                        r++;
                        c++;
                    }
                    r=i;
                    c=j;
                    while(board[r][c] == board[r-1][c-1]) {
                        //if(board[r][c] != board[r-1][c-1]) break;                    
                        diagonal++;
                        r--;
                        c--;
                    }
                    //System.out.println(vertical+" "+horizontal+" "+ diagonal);
                    if(horizontal>=4 || vertical>=4 ||diagonal>=4 ) return false;
                }
            }
        }
        return notOver;
    }

    
    //grafika
}

