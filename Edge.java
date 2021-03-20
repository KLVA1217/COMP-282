//Ken Leonard Aquino, Jeremy Dominguez, Ivan Hernandez - Project 2
/**
 * Representation of an undirected graph edge
 */
public class Edge {
  
	// instance variables
	public Vertex vertex1, vertex2;  // vertices at either end of this edge
	public double weight;            // distance between vertices (weight)
  
	/**
   	* Construct a new edge
   	* @param v1 vertex at one end of this edge
   	* @param v2 vertex at the other end of this edge
   	* @param wt the distance or weight of this edge
   	*/
  	public Edge(Vertex v1, Vertex v2, double wt) {
  		vertex1 = v1;
  		vertex2 = v2;
	    weight = wt;
  	}
  
  	/**
  	* Return the other vertex associated with this edge
  	* @param v one of the vertices on this edge
  	* @return the other vertex on this edge
  	* @throws IllegalArgumentException if v is not one of the
  	*         vertices on this edge
  	*/
  	public Vertex opposite(Vertex v) {
  		
  		if (v.getLabel().equals(vertex1.getLabel())) 
  			return this.vertex2;
  		
  		else if (v.getLabel().equals(vertex2.getLabel())) 
  			return this.vertex1;
  		
  		else throw new UnsupportedOperationException();
  	}
  	
  	//Returns Vertex1 of the edge
  	 public Vertex getVertex1(){
  		 return vertex1;
  	 }
  	 
  	 //Returns Vertex2 of the edge
  	 public Vertex getVertex2(){
 		 return vertex2;
 	 }
  	 
  	 //Returns the weight of the edge
  	 public double getWeight(){
  		 return this.weight;
  	 }
  	 
  	 //To string method for the edge
  	 public String toString(){
  		 return vertex1 +" <---("  +weight+ ")---> " +vertex2; 
  	 }
}