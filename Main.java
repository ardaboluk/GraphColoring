package source;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String fileName = "";
		Scanner scanner = new Scanner(System.in);
		
		Sudoku sudoku_solver = new Sudoku();
		
		while(true){
			
			System.out.println("Please enter the name of the file");
			System.out.println("containing sudoku puzzle to be solved");
			System.out.println("Enter 'exit' to exit");
			
			fileName = scanner.nextLine();
			
			if(fileName.equalsIgnoreCase("exit")){
				
				break;
			}
			
			sudoku_solver.setFileName(fileName);
			
			if(sudoku_solver.solveSudoku() == false){
				
				System.out.println("Given sudoku puzzle couldn't be solved");
			}else{
				
				System.out.println("Given sudoku puzzle is solved");
				System.out.println("Solution is saved to solved_sudoku.txt file");
			}
			
		}
		
		scanner.close();
		
	}
	
	

}
