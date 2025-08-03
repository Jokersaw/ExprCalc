package expression.exceptions;

public class CountArgumentException extends ParsingException{
    public CountArgumentException(String message, int position) {
        super(message, position);
    }
}
