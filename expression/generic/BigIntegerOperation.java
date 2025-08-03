package expression.generic;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerOperation implements TypeOperation<BigInteger> {
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        if(y.equals(BigInteger.ZERO)){
            throw new DivisionByZeroException("division by zero");
        }
        return x.divide(y);
    }

    @Override
    public BigInteger not(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger castType(String x) {
        return new BigInteger(x);
    }
}
