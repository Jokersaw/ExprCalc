package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class ShortOperation implements TypeOperation<Short>{
    @Override
    public Short add(Short x, Short y) {
        return (short)(x + y);
    }

    @Override
    public Short subtract(Short x, Short y) {
        return (short)(x - y);
    }

    @Override
    public Short multiply(Short x, Short y) {
        return (short)(x*y);
    }

    @Override
    public Short divide(Short x, Short y) {
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return (short)(x/y);
    }

    @Override
    public Short not(Short x) {
        return (short)(-x);
    }

    @Override
    public Short castType(String x) {
        return (short)(Integer.parseInt(x));
    }
}
