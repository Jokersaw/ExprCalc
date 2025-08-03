package expression.exceptions;

public class OverflowException extends EvaluateException{
    public OverflowException(String message, int position) {
        super(message, position);
    }
    public OverflowException(String message){
        super(message);
    }
}
