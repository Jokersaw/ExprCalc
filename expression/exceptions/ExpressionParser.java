package expression.exceptions;

import expression.*;
import expression.generic.TypeOperation;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.ParserInterface;
import expression.parser.StringCharSource;

import java.util.Arrays;
import java.util.List;

public final class ExpressionParser<T extends Number> implements ParserInterface<T> {
    private final TypeOperation<T> op;

    public ExpressionParser(TypeOperation<T> typeOperation){
        op = typeOperation;
    }

    public ExpressionInterface<T> parse(final String source) throws ParsingException, OverflowException {
        //System.err.println(source);
        return parse(new StringCharSource(source));
    }

    public ExpressionInterface<T> parse(final CharSource source) throws ParsingException, OverflowException {
        return new ParserInner<T>(source, op).parseExpression();
    }

    private static class ParserInner<T extends Number> extends BaseParser<T> {
        private int balance = 0;
        private boolean wasOperation = false;

        private final List<Character> operations = Arrays.asList('+', '-', 's', 'c', '*', '/', ')');

        public ParserInner(CharSource source, TypeOperation<T> typeOperation) {
            super(source, typeOperation);
        }

        public ExpressionInterface<T> parseExpression() throws ParsingException, OverflowException {
            skipWhiteSpace();
            ExpressionInterface<T> result = parseAddSub(0);
            skipWhiteSpace();
            return result;
        }

        /*private ExpressionInterface<T> parseFunc(int negate) throws ParsingException, OverflowException {
            ExpressionInterface<T> first = parseAddSub(negate);
            while (!eof()) {
                if (take('s')) {
                    expect("et");
                    if (!Character.isWhitespace(source.seeCurrent()) && source.seeCurrent() != '-' && source.seeCurrent() != '(') {
                        throw new SetArgumentException("invalid second argument", source.getPos());
                    }
                    skipWhiteSpace();
                    wasOperation = true;
                    first = (new Set(first, parseAddSub(negate)));
                    skipWhiteSpace();
                } else if (take('c')) {
                    expect("lear");
                    if (!Character.isWhitespace(source.seeCurrent()) && source.seeCurrent() != '-' && source.seeCurrent() != '(') {
                        throw new ClearArgumentException("invalid second argument", source.getPos());
                    }
                    skipWhiteSpace();
                    wasOperation = true;
                    first = (new Clear(first, parseAddSub(negate)));
                    skipWhiteSpace();
                } else {
                    break;
                }
            }
            return first;
        }*/

        private ExpressionInterface<T> parseAddSub(int negate) throws ParsingException, OverflowException {
            ExpressionInterface<T> first = parseHighRank(negate);
            skipWhiteSpace();
            while (!eof()) {
                if (take('+')) {
                    wasOperation = true;
                    first = (new CheckedAdd<T>(first, parseHighRank(negate), typeOperation));
                    skipWhiteSpace();
                } else if (take('-')) {
                    wasOperation = true;
                    first = (new CheckedSubtract<>(first, parseHighRank(negate), typeOperation));
                    skipWhiteSpace();
                } else {
                    break;
                }
            }
            return first;
        }


        private ExpressionInterface<T> parseHighRank(int negate) throws ParsingException, OverflowException {
            ExpressionInterface<T> first = parseNoRank(negate);
            while (!eof()) {
                if (take('*')) {
                    wasOperation = true;
                    first = (new CheckedMultiply<>(first, parseNoRank(negate), typeOperation));
                    skipWhiteSpace();
                } else if (take('/')) {
                    wasOperation = true;
                    first = (new CheckedDivide<T>(first, parseNoRank(negate), typeOperation));
                    skipWhiteSpace();
                } else {
                    break;
                }
            }
            return first;
        }

        private ExpressionInterface <T>parseNoRank(int negate) throws ParsingException, OverflowException {
            skipWhiteSpace();
            if (take('-')) {
                ExpressionInterface<T> first;
                if (Character.isWhitespace(source.seeCurrent()) || source.seeCurrent() == '(') {
                    first = new CheckedNegate<>(parseNoRank(negate), typeOperation);
                } else {
                    first = parseNoRank(negate + 1);
                }
                return first;
            }else if (between('0', '9')) {
                wasOperation = false;
                return parseConst(negate);
            } else if (Character.isLetter(source.seeCurrent())) {
                char a = source.seeCurrent();
                if (a != 'x' && a != 'y' && a != 'z') {
                    throw new InvalidSymbolException("invalid variable, use only x, y, z", source.getPos());
                }
                wasOperation = false;
                return parseVariable(negate);
            } else if (take('(')) {
                skipWhiteSpace();
                balance++;
                wasOperation = false;
                ExpressionInterface<T> first = parseAddSub(negate);
                if (source.seeCurrent() != ')' || eof()) { // if see not ')'
                    throw new BracketException("no ')' expected", source.getPos());
                }
                take();//for ')'
                //wasOperation = false;
                balance--;
                skipWhiteSpace();
                if (source.seeCurrent() == '(' || source.seeCurrent() == ')' && balance <= 0 && !eof()) {
                    throw new BracketException("no operation before '(' / too many ')'", source.getPos());
                }
                if (!operations.contains(source.seeCurrent()) && !eof()) {
                    throw new InvalidSymbolException("invalid symbol after ')'", source.getPos());
                }
                return first;
            }
            if (!wasOperation) {
                throw new NoElementException("no first element", source.getPos());
            } else {
                skipWhiteSpace();
                if (eof() || source.seeCurrent() == ')') {
                    throw new NoElementException("no last element", source.getPos());
                }
                if (operations.contains(source.seeCurrent()) && source.seeCurrent() != ')') {
                    throw new NoElementException("no middle element", source.getPos());
                }
            }
            throw source.error("Incorrect expr");
        }

        private ExpressionInterface<T> parseVariable(int negative) throws ParsingException {
            char vr = take();
            skipWhiteSpace();
            ExceptionCausedByConstVariable();

            if (negative % 2 != 0) {
                return new Variable<>("-" + vr, typeOperation);
            } else {
                return new Variable<>(String.valueOf(vr), typeOperation);
            }
        }

        private ExpressionInterface<T> parseConst(int negative) throws ParsingException, OverflowException {
            StringBuilder sb = new StringBuilder();
            while (between('0', '9')) {
                sb.append(take());
            }
            skipWhiteSpace();
            ExceptionCausedByConstVariable();

            if (negative % 2 != 0) {
                T result;
                try {
                    result = typeOperation.castType("-" + sb);
                } catch (NumberFormatException e) {
                    throw new OverflowException("negative integer overflow", source.getPos());
                }
                return new Const<>(result);
            } else {
                T result;
                try {
                    result = typeOperation.castType(sb.toString());
                } catch (NumberFormatException e) {
                    throw new OverflowException("positive integer overflow", source.getPos());
                }
                return new Const<>(result);
            }
        }

        private void ExceptionCausedByConstVariable() throws BracketException, InvalidSymbolException {
            if (balance <= 0 && source.seeCurrent() == ')') {
                throw new BracketException("no '(' in expr", source.getPos());
            }
            if (!operations.contains(source.seeCurrent()) && !eof()) {
                throw new InvalidSymbolException("invalid symbol after const/variable", source.getPos());
            }
        }
    }


}
