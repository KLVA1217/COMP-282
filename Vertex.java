//Ken Leonard Aquino, Jeremy Dominguez, Ivan Hernandez - Project 2
/**
 * Representation of a graph vertex
 */
import java.util.*;

public class Vertex implements Comparable<Vertex>{
  private String label;   // label attached to this vertex
  private ArrayList<Vertex> adjVertices = new ArrayList<Vertex>(); 
  private boolean visited;
  private double value;
  private Vertex path;
  
  /**
   * Construct a new vertex
   * @param label the label attached to this vertex
   */
  Vertex(String label) {
    this.label = label;
  }
  
  /**
   * Get a vertex label
   * @return the label attached to this vertex
   */
  public String getLabel() {
    return label;
  }
  
  //Adds the vertex v to this vertex's adjacent vertices list
  public void setAdjVert(Vertex v) {
	  adjVertices.add(v);
  }
  
  //Removes vertex v fromt this vertex's adjacent vertices list
  public void removeAdjVert(Vertex v) {
	  adjVertices.remove(v);
  }
  
  //returns true if the given vertex v is in this vertex's adjacent vertices list
  public boolean isAdjacent(Vertex v) {
	  for (Vertex adjVertIter : adjVertices) {
		  if( adjVertIter.equals(v) ) {
			  return true;
		  }
	  }
	  return false;
  }
  
  //Returns all of this vertex's adjacent vertices list.
  public ArrayList<Vertex> getAdjVerts(){
	  return adjVertices;
  }
  
  //marks the vertex as visited/seen/used.
  public void visited() {
	  visited=true;
  }
  
  //returns a boolean if this vertex was visited/used
  public boolean wasVisited() {
	  return visited;
  }
  
  //Resets the vertex's visited attribute
  public void resetVisited() {
	  visited=false;
  }
  
  //Resets the vertex's value
  public void resetCost() {
	  value=0;
  }
  
  //A toString method for the vertex object
  public String toString() {
	  return label;
  }
  
  //Returns a toString version of all of this vertex's adjacent vertexes as a string.
  public String toStringAdjVerts(){
	  String adjVerts="";
	  for(Vertex vertIter : adjVertices) {
		  adjVerts = adjVerts + " -> " + vertIter.getLabel();
	  }
	  return adjVerts;
  }

  //Sets the given vertex p as the previous vertex, as part of the path
  public void setPath(Vertex p) {
	  this.path=p;
  }
  
  //returns the vertex object that has been marked as this vertex's previous vertex
  public Vertex getPath() {
	  return path;
  }
  
  //Sets the vertex's value
  public void setValue(double val) {
	  this.value=val;
  }
  
  //Returns Vertex's cost/value from the origin vertex
  public double getValue() {
	  return this.value;
  }
  
  //overrides Java's object.equals() method to see if two vertices are the same because of their name
  public boolean equals(Vertex other) {
	  return this.getLabel() == other.getLabel();
  }
  
  //Compares Vertices by their cost, and are considered the same object if the names are the same
  public int compareTo(Vertex other) {
	if (label.equals(other.getLabel())) {
		return 0;
	}
	
	else if(getValue() > other.getValue()) {
		return 1;
	}
	else {
		return -1;
	}
  }
}