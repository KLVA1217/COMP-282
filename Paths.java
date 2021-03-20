//Ken Leonard Aquino, Jeremy Dominguez, Ivan Hernandez - Project 2
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner; 


public class Paths {

	//Creates a Graph Object
	public static AdjListGraph Graph = new AdjListGraph();
	
	public static void main(String[] args) {
		//Reads the Vertex File to get vertices for the graph
		readVertexfile(); 
		//Reads the Edge file and obtains the edges between the vertices for graph
		readEdgefile();
		
		String options="\nOptions:"
			      +"\n\t1. Finding shortest path between two vertices. Input Example: 1 ATL JFK"
			      +"\n\t2. Add Vertex. Input Example: 2 ATL"
			      +"\n\t3. Remove Vertex. Input Example 3 ATL"
			      +"\n\t4. Add Edge. Input Example: 4 ATL JFK 123"
			      +"\n\t5. Remove Edge. Input Example: 5 ATL JFK"
			      +"\n\t6. Print Graph. Input Example: 6"
				  +"\n\nType quit or q to exit program.";
		
		System.out.println("Hello! What would you like me to do?" 
					    +options);
		
		int count=0;
		String userInput="start";
		Scanner scanner = new Scanner(System.in);
		
		while(!userInput.equals("q") || !userInput.equals("quit")) {
			if (count >0) {
				System.out.println("\nWhat else would you like me to do?"
								    +options);
			}
			
			else {
				count++;
			}
			
			userInput=scanner.nextLine();
			userInput.trim();
			
			if (userInput.equals("q") || userInput.equals("q")) {break;}
			
			//Splits the userinput into a string array split by the space, 
			//making it so that the user input can be converted to an element that the computer can use
			String[] splitUserInput = userInput.split(" ");
			
			int optionNum =Integer.parseInt(splitUserInput[0].trim());
			
			switch(optionNum) {
				//Case 1 is Dijikstra's Algorithim but it runs a few checks before attempting
				//	1st is to see if any of the vertices in the user input aren't in the graph
				//  2nd is to check that it's going to cycle
				//If none of these checks are not triggered then it will print out the shortest path according to Dijikstra's algorithim
				case 1:{
					if (Graph.vertexExists(splitUserInput[1].trim()) == false) {
						System.out.println("\nSorry but, Vertex: " + splitUserInput[1].trim() +" doesn't exist.");
						break;
					}
					else if (Graph.vertexExists(splitUserInput[2].trim()) == false) {
						System.out.println("\n Sorry but, Vertex: " + splitUserInput[2].trim() +" doesn't exist.");
						break;
					}
					else if (splitUserInput[1].trim().equals(splitUserInput[2].trim())) {
						System.out.println("\nThat's silly, why would you want to know the quickest path to where you already are?!"
										  +"\nIt's a good thing I checked because other wise I'd be wasting my time!"
										  +"\nYOU'RE ALREADY IN " + splitUserInput[1].trim() + "!!!!!!!!");
						break;
					}
					else { 
						System.out.println("\nShortest path from: " + splitUserInput[1].trim() + " to " + splitUserInput[2].trim());
						System.out.println(Dijikstra( Graph.getVertex(splitUserInput[1].trim()) , Graph.getVertex(splitUserInput[2].trim())));
						Graph.resetGraph();
						break;
					}
				}
				//Adds a vertex but only if the vertex doesn't exist
				case 2 : {
					if (Graph.vertexExists(splitUserInput[1].trim()) == true) {
						System.out.println("Vertex: " + splitUserInput[1].trim() + " exists."
								        +"\nI won't attempt to add a vertex that already exists!");
						break;
					}
					
					else {
						Vertex v = new Vertex(splitUserInput[1].trim());
						Graph.addVertex(v);
						System.out.println("\nAdded Vertex : " + splitUserInput[1].trim() + "to the graph!");
						break;
					}
				}
				//Removes a vertex but won't attempt to remove a vertex that doesn't exist
				case 3 : {
					if (Graph.vertexExists(splitUserInput[1].trim()) == false) {
						System.out.println("Vertex: " + splitUserInput[1].trim() + " doesn't exists"
										+"\nI won't attempt to delete a vertex that doesn't exist!");
						break;
					}
					else {
						Graph.removeVertex(Graph.getVertex(splitUserInput[1].trim()));
						System.out.println("Removed Vertex: " + splitUserInput[1].trim() + "from the graph.");
						break;
					}
				}
				//Adds an edge between vertex1 and vertex 2 if
				//	vertex1 and vertex2 are in the graph
				//	the user has given the weight
				//	there isn't an edge already existing between the two vertices
				case 4 : {
					if (Graph.vertexExists(splitUserInput[1].trim()) == false) {
						System.out.println("\nSorry but, Vertex: " + splitUserInput[1].trim() +" doesn't exist."
								          +"\nPlease add Vertex: "+ splitUserInput[1].trim() + " before adding the edge");
						break;
					}
					else if (Graph.vertexExists(splitUserInput[2].trim()) == false) {
						System.out.println("\n Sorry but, Vertex: " + splitUserInput[2].trim() +" doesn't exist."
								          +"\nPlease add Vertex: "+ splitUserInput[2].trim() + " before adding the edge");
						break;
					}
					else if (splitUserInput.length < 3 ) {
						System.out.println("\nPlease give me the weight of the edge between " + splitUserInput[1].trim() + " and " + splitUserInput[2].trim() + ".");
						break;
					}

					else if(Graph.getVertex(splitUserInput[1].trim()).isAdjacent(Graph.getVertex(splitUserInput[2].trim())) == true) {
						System.out.println("The Edge: " + Graph.getEdge(splitUserInput[1].trim() , splitUserInput[2].trim()) + " already exists!");
						break;
					}
					
					else {
						Edge e = new Edge(Graph.getVertex(splitUserInput[1].trim()) , Graph.getVertex(splitUserInput[2].trim()) , Double.parseDouble(splitUserInput[3].trim()));
						Graph.addEdge(e);
						System.out.println("Edge " + e + "added to the graph!");
						break;
					}
				}
				//Removes an edge only if 
				//	The vertices exist in the graph
				// 	and there is an edge between the two vertices
				case 5 : {
					if (Graph.vertexExists(splitUserInput[1].trim()) == false) {
						System.out.println("\nSorry but, Vertex: " + splitUserInput[1].trim() +" doesn't exist."
								          +"\nPlease add Vertex: "+ splitUserInput[1].trim() + " before adding the edge");
						break;
					}
					else if (Graph.vertexExists(splitUserInput[2].trim()) == false) {
						System.out.println("\n Sorry but, Vertex: " + splitUserInput[2].trim() +" doesn't exist."
								          +"\nPlease add Vertex: "+ splitUserInput[2].trim() + " before adding the edge");
						break;
					}
					
					else if(Graph.getVertex(splitUserInput[1].trim()).isAdjacent(Graph.getVertex(splitUserInput[2].trim())) == false) {
						System.out.println("An Edge between " + splitUserInput[1].trim() + " and "+ splitUserInput[2].trim() + " doesn't exist!"
								         +"\nI won't try to remove an edge that doesn't exist!");
						break;
					}
					
					else {
						Graph.removeEdge(Graph.getEdge(splitUserInput[1].trim(),splitUserInput[2].trim()));
						System.out.println("Removed the edge between " + splitUserInput[1].trim() + " and " + splitUserInput[2].trim());
						break;
					}
				}
				
				//Prints the graph
				case 6 : {
					System.out.println("---***Adjacency List Graph***----");
					System.out.println(Graph);
					break;
				}
		   }
		}
		scanner.close();
		System.out.println("\nBye!");
		
	}

	public static void readVertexfile(){
		// The name of the file to open.
        String fileName = "vertex.txt";

     // This will reference one line at a time
        String line = null; 

        try {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Program will keep reading line by line until there are no lines
            while((line = bufferedReader.readLine()) != null) {
            	
            	//Creates a new Vertex based off just read information
            	Vertex v = new Vertex(line.trim());
            	
            	//Graph will add newly created vertex.
            	Graph.addVertex(v);
            }
            bufferedReader.close();   
        }
        //Error is thrown if unable to find file
        catch(FileNotFoundException ex) {
        	System.out.println("Unable to open file '" + fileName + "'");
        }
        //Error is thrown when unable to read the file
        catch(IOException ex) {
        	System.out.println("Error reading file '" + fileName + "'");
        } 
    }
	
	public static void readEdgefile(){
		// The name of the file to open.
        String fileName = "edge.txt";

     // This will reference one line at a time
        String line = null; 

        try {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //Since the file contains information every three lines, 
            //	the int count and if statements are used to let the program know what kind of information it's handling.
            int count=0; String v1=null; String v2=null; String w=null;
            while((line = bufferedReader.readLine()) != null) {
            	if (count==0) {
            		v1=line; 
            		count++;
            	} 
            	else if(count==1){ 
            		v2=line; 
            		count++;
            	} 
            	else if(count==2){ 
            		//Assuming that this piece of information is the weight of the edges, 
            		//it knows that it has the necessary pieces of information to create an edge between two vertices
            		//Graph will than add the edge
            		w=line;
            		Edge e = new Edge(Graph.getVertex(v1),Graph.getVertex(v2),Double.parseDouble(w));
            		Graph.addEdge(e);
            		count=0;
            	}
            }
            bufferedReader.close();   
        }
        //Error is thrown if unable to find file
        catch(FileNotFoundException ex) {
        	System.out.println("Unable to open file '" + fileName + "'");
        }
        //Error is thrown when unable to read the file
        catch(IOException ex) {
        	System.out.println("Error reading file '" + fileName + "'");
        } 
    }
	
	public static String Dijikstra(Vertex startVert, Vertex destVert) {
		
		//Create a PriorityQueue of Vertices that are ordered by value
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		
		//Create an ArrayList of Vertices that are adjacent to the starting vertex
		ArrayList<Vertex> adjVerts= startVert.getAdjVerts();
		
		//Add the starting vertex to the queue
		queue.add(startVert);
		
		//mark the starting vertex as visited
		startVert.visited();
		
		//set cost as 0
		double cost=0;
		
		//set start Vertex aka the origin's cost as 0
		startVert.setValue(0);
		
		//for as long as there is an edge
		for(int i=0 ; i< Graph.numberOfEdges();i++) {
			
			//for every vertex in the queue
			for (Vertex vertIter : queue) {
				
				//Get the current vertex's adjacent vertices
				adjVerts = vertIter.getAdjVerts();
				
				//set cost as the current vertex's value
				cost=vertIter.getValue(); 
				
				//for every adjacent vertex
				//	if the adjacent vertex is the destination vertex
				//		set that adjacent vertex's value as the current cost plus the weight of the edge to get to the destination vertex from the current vertex
				//		set the adjacent vertex's path aka the previous vertex in the path, as the current vertex
				//		then return the shortest path of vertices from the starting vertex to the destination vertex as a string
				// else if the adjacent vertex has not yet been visited
				//		set it's value equal to the cost and the edge weight
				//		set the path as the current vertex in the queue
				//		add the adjacent vertex to the queue
			
				for(Vertex adjVertIter : adjVerts) {
					if(adjVertIter.getLabel().equals(destVert.getLabel())) {
						   adjVertIter.setValue(cost + Graph.edgeWeight(vertIter, adjVertIter)); 
						   adjVertIter.setPath(vertIter);
						   return returnPath(adjVertIter,adjVertIter.getValue());
					}
					else if(adjVertIter.wasVisited() == false){
						adjVertIter.setValue(cost + Graph.edgeWeight(vertIter, adjVertIter)); 
						adjVertIter.setPath(vertIter);
						queue.add(adjVertIter);
						
					}
				}
				
				//Once all of the current vertex's adjacent vertices have been "checked"
				//Mark the current vertex in the queue as visited
				//Then remove from the queue
				vertIter.visited();
				queue.remove();
				
				//This is to avoid  a Java Concurrent Access error
				break;
			}
			
		}
		return null;
		}
	
	//Returns the shortest path as a string and also the cost of using this path
	public static String returnPath(Vertex lastVert, double value) {
		
		//Vertex iter is the iterator node
		Vertex iter=new Vertex("iter");
		iter.setPath(lastVert);
		
		//Will store the path as a vertex
		String path="";
		
		//The int count and if(count == 0) are purely for aesthetic reasons
		//	Doing this will make it so that the string is: ATL -> IAD -> JFK instead of -> IAD -> JFK ->
		int count=0;
		while(iter.getPath() != null) {
				if(count == 0) path=iter.getPath().getLabel();
				
				else path=iter.getPath().getLabel() +" -> "+path; 
				
				iter.setPath(iter.getPath().getPath());
				count++;
		}
		
		//Returns the path as a string along with the cost of using this path
		return path+"\n\tCost: " + value; 
	}
	
}
