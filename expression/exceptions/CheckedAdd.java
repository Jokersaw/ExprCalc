package expression.exceptions;

import expression.AbstractExpression;
import expression.ExpressionInterface;
import expression.generic.TypeOperation;

public class CheckedAdd<T extends Number> extends AbstractExpression<T> {
    public CheckedAdd(ExpressionInterface<T> first, ExpressionInterface<T> second, TypeOperation<T> type) {
        super(first, second, type);
    }

    @Override
    public String getOperation() {
        return "+";
    }

    @Override
    public T operate(T x, T y) throws OverflowException {
        return type.add(x, y);
    }
}
