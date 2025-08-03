package expression.exceptions;

public class BracketException extends ParsingException{
    public BracketException(String message, int position) {
        super(message, position);
    }
}
