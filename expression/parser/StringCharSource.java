package expression.parser;

public class StringCharSource implements CharSource {

    private final String string;
    private int pos;

    public StringCharSource(String string) {
        this.string = string;
    }

    @Override
    public boolean hasNext() {
        return pos < string.length();
    }

    @Override
    public char next() {
        return string.charAt(pos++);
    }

    @Override
    public char seeCurrent() {
        return string.charAt(pos - 1); //Check IndexOutOfBounds
    }

    @Override
    public int getPos() {
        return pos;
    }


    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException("Error position: " + pos + ":" + message);
    }
}
