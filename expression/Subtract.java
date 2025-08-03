package expression;

import expression.generic.TypeOperation;

public class Subtract<T extends Number> extends AbstractExpression<T> {

    public Subtract(ExpressionInterface<T> first, ExpressionInterface<T> second, TypeOperation<T> typeOperation) {
        super(first, second, typeOperation);
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public T operate(T x, T y) {
        return type.subtract(x, y);
    }

}