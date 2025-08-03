package expression.exceptions;

public class ParsingException extends Exception{
    public ParsingException(String message, int position){
        super("Parsing issue: " + message + "; CHECK POSITION: " + position);
    }

}
