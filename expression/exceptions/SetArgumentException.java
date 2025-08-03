package expression.exceptions;

public class SetArgumentException extends ParsingException{
    public SetArgumentException(String message, int position) {
        super(message, position);
    }
}
