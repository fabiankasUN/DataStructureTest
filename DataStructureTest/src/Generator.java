import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Generator {

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
	public int getInt( int min, int max ) {
		if ( min < 0 )
			throw new IllegalArgumentException("min should be greater or equal to 0");
		if ( max < 0 )
			throw new IllegalArgumentException("max should be greater or equal to 0");
		if ( min >= max )
			throw new IllegalArgumentException("min should be less than max");
		return r.nextInt(max - min) + min;
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
	public ArrayList<Edge>[] unweightedDG( int v, int e ) {
		if ( v == 0 )
			throw new IllegalArgumentException();
		if ( e > v * v && v > 1 )
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

}
