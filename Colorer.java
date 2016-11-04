package source;

import java.util.ArrayList;
import java.util.LinkedHashSet;


public class Colorer {

	private Graph graph;
	private int vertexSize;
	private int[] colors;

	private int number_of_colored_vertexes;

	public Colorer(){

	}

	public void setGraph(Graph p_graph){

		graph = p_graph;
		this.vertexSize = graph.getVertexSize();
		this.colors = graph.getColors();

		number_of_colored_vertexes = 0;

		// find number of colored vertices
		for(int i = 0; i < vertexSize; i++){

			if(colors[i] != 0){
				number_of_colored_vertexes++;
			}
		}
	}


	// DSATUR algorithm is applied for coloring vertices
	// Reference: "Greedy Algorithm: Degree of Saturation(DSATUR)" by
	// Tsai-Chen Du December 11, 2003
	// Reference Link: "http://cs.indstate.edu/tdu/intro.pdf"
	// Reference: "DSATUR" by Tsai-Chen Du
	// Reference Link: "http://cs.indstate.edu/tdu/mine1.pdf"
	public boolean colorVertices(){

		// true if all of the vertices are colored
		boolean all_vertices_colored = true;

		if(graph != null){

			while(number_of_colored_vertexes != vertexSize){

				// find next vertex to be colored
				int nextVertex = indexOfVertexToBeColoredNext();

				// find the color that will be assigned to this vertex
				int colorOfTheVertex = indexOfLowestColorUnusedByNeighbors(nextVertex);

				// if no such color exist, break
				if(colorOfTheVertex == 0){

					all_vertices_colored = false;
					break;
				}

				// color the vertex
				colors[nextVertex] = colorOfTheVertex;

				number_of_colored_vertexes++;
			}

		}else{
			
			all_vertices_colored = false;
		}


		return all_vertices_colored;

	}

	// this method gives the index of vertex which is adjacent to the largest
	// number of distinctly colored vertices
	private int indexOfVertexToBeColoredNext(){

		// holds largest number of colored neighbors
		int largestNumberColoredNeighbors = 0;
		// holds index of vertex whose number of distinct colored neighbors is the largest
		int indexLargestNumberColoredNeighbors = 0;

		for(int i = 0; i < vertexSize; i++){

			// if current vertex has no color
			if(colors[i] == 0){

				// linked hash set holds distinct values
				LinkedHashSet<Integer> current_vertex_colors = new LinkedHashSet<Integer>();
				// get neighbors of the current vertex
				ArrayList<Integer> neighbors = graph.getAdjacentVertices(i);

				for(int j = 0; j < neighbors.size(); j++){

					current_vertex_colors.add(colors[neighbors.get(j)]);
				}

				// if the current vertex has the most distinctly colored adjacent vertices
				if(current_vertex_colors.size() > largestNumberColoredNeighbors){

					largestNumberColoredNeighbors = current_vertex_colors.size();
					indexLargestNumberColoredNeighbors = i;
				}

			}
		}

		return indexLargestNumberColoredNeighbors;
	}

	// this method gives the lowest index color, till index 9, which is unused by the
	// neighbors of given vertex. If no such color exist, return 0 
	private int indexOfLowestColorUnusedByNeighbors(int p_vertex){

		int lowestColorIndex = 0;

		LinkedHashSet<Integer> current_vertex_colors = new LinkedHashSet<Integer>();
		ArrayList<Integer> neighbors = graph.getAdjacentVertices(p_vertex);

		for(int i = 0; i < neighbors.size(); i++){

			current_vertex_colors.add(colors[neighbors.get(i)]);
		}

		for(int i = 1; i <= 9; i++){

			if(!current_vertex_colors.contains(i)){

				lowestColorIndex = i;
				break;
			}
		}

		return lowestColorIndex;


	}



}
