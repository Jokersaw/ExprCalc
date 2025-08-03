package expression.exceptions;

public class ClearArgumentException extends ParsingException{
    public ClearArgumentException(String message, int position) {
        super(message, position);
    }
}
