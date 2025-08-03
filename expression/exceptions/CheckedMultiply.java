package expression.exceptions;

import expression.AbstractExpression;
import expression.ExpressionInterface;
import expression.generic.TypeOperation;

public class CheckedMultiply<T extends Number> extends AbstractExpression<T> {

    public CheckedMultiply(ExpressionInterface<T> first, ExpressionInterface<T> second, TypeOperation<T> typeOperation) {
        super(first, second, typeOperation);
    }

    @Override
    public String getOperation() {
        return "*";
    }

    @Override
    public T operate(T x, T y) {
        return type.multiply(x, y);
    }

}
