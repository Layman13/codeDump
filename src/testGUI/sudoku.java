package testGUI;
import java.lang.Math;   

public class sudoku {
	String name;
	String difficulty;
	int givenNumbers;
	int[][] board = new int[9][9];
	int[][] unsolved = new int[9][9];
	
	sudoku(String name){
		this.name = name;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	void difficulty(String input) {
		String dif = input;
		this.difficulty = input;
		String ez1 = "easy";
		String ez2 = "medium";
		String ez3 = "hard";
		String ez4 = "insane";
		
		if(dif.equals(ez1)) {
			this.givenNumbers = 34;
		}else if(dif.equals(ez2)) {
			this.givenNumbers = 30;
		}else if(dif.equals(ez3)) {
			this.givenNumbers = 27;
		}else if(dif.equals(ez4)) {
			this.givenNumbers = 22;
		}else {
			System.out.println("Error, invalid difficulty entered, null taken");
		}
	}
	
	void reset(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				this.unsolved[i][j] = 0;
				this.board[i][j] = 0;
			}
		}
	}
	
	void printBoard(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			System.out.print(" ");
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 0) {
					System.out.print(" ");
				}else {
					System.out.print(board[i][j]);
				}
				System.out.print(" ");
				if(j == 2 || j == 5) {
					System.out.print("|");
				}
			}
			System.out.print(" ");
			System.out.println();
			if(i == 2 || i == 5) {
				System.out.print("____________________");
				System.out.println();
			}
		}
	}
	
	boolean isSafe(int[][] grid, int row, int col, int num) {
		if(row >= 10 || col >= 10 || num >= 10) {
			return false;
		}
		
		for(int x = 0; x <= 8; x++) {
			if(grid[row][x] == num) {
				return false;
			}
		}
		
		for(int x = 0; x <= 8; x++) {
			if(grid[x][col] == num) {
				return false;
			}
		}
		
		int startRow = row - row % 3;
		int startCol = col - col % 3;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(grid[i + startRow][j + startCol] == num) {
					return false;
				}
			}
		}
		return true;
	}
	
	boolean algorithm(int[][] grid, int x, int y) {
		if(x == 9 - 1 && y == 9 ) {
			return true;
		}
		
		if(y == 9) {
			x++;
			y = 0;
		}
		
		if(grid[x][y] > 0) {
			return algorithm(grid, x, y + 1);
		}
		
		for(int num = 1; num <= 9; num++) {
			if(isSafe(grid, x, y, num)) {
				grid[x][y] = num;
				
				if(algorithm(grid, x, y + 1)) {
					return true;
				}
			}
			grid[x][y] = 0;
		}
		return false;
	}
	
	void createGrid() {
		int posx = (int)(Math.random() * (8 - 0 + 1) + 0);
		int count = 0;
		int posy = (int)(Math.random() * (8 - 0 + 1) + 0);
		int val;
		int i = 0;
		while(i < givenNumbers) {
			if(this.board[posx][posy] != 0 || count >= 9) {
				posx = (int)(Math.random() * (8 - 0 + 1) + 0);
				posy = (int)(Math.random() * (8 - 0 + 1) + 0);
				count = 0;
			}
			val = (int)(Math.random() * (9 - 0 + 1) + 1);
			if(isSafe(this.board, posx, posy, val) && this.board[posx][posy] == 0) {
				this.board[posx][posy] = val;
				i++;
			}else {
				count++;
			}
			System.out.println("Does this even work");
		}
	//	this.unsolved = this.board.clone();
		for(int p = 0; p < 9; p++) {
			for(int l = 0; l < 9; l++) {
				this.unsolved[p][l] = this.board[p][l];
			}
		}
	}
	
	void generate(int num) {
		this.givenNumbers = num;
		boolean solvedd = false;
		do {
			reset();
			createGrid();
			printBoard(board);
			solvedd = algorithm(board, 0, 0);
			
		}while(solvedd == false);
	}
	
	
}
