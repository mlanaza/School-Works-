/*************************************************
File: ALcsc210hw6
By: Anne Lanaza
Date: 12/20/17
Compile: [how to compile this program, e.g., gcc p1.c –o p1 –lm]
Usage: [how to run this program] e.g. if arguments are needed:
java -jar myprogram.jar input.txt
Description: Conway's Game of Life 
	Conway’s game of life is a simplistic way to simulate a living system. It most resembles a colony of cells
on a flat surface, and the cells are able to live, die, or multiply as time passes. The fate of each cell
depends on a set of rules, and new cells will be created according to the same rules. The rules are as
follows:
1. If a cell is surrounded by 0 or 1 other cells, then the cell dies. The cell is isolated and does not
survive.
2. If a cell is surrounded by 4 other cells, then the cell dies. The cell is too crowded, and dies as a
result of overpopulation.
3. If a cell is surrounded by 2 or 3 other cells, then the cell lives. These are ideal conditions.
4. If a space not occupied by a cell is surrounded by exactly 3 cells, then the space becomes populated
with a cell. There are enough nearby cells, and enough space, for reproduction.
*************************************************/



import java.util.Random;

public class ALcsc210hw6 {
	
	
	
	
	//prints the grid 
	static void PrintGrid(int[][] grid, int M, int N) {
		
		for(int i = 1; i < M-1; i++) {
			for(int j = 1; j < N-1; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	
	//updates grid by checking for cells neighbors and storing it into array B
	static int[][] UpdateGrid(int[][] A, int[][] B, int M, int N){
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				
				//sets edges to 1
				if((i == 0 || i == M-1) || (j == 0 || j == N-1)) {
					B[i][j] = 1;
				} else {
					//checks for neighbors
					int numNeighbors = 0;
					
					//top
					if(A[i-1][j] == 1) {
						numNeighbors++;
					}
					
					//bottom
					if(A[i+1][j] == 1) {
						numNeighbors++;
					}
					
					//left
					if(A[i][j-1] == 1) {
						numNeighbors++;
					}
					
					//right
					if(A[i][j+1] == 1) {
						numNeighbors++;
					}
					
					//for empty spaces with 3 neighbors
					if((A[i][j] == 0) && (numNeighbors == 3)) {						
							B[i][j] = 1;
						}
					
					if(A[i][j] == 1) {
						//if cell is surrounded by 0 or 1 or 4 then cell dies
						if((numNeighbors <= 1) || (numNeighbors == 4)) {
							B[i][j] = 0;
						//if 2 or 3, cell lives
						} else {
							B[i][j] = 1;
							}
						}			
					}
				}
				
			}
		return B;
		}
	
	
	
	
	//copies the values from array B to array A
	static void CopyGrid(int[][] A, int[][] B, int M, int N) {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				//if value of A is not equals to B, sets the A to B
				if(A[i][j] != B[i][j]) {
					A[i][j] = B[i][j];
				}
			}
		}
	}
	
	
	
	
	//Fills grid randomly either 0 or 1
	static void FillGrid(int[][] A, int M, int N) {
		Random rand = new Random();
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				
				//sets the edges of grid to a default 1
				if((i == 0 || i == M-1) || (j == 0 || j == N-1)) {
					A[i][j] = 1;
				} else {
					A[i][j] = rand.nextInt(2);
				}			
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		int M = 5; 		//size of grid, row
		int N = 5; 		//size of grid, column
		int T = 2; 		//the times a grid is updated
		int[][] A = new int[M][N];
		int[][] B = new int[M][N];
		
		FillGrid(A, M, N);
		System.out.println("NEW:");
		PrintGrid(A, M, N);	
		
		//loop runs for T times or until its zero
		while(T != 0) {
			
			B = UpdateGrid(A, B, M, N);
			CopyGrid(A, B, M, N);
			System.out.println("UPDATE:");
			PrintGrid(A, M, N);
			T--;
		}
	}
}


/*----------------paste of run from console window---------
Note: The grid is set to 5x5 but since edges are set to 1, it
will only show a 3x3 grid. Also the program is set to run twice:

NEW:
010
000
001

UPDATE:
101
000
001

UPDATE:
111
001
001
 
 
-----------------------------------------------------------*/

