package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Sudoku {
	
	private String input_filename;
	private Graph sudoku_graph;
	private Colorer colorer;
	
	public Sudoku(){
		
		input_filename = "";
		sudoku_graph = new Graph();
		colorer = new Colorer();
	}
	
	public void setFileName(String p_input_filename){
		
		input_filename = p_input_filename;
	}
	
	public boolean solveSudoku(){
		
		if(input_filename == ""){
			
			System.out.println("Please enter the name of the file");
			System.out.println("containing the name of the file");
			return false;
		}
		
		// read colors from file
		int[] colors;
		if((colors = readColorVector()) == null){
			System.out.println("Color vector cannot be read");
			return false;
		}
		
		// construct graph representing sudoku board
		int[][] adjacencyMatrix = constructSudokuMatrix();
		
		// configure objects
		sudoku_graph.setAdjacencyMatrix(adjacencyMatrix);
		sudoku_graph.setColors(colors);
		colorer.setGraph(sudoku_graph);
		
		// solve sudoku by vertex coloring
		if(colorer.colorVertices() == false){
			
			System.out.println("Graph couldn't be vertex-colored");
			return false;
		}
		
		// write final coloring to file
		writeColorVector("solved_sudoku.txt");
		
		return true;
	}
	
	// this method construct adjacency matrix of sudoku graph
	// sudoku board is indexed in row-major order
	private int[][] constructSudokuMatrix(){
		
		int[][] adjacencyMatrix = new int[81][81];
		
		for(int i = 0; i < 81; i++){
			for(int j = 0; j < 81; j++){
				
				if(checkSameRow(i,j) || checkSameCol(i,j) || checkSameSquare(i,j)){
					
					adjacencyMatrix[i][j] = 1;
				}
			}
		}
		
		return adjacencyMatrix;
	}
	
	// this method checks if given two indexes are on the same row
	// on the sudoku board
	private boolean checkSameRow(int index1, int index2){
		
		boolean sameRow = false;
		
		if(index1 / 9 == index2 / 9){
			sameRow = true;
		}
		
		return sameRow;
	}
	
	// this method checks if given two indexes are on the same column
	private boolean checkSameCol(int index1, int index2){
		
		boolean sameCol = false;
		
		if(index1 % 9 == index2 % 9){
			
			sameCol = true;
		}
		
		return sameCol;
	}
	
	// this method checks if given two indexes are on the same 3x3
	// square
	private boolean checkSameSquare(int index1, int index2){
		
		boolean sameSquare = false;
		
		// they are in the same square if they are in the same square row
		// and same square column
		if(((index1 / 9) / 3) == ((index2 / 9) / 3) && ((index1 % 9 ) / 3) == ((index2 % 9) / 3)){
			
			sameSquare = true;
		}
		
		return sameSquare;
	}
	
	// this method reads from file and constructs color vector
	private int[] readColorVector(){
		
		int[] colors = new int[81];
		
		try{
			
			String colors_string = "";
			String current_line = "";
			
			File input_file = new File(input_filename);
			FileReader fileReader = new FileReader(input_file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((current_line = bufferedReader.readLine()) != null){
				
				colors_string += current_line;
			}
			
			fileReader.close();
			bufferedReader.close();
			
			colors_string = clearString(colors_string);
			
			if(colors_string.length() != 81){
				
				System.out.println("Format of the input file is not correct");
				return null;
			}
			
			for(int i = 0; i < 81; i++){
				
				char current_char = colors_string.charAt(i);
				
				if(current_char == '.'){
					
					colors[i] = 0;
				}else if(current_char >= 49 && current_char <= 57){
					
					colors[i] = current_char - 48;
				}else{
					
					System.out.println("Format of the input file is not correct");
					return null;
				}
			}
			
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
			return null;
		}
		
		return colors;
	}
	
	// this method removes all characters that is not '.' or a digit
	// from given string
	private String clearString(String p_givenString){
		
		String newString = "";
		
		for(int i = 0; i < p_givenString.length(); i++){
			
			if(p_givenString.charAt(i) == '.'){
				
				newString += '.';
			}else if(p_givenString.charAt(i) >= 49 && p_givenString.charAt(i) <= 57){
				
				newString += p_givenString.charAt(i);
			}
		}
		
		return newString;
	}
	
	// this method writes color vector to given file
	private void writeColorVector(String filename_to_write){
		
		try{
			
			int[] colors = sudoku_graph.getColors();
			
			FileWriter file_writer = new FileWriter(filename_to_write);
			PrintWriter print_writer = new PrintWriter(file_writer);
			
			for(int i = 0; i < 81; i++){
				
				print_writer.print(colors[i] + " ");
				
				if(i % 9 == 8){
					
					print_writer.println("");
				}
			}
			
			print_writer.close();
			
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
		}
	}

}
