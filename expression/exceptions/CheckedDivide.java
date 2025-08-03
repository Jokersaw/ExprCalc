package expression.exceptions;

import expression.AbstractExpression;
import expression.ExpressionInterface;
import expression.generic.TypeOperation;

public class CheckedDivide<T extends Number> extends AbstractExpression<T> {

    public CheckedDivide(ExpressionInterface<T> first, ExpressionInterface<T> second, TypeOperation<T> type) {
        super(first, second, type);
    }

    @Override
    public String getOperation() {
        return "/";
    }

    @Override
    public T operate(T x, T y){
        return type.divide(x,y);
    }

}
