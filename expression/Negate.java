package expression;

import expression.generic.TypeOperation;

public class Negate<T extends Number> implements ExpressionInterface<T>{
    final ExpressionInterface<T> expr;
    final TypeOperation<T> typeOperation;

    public Negate(ExpressionInterface<T> expr, TypeOperation<T> typeOperation) {
        this.expr = expr;
        this.typeOperation = typeOperation;
    }

    @Override
    public String toString() {
            return "-(" + expr.toString() + ")";
    }

    @Override
    public T evaluate(T x) {
        return typeOperation.not(expr.evaluate(x));
    } //checkforOverflow

    @Override
    public T evaluate(T x, T y, T z) {
        return null;
    }
}
