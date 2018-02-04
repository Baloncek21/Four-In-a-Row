//FOUR IN A ROW [java]
asd
import java.util.*;
import java.awt.*;
import javax.swing.*;

class Main {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final double W = 800;
    public static final double H = 800;
    private static int player = 1;  //spremeni se v metodi drop
    private static int[][] board = new int[ROWS+2][COLUMNS+2];
    public static boolean over = false;
    
    // main
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        board = makeBoard(board);
        //System.out.println("It is Player 1's turn.");
        int cnt = 0;
        boolean continues = true;


        JFrame f = new JFrame("Four In a Row");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawPanel dp = new DrawPanel();
        f.add(dp);
        f.setSize((int)W,(int)H);
        f.setVisible(true);

        while(cnt < ROWS*COLUMNS && continues) {  // && gameNotOver(board)
            int column = sc.nextInt();  // izbrani stolpec
            boolean dropHappens = false;
            while (!dropHappens) {
                // Preveri, ce je stolpec veljaven - ce je, se izvede met
                if(canDrop(board, column)) {
                    //drop(board, column);
                    dropHappens = true;
                    // Prikaz tabele
                    /*
                    for (int i = 1; i < board.length-1; i++) {
                        for (int j = 1; j < board[i].length-1; j++) {
                            if(board[i][j] == -1) System.out.print("   ");
                            else {
                                System.out.printf("%2d ",board[i][j]);
                            } 
                        }
                        System.out.printf("%n");
                    }
                    System.out.printf("%n");
                    for (int j = 1; j < board[0].length-1; j++) {
                        System.out.printf("%2d ",j);
                    }
                    System.out.printf("%nIt is Player %d's turn.%n",player);
                    */
                }
                else {
                    // Prikaz tabele
                    /*
                    for (int i = 1; i < board.length-1; i++) {
                        for (int j = 1; j < board[i].length-1; j++) {
                            if(board[i][j] == -1) System.out.print("   ");
                            else System.out.printf("%2d ",board[i][j]);
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
                    */
                    column = sc.nextInt();
                }
                DrawPanel dp1 = new DrawPanel();
                f.add(dp1);
                f.setSize((int)W,(int)H);
                f.setVisible(true);
            }
            cnt++;    
            continues = gameNotOver(board);  
        }    
        over = true;
        player = (player == 1) ? 2 : 1;	
        System.out.println();
        System.out.println("THE WINNER IS PLAYER "+player+"!");   
        DrawPanel dp2 = new DrawPanel();
        f.add(dp2);
        f.setSize((int)W,(int)H);
        f.setVisible(true);
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
                int diagonalUp = 1;
                int diagonalDown = 1;
                int r=i;
                int c=j;
                if(board[r][c] != -1 ) {
                    // vertical
                    while(board[r][c] == board[r][c+1]) {
                        vertical++;
                        c++;
                    }
                    r=i;
                    c=j;
                    while(board[r][c] == board[r][c-1]) {
                        vertical++;
                        c--;
                    }
                    r=i;
                    c=j;
                    //horizontal
                    while(board[r][c] == board[r+1][c]) {
                        horizontal++;
                        r++;
                    }
                    r=i;
                    c=j;
                    while(board[r][c] == board[r-1][c]) {
                        horizontal++;
                        r--;
                    }
                    r=i;
                    c=j;
                    //diagonal
                    while(board[r][c] == board[r+1][c+1]) {
                        diagonalDown++;
                        r++;
                        c++;
                    }
                    r=i;
                    c=j;
                    while(board[r][c] == board[r-1][c-1]) {                
                        diagonalDown++;
                        r--;
                        c--;
                    }
                    while(board[r][c] == board[r+1][c-1]) {                  
                        diagonalUp++;
                        r++;
                        c--;
                    }
                    r=i;
                    c=j;
                    while(board[r][c] == board[r-1][c+1]) {                   
                        diagonalUp++;
                        r--;
                        c++;
                    }
                    r=i;
                    c=j;
                    if(horizontal>=4 || vertical>=4 ||diagonalUp>=4 || diagonalDown>=4 ) return false;
                }
            }
        }
        return notOver;
    }
//grafika
    public static int[][] getBoard() {
        return board;
    }
    public static int getPlayer() {
        return player;
    }
    private static class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int[][] board = getBoard();
            int player = getPlayer();

            Color myBrown = new Color(190, 150, 70);            
            this.setBackground(myBrown);
            g.setColor(Color.BLUE);
            double xBoard = 0.05*W;
            double yBoard = 0.15*H;
            double squareW = 0.89*W/COLUMNS;
            double squareH = 0.8*H/ROWS;

            //addMouseListener(this);
            //int column = 1;
            //drop(board, column);

            g.fillRect((int)xBoard, (int)yBoard, (int)(0.89*W), (int)(0.8*H));
            // napis
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50)); 
            if(over) {
                String over = String.format("THE WINNER IS PLAYER %d!", player);
                g.drawString(over,(int)xBoard,(int)(0.08*H));
            }
            else {
                 String turn = String.format("It is Player %d's turn.", player);
                 g.drawString(turn,(int)xBoard,(int)(0.08*H));
            }
            // narise zetone  
            for (int i = 1; i < board.length-1; i++) {
                for (int j = 1; j < board[i].length-1; j++) {
                    double x = xBoard + (j-1)*squareW+ 0.1*squareW;
                    double y = yBoard + (i-1)*squareH+ 0.1*squareH;
                    if(board[i][j] == 1) {
                        g.setColor(Color.RED);
                        g.fillOval((int)x, (int)y, (int)(0.8*squareW), (int)(0.8*squareH));
                    } else if(board[i][j] == 2) {
                        g.setColor(Color.YELLOW);
                        g.fillOval((int)x, (int)y, (int)(0.8*squareW), (int)(0.8*squareH));
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillOval((int)x, (int)y, (int)(0.8*squareW), (int)(0.8*squareH));
                    }
                }
            }
            // narise stevilke stolpcev
            g.setColor(Color.BLUE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 30));
            for (int i = 1; i < board[0].length; i++) {
                double x = xBoard + (i-1)*squareW+ 0.4*squareW;
                double y = yBoard - 0.1*squareH;
                g.drawString(String.valueOf(i),(int)x, (int)y);
            }

        }
    }
}

    
