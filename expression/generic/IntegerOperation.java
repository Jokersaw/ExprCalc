package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerOperation implements TypeOperation<Integer> {
    /*private IntegerOperation() {

    }

    public static final IntegerOperation i = new IntegerOperation();*/
    private final boolean check;

    public IntegerOperation(boolean check) {
        this.check = check;
    }

    @Override
    public Integer add(Integer x, Integer y) throws OverflowException {
        if (check) {
            if (x >= 0 && Integer.MAX_VALUE - x < y) {
                throw new OverflowException("add overflow");
            }
            if (x < 0 && Integer.MIN_VALUE - x > y) {
                throw new OverflowException("add overflow");
            }
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (check) {
            if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
                throw new OverflowException("sub overflow");
            }
            if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
                throw new OverflowException("sub overflow");
            }
        }

        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (check) {
            if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
                throw new OverflowException("mul overflow");
            }
            if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
                throw new OverflowException("mul overflow");
            }
            if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
                throw new OverflowException("mul overflow");
            }
            if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
                throw new OverflowException("mul overflow");
            }
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (check) {
            if (x == Integer.MIN_VALUE && y == -1) {
                throw new OverflowException("divide overflow"); //get out code
            }
        }
        if (y == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return x / y;
    }

    @Override
    public Integer not(Integer x) {
        if(check){
            if (x == Integer.MIN_VALUE) {
                throw new OverflowException("negate overflow");
            }
        }
        return -x;
    }

    @Override
    public Integer castType(String x) {
        return Integer.parseInt(x);
    }
}
