package expression.exceptions;

import expression.ExpressionInterface;
import expression.generic.TypeOperation;

public class CheckedNegate<T extends Number> implements ExpressionInterface<T> {
    final ExpressionInterface<T> expr;
    final TypeOperation<T> typeOperation;

    public CheckedNegate(ExpressionInterface<T> expr, TypeOperation<T> typeOperation) {
        this.expr = expr;
        this.typeOperation = typeOperation;
    }

    @Override
    public String toString() {
        return "-(" + expr.toString() + ")";
    }

    @Override
    public T evaluate(T x){
        return typeOperation.not(expr.evaluate(x));
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return typeOperation.not(expr.evaluate(x, y, z));
    }

}
