
public class EmptyListException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyListException() {
		super("The size of the list mus't be 0");
	}
}
