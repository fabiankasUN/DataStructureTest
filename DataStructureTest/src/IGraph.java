import java.util.ArrayList;

public interface IGraph {

	public ArrayList<Edge>[] unweightedDirectedGraph( int v, int e );

	public ArrayList<Edge>[] weightedDirectedGraph( int v, int e, int maxWeight, boolean negativeWeight );

	public ArrayList<Edge>[] unweightedGraph( int v, int e );

	public ArrayList<Edge>[] weightedGraph( int v, int e, int maxWeight, boolean negativeWeight );

	public ArrayList<Edge>[] unweightedDirectedAcyclicGraph(int v);
	

}
