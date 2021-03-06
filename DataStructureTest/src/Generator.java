import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Generator implements IGeneratorData, IGraph {

	private Random r = new Random();

	/**
	 * 
	 * Returns a pseudorandom, uniformly distributed int value between min
	 * (inclusive) and the specified value max (exclusive)
	 * 
	 * @param min
	 *            the lower bound (inclusive). Must be positive.
	 * @param max
	 *            the upper bound (exclusive). Must be positive.
	 * 
	 * @exception IllegalArgumentException
	 *                if min or max are not positive or if min is greater than
	 *                max
	 * 
	 * @return the next pseudorandom, uniformly distributed int value between
	 *         min (inclusive) and bound max (exclusive) from this random number
	 *         generator's sequence
	 * 
	 */
	@Override
	public int getInt( int min, int max ) {
		if ( min < 0 )
			throw new IllegalArgumentException("min should be greater or equal to 0");
		if ( max < 0 )
			throw new IllegalArgumentException("max should be greater or equal to 0");
		if ( min >= max )
			throw new IllegalArgumentException("min should be less than max");
		return r.nextInt(max - min) + min;
	}

	@Override
	public int getNegativeInt( int min, int max ) {
		if ( min >= max )
			throw new IllegalArgumentException("min should be less than max");
		if ( min < 0 && max < 0 ) {
			return -getInt(-min, -max);
		}
		if ( min <= 0 && max > 0 ) {
			return getInt(0, -min + max) + min;
		}
		if ( min >= 0 && max > 0 ) {
			return getInt(min, max);
		}
		throw new IllegalArgumentException("Case not found");
	}

	/**
	 * @param min
	 *            the lower bound (inclusive). Must be positive.
	 * @param max
	 *            the upper bound (exclusive). Must be positive.
	 * @param size
	 *            The number of numbers that be created
	 * @return A list with b - a different numbers
	 */
	@Override
	public ArrayList<Integer> notRepeatListInt( int min, int max, int size ) {
		if ( min < 0 )
			throw new IllegalArgumentException("min should be greater or equal to 0");
		if ( max < 0 )
			throw new IllegalArgumentException("max should be greater or equal to 0");
		if ( min >= max )
			throw new IllegalArgumentException("min should be less than max");
		if ( size > max - min )
			throw new RepeatedNumberException();
		int n;
		HashSet<Integer> set = new HashSet<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		do {
			n = r.nextInt(max - min) + min;
			if ( !set.contains(n) ) {
				set.add(n);
				list.add(n);
			}
		} while ( set.contains(n) && set.size() < size );
		return list;
	}

	/**
	 * @param min
	 * @param max
	 * @param size
	 * @return
	 */
	@Override
	public ArrayList<Integer> repeatListInt( int min, int max, int size ) {
		if ( min < 0 )
			throw new IllegalArgumentException("min should be greater or equal to 0");
		if ( max < 0 )
			throw new IllegalArgumentException("max should be greater or equal to 0");
		if ( min >= max )
			throw new IllegalArgumentException("min should be less than max");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for ( int i = 0; i < size; i++ ) {
			list.add(r.nextInt(max - min) + min);
		}
		return list;
	}

	/**
	 * @param list
	 * @return
	 */
	@Override
	public int selectRandomItemInt( ArrayList<Integer> list ) {
		if ( list.size() == 0 )
			throw new EmptyListException();
		return list.get(r.nextInt(list.size()));
	}

	/**
	 * @param size
	 * @param list
	 * @return
	 */
	@Override
	public ArrayList<Integer> selectRandomItemsInt( int size, ArrayList<Integer> list ) {
		if ( list.size() == 0 )
			throw new EmptyListException();
		ArrayList<Integer> res = new ArrayList<Integer>();
		for ( int i = 0; i < size; i++ ) {
			res.add(selectRandomItemInt(list));
		}
		return res;
	}

	/**
	 * @param list
	 * @return
	 */
	@Override
	public String selectRandomItemString( ArrayList<String> list ) {
		if ( list.size() == 0 )
			throw new EmptyListException();
		return list.get(r.nextInt(list.size()));
	}

	/**
	 * @param size
	 * @param usedLetters
	 * @return
	 */
	@Override
	public String makeString( int size, int usedLetters ) {
		if ( size == 0 || usedLetters == 0 )
			throw new IllegalArgumentException();
		String s = "";
		for ( int i = 0; i < size; i++ ) {
			s += (char) ( r.nextInt(usedLetters) + 'a' );
		}
		return s;
	}

	/**
	 * @param sizeCad
	 * @param usedLetters
	 * @param sizeList
	 * @return
	 */
	@Override
	public ArrayList<String> notRepeatListString( int sizeCad, int usedLetters, int sizeList ) {
		if ( sizeCad == 0 || usedLetters == 0 )
			throw new IllegalArgumentException();
		ArrayList<String> list = new ArrayList<String>();
		HashSet<String> set = new HashSet<>();
		String temp;
		for ( int i = 0; i < sizeList; i++ ) {
			temp = makeString(sizeCad, usedLetters);
			if ( set.contains(temp) ) {
				i--;
			} else {
				list.add(temp);
			}
		}
		return list;
	}

	/**
	 * @param v
	 * @return
	 */

	private ArrayList<Edge>[] makeGraph( int v ) {
		ArrayList<Edge>[] g = new ArrayList[v];
		for ( int i = 0; i < v; i++ ) {
			g[i] = new ArrayList<Edge>();
		}
		return g;
	}

	/**
	 * @param v
	 * @param e
	 * @return
	 */
	@Override
	public ArrayList<Edge>[] unweightedDirectedGraph( int v, int e ) {
		if ( v == 0 )
			throw new IllegalArgumentException();
		if ( e > ( v - 1 ) * v && v > 1 )
			throw new MakeGraphException("E should be less or equal than v*v");
		if ( v == 1 && e > 0 )
			throw new MakeGraphException("E should be 0 because v is 1");

		ArrayList<Edge>[] graph = makeGraph(v);
		HashSet<String> set = new HashSet<String>();
		int a, b;
		while ( e > 0 ) {
			a = r.nextInt(v);
			b = r.nextInt(v);
			if ( !set.contains(a + " " + b) && a != b ) {
				set.add(a + " " + b);
				graph[a].add(new Edge(b));
				e--;
			}
		}
		return graph;
	}

	/**
	 * @param v
	 * @param e
	 * @param maxWeight
	 * @param negativeWeight
	 * @return
	 */
	@Override
	public ArrayList<Edge>[] weightedDirectedGraph( int v, int e, int maxWeight, boolean negativeWeight ) {
		ArrayList<Edge>[] graph = unweightedDirectedGraph(v, e);
		for ( int i = 0; i < v; i++ ) {
			for ( int j = 0; j < graph[i].size(); i++ ) {
				if ( negativeWeight )
					graph[i].get(j).setW(getNegativeInt(-maxWeight, maxWeight));
				else
					graph[i].get(j).setW(getInt(0, maxWeight));
			}
		}
		return graph;
	}

	@Override
	public ArrayList<Edge>[] unweightedGraph( int v, int e ) {
		if ( v == 0 )
			throw new IllegalArgumentException();
		if ( e > ( v * ( v - 1 ) ) / 2 && v > 1 )
			throw new MakeGraphException("E should be less or equal than ( v * ( v - 1 ) ) / 2 in NonDirectedGraphs");
		if ( v == 1 && e > 0 )
			throw new MakeGraphException("E should be 0 because v is 1");

		ArrayList<Edge>[] graph = makeGraph(v);
		HashSet<String> set = new HashSet<String>();
		int a, b;
		while ( e > 0 ) {
			a = r.nextInt(v);
			b = r.nextInt(v);
			if ( !set.contains(a + " " + b) && a != b ) {
				set.add(a + " " + b);
				graph[a].add(new Edge(b));
				e--;
			}
		}
		
		
		return null;
	}

	@Override
	public ArrayList<Edge>[] weightedGraph( int v, int e, int maxWeight, boolean negativeWeight ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Edge>[] unweightedDirectedAcyclicGraph( int v ) {
		// TODO Auto-generated method stub
		return null;
	}

}
