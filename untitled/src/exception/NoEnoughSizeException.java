package exception;

/**
 * @author T.Whiter
 * @Date 2020/2/17 13:04
 * @Version 1.0
 */
public class NoEnoughSizeException extends Exception {
    public NoEnoughSizeException() {
    }

    public NoEnoughSizeException(String message) {
        super(message);
    }
}
