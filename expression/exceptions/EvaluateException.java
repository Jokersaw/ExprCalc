package expression.exceptions;

public class EvaluateException extends ArithmeticException{
    public EvaluateException(String message, int position){
        super("Evaluating issue: " + message + "; CHECK POSITION: " + position);
    }

    public EvaluateException(String message){
        super("Evaluating issue: " + message);
    }
}
