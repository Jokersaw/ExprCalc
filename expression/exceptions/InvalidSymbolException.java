package expression.exceptions;

public class InvalidSymbolException extends ParsingException{
    public InvalidSymbolException(String message, int position) {
        super(message, position);
    }
}
