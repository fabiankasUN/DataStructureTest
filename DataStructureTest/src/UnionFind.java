
public class UnionFind {

	private int father[];
	private int range[];

	public UnionFind( int v ) {
		father = new int[v];
		range = new int[v];
		for ( int i = 0; i < v; i++ ) {
			father[i] = i;
			range[i] = 0;
		}
	}

	public void union( int u, int v ) {
		if ( u == v )
			return;
		int xRoot = find(u);
		int yRoot = find(v);

		if ( range[xRoot] > range[yRoot] ) {
			father[yRoot] = xRoot;
		} else {
			father[xRoot] = yRoot;
			if ( range[xRoot] == range[yRoot] ) {
				range[yRoot]++;
			}
		}
	}

	public int find( int u ) {
		if ( father[u] == u )
			return u;
		return father[u] = find(u);
	}

	public boolean sameComponent( int u, int v ) {
		return find(u) == find(v);
	}

}
