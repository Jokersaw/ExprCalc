package expression;

import expression.generic.TypeOperation;

import java.util.Objects;

public class Variable<T extends Number> implements ExpressionInterface<T> {
    private final String operator;
    private final TypeOperation<T> typeOperation;

    public Variable(String operator, TypeOperation<T> typeOperation) {
        this.operator = operator;
        this.typeOperation = typeOperation;
    }


    @Override
    public T evaluate(T x) {
        if(operator.equals("x")){
            return x;
        }else{
            return typeOperation.not(x);
        }
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (operator){
            case "x" -> x;
            case "-x" -> typeOperation.not(x);
            case "y" -> y;
            case "-y" -> typeOperation.not(y);
            case "z" -> z;
            case "-z" -> typeOperation.not(z);
            default -> typeOperation.castType("0");
        };
    }

    public String toString() {
        return operator;
    }

    public int hashCode() {
        return Objects.hashCode(operator);
    }
}
