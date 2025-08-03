package expression.generic;

public class FloatOperation implements TypeOperation<Float> {
    @Override
    public Float add(Float x, Float y) {
        return x + y;
    }

    @Override
    public Float subtract(Float x, Float y) {
        return x - y;
    }

    @Override
    public Float multiply(Float x, Float y) {
        return x * y;
    }

    @Override
    public Float divide(Float x, Float y) {
        return x / y;
    }

    @Override
    public Float not(Float x) {
        return -x;
    }

    @Override
    public Float castType(String x) {
        return Float.parseFloat(x);
    }
}
