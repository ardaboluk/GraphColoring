package source;

import java.util.ArrayList;

public class Graph {

	private int vertexSize;
	private int[][] adjacencyMatrix;
	private int[] colors;
	
	public Graph(){
		
	}
	
	public void setAdjacencyMatrix(int[][] p_adjacencyMatrix){
		
		adjacencyMatrix = p_adjacencyMatrix;
		vertexSize = adjacencyMatrix.length;
	}
	
	public void setColors(int[] p_colors){
		
		if(p_colors.length == vertexSize){
			
			colors = p_colors;
		}		
	}

	public int[][] getAdjacencyMatrix(){

		return adjacencyMatrix;
	}

	public int[] getColors(){

		return colors;
	}
	
	public int getVertexSize(){

		return vertexSize;
	}

	// this method gives indexes of adjacent vertices of the given vertex
	public ArrayList<Integer> getAdjacentVertices(int indexof_currentVertex){

		ArrayList<Integer> adjacentVertices = new ArrayList<Integer>();

		// get indexes from adjacency matrix
		for(int i = 0; i < vertexSize; i++){

			if(adjacencyMatrix[indexof_currentVertex][i] == 1){

				adjacentVertices.add(i);
			}
		}

		return adjacentVertices;
	}


}
