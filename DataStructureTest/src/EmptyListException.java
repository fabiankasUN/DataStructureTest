
public class EmptyListException extends RuntimeException {

	public EmptyListException() {
		super("The size of the list mus't be 0");
	}
}
