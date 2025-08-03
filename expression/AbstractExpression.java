package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;
import expression.generic.TypeOperation;

import java.util.Objects;

public abstract class AbstractExpression<T extends Number> implements ExpressionInterface<T>  {
    private final ExpressionInterface<T>  first;
    private final ExpressionInterface<T>  second;

    protected final TypeOperation<T> type;

    public AbstractExpression(ExpressionInterface<T> first, ExpressionInterface<T> second, TypeOperation<T> type) {
        this.first = first;
        this.second = second;
        this.type = type;
    }

    @Override
    public T evaluate(T x) {
        return operate(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperation() + " " + second + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getClass());
    }

    protected abstract T operate(T x, T y) throws OverflowException, DivisionByZeroException;

    protected abstract String getOperation();
}
