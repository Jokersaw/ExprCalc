package expression.generic;

public class DoubleOperation implements TypeOperation<Double> {
    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double not(Double x) {
        return -x;
    }

    @Override
    public Double castType(String x) {
        return Double.parseDouble(x);
    }
}
