package expression.exceptions;

public class NoElementException extends ParsingException{
    public NoElementException(String message, int position) {
        super(message, position);
    }
}
