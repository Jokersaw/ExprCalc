package expression.parser;

public interface CharSource {
    boolean hasNext();

    char next();

    char seeCurrent();

    int getPos();

    IllegalArgumentException error(String message);
}
