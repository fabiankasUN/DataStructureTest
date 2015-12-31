public class RepeatedNumberException extends RuntimeException {

	public RepeatedNumberException() {
		super("The size shouldn't be greater than b - a");
	}
}
