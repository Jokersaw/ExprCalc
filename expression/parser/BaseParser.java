package expression.parser;

import expression.generic.TypeOperation;

public class BaseParser<T extends Number> {
    private static final char END = '\0';
    protected final CharSource source;
    protected TypeOperation<T> typeOperation;
    private char ch;

    public BaseParser(CharSource source, TypeOperation<T> typeOperation) {
        this.source = source;
        this.typeOperation = typeOperation;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(char expected) {
        if (ch == expected) {
            take();
            return true;
        }else{
            return false;
        }
    }

    protected void expect(char expected) {
        if (!take(expected)) {
            throw source.error("Expected '" + expected + "', found '" + (ch == END ? "END" : ch) + "'");
        }
    }

    protected void expect(String chars){
        for(char ch: chars.toCharArray()){
            expect(ch);
        }
    }
    protected void checkEof() {
        if(!eof()){
            throw source.error("Expected EOF, found '" + ch + "'");
        }
    }

    protected boolean eof() {
        return ch == END;
    }

    protected boolean between(final char ch1, final char ch2) {
        return ch1 <= ch && ch <= ch2;
    }

    protected void skipWhiteSpace() {
        while(Character.isWhitespace(ch)){
            take();
        }
    }

}
