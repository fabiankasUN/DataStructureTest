import java.util.ArrayList;

public interface IGeneratorData {

	public int getInt( int min, int max );

	public int getNegativeInt( int min, int max );

	public ArrayList<Integer> notRepeatListInt( int min, int max, int size );

	public ArrayList<Integer> repeatListInt( int min, int max, int size );

	public int selectRandomItemInt( ArrayList<Integer> list );

	public String selectRandomItemString( ArrayList<String> list );

	public ArrayList<Integer> selectRandomItemsInt( int size, ArrayList<Integer> list );

	public String makeString( int size, int usedLetters );

	public ArrayList<String> notRepeatListString( int sizeCad, int usedLetters, int sizeList );

}
