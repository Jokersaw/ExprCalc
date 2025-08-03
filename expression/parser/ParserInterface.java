package expression.parser;

import expression.ExpressionInterface;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

public interface ParserInterface<T>{
    ExpressionInterface<T> parse(String expression) throws ParsingException, OverflowException;
}
