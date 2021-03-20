//Ken Leonard Aquino, Jeremy Dominguez, Ivan Hernandez - Project 2
/**
 * Adjacency list implementation of a graph
 */
import java.util.*;

public class AdjListGraph{
  
	//Contains all the Graph's vertices
	private final ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	
	//Contains all the Graph's Edges
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    
    //Adds the vertex to the vertices arraylist
    public void addVertex(Vertex v){
    	vertices.add(v);
    }
    
    //Removes Vertex from the graph by 
    //	removing it's edges/connections to it's adjacent vertices first 
    //	then removing the vertex from the list
    public void removeVertex(Vertex v) {
    	ArrayList<Vertex> adjVerts = v.getAdjVerts();
    	
    	for(int i=0; i<adjVerts.size();i++) {
    		for(Vertex adjVertIter : adjVerts) {
    			removeEdge(getEdge(v.getLabel(),adjVertIter.getLabel()));
    			break;
    		}
    	}
    	vertices.remove(v);
    }
    
    //Returns a vertex with the name
    public Vertex getVertex(String v){
    	for (Vertex verIter : vertices) {
    		if(verIter.getLabel().equals(v)) return verIter;
    	}
    	return null;
    }
    
    //Checks if a given vertex, converted from a string exists in the graph
    public boolean vertexExists(String v) {
    	v.trim();
    	for (Vertex vertIter : vertices) {
    		if(vertIter.getLabel().equals(v)) {return true;}
    	}
    	return false;
    }
    
    //Returns how many vertices are in the vertices list, which is equivalent to how big the list is
    public int numberOfVertices(){
    	return vertices.size();
    }
    
    //Checks to see if vertex v1 is adjacent to vertex v2 by 
    //	accessing v1's adjacent vertices list and comparing it's names to string v2's name, after being converted from string to a vertex
    public boolean isAdjacent(String v1, String v2) {
    	ArrayList<Vertex> adjVerts = getVertex(v1).getAdjVerts();
    	
    	for(int i=0; i<adjVerts.size();i++) {
    		for(Vertex adjVertIter : adjVerts) {
    			if (adjVertIter.getLabel().equals( getVertex(v2).getLabel() ) ) {return true;}
    			break;
    		}
    	}
    	return false;
    }
    
    //Adds an edge to the edges list
    //	as well as make the vertex v1 know that it is adjacent to v2 (because of the edge) and vice versa by adding the opposite vertex to the vertex's adjacent vertices list
    public void addEdge(Edge e){
    	e.getVertex1().setAdjVert(e.getVertex2());
    	e.getVertex2().setAdjVert(e.getVertex1());
    	edges.add(e);
    }
    
    //Removes an edge by letting the vertices know that they are no longer a part of the other's adjacent vertices list
    // Then remove the edge from the edges list
    public void removeEdge(Edge e) {
    	e.getVertex1().removeAdjVert(e.getVertex2());
    	e.getVertex2().removeAdjVert(e.getVertex1());
    	edges.remove(e);
    }
    
    //Returns an edge given the string name of two vertices
    //	It does this by comparing the names given by v1 and v2 until they match both of the edges vertices names.
    public Edge getEdge(String v1, String v2) {
    	for(Edge EdIter : edges) {
    		if(EdIter.getVertex1().getLabel().equals(v1) && EdIter.getVertex2().getLabel().equals(v2)) {
    			return EdIter;
    		}
    		else if(EdIter.getVertex2().getLabel().equals(v1) && EdIter.getVertex1().getLabel().equals(v2)) {
    			return EdIter;
    		}
    	}
    	return null;
    }
    
    //Returns the weight of the edge given the names of the two vertices in question
    public double edgeWeight(Vertex v1, Vertex v2) {
    	for(Edge EdIter : edges) {
    		if(EdIter.getVertex1().equals(v1) && EdIter.getVertex2().equals(v2)) {
    			return EdIter.getWeight();
    		}
    		else if(EdIter.getVertex2().equals(v1) && EdIter.getVertex1().equals(v2)) {
    			return EdIter.getWeight();
    		}
    	}
    	return 0;
    }
    
    //Returns the number of edges in the graph which is the same has how many elements are in the list
   public int numberOfEdges(){
	   return edges.size();
   }
   
   //Goes through edges list to see if there is an edge where both vertex1's name and vertex2's name are the same
   //	If found it, then it returns true
   //	else it would leave the advanced for loop and return false
   public boolean edgeExists(Edge e){
	   for(Edge EdIter : edges) {
   		if( EdIter.getVertex1().equals(e.getVertex1() ) && EdIter.getVertex2().equals(e.getVertex2() ) ) {
   			return true;
   		}
   		else if(EdIter.getVertex2().equals(e.getVertex1()) && EdIter.getVertex1().equals(e.getVertex2())) {
   			return true;
   		}
   	}
   	return false;
   }
   
   //A  toString method for the Graph object
   //Returns a string that shows the Adjacent List Represenation of the Graph
   public String toString(){
	   String Graph="";
	   
	   for (Vertex vertIter : vertices) {
		   
		   ArrayList<Vertex> adjVerts= vertIter.getAdjVerts() ;
		   
		   Graph=Graph + "\n" + vertIter;
		   
		   for (Vertex adjVertIter : adjVerts) {
			   Graph=Graph+"---> " +adjVertIter+ "("+edgeWeight(vertIter,adjVertIter)+")" ;
		   }
	   }
	   
	   return Graph;
   }
   
   //Runs a BFS search on the graph to see if a path is possible,
   //	This is a different implementation of a BFS because vertices are not given weight by how far the origin they are 
   //	but they are considered visited and put into the queue the moment they are spotted
   //	The BFS search stops either 
   //		when the vertex's adjacent vertices is the destination vertex 
   //		or it has gone through all the edges and nothing still showed up.
   public boolean doesPathExist(Vertex startVert,Vertex destVert) {
		   PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		   ArrayList<Vertex> adjVerts= startVert.getAdjVerts();
		   
		   queue.add(startVert);
		   startVert.visited();
			
		   for(int i=0; i<=edges.size();i++) {
			   
			   for (Vertex vertIter : queue) {
				   adjVerts = vertIter.getAdjVerts(); 
				   
				   for(Vertex adjVertIter : adjVerts) {
				   		   if(adjVertIter.getLabel().equals(destVert.getLabel())) {
				   			   return true;
					   }
					   else if (adjVertIter.wasVisited() == false) {
						   queue.add(adjVertIter);
						   adjVertIter.visited();
					   }
				   }
				   queue.remove(); 
				   break;
			   }
		   }
		   return false;
   }  
   
   //Resets some of the vertices' attributes  in the graph so that it's reset as new every-time Dijikstra's algorithim is used.
   public void resetGraph(){
	   for(Vertex vertIter : vertices) {
		   vertIter.setPath(null);
		   vertIter.resetVisited();
		   vertIter.resetCost();
	   }
   }
}