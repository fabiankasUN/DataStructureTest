public class RepeatedNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatedNumberException() {
		super("The size shouldn't be greater than b - a");
	}
}
