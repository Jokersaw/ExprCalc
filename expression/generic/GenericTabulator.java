package expression.generic;

import expression.exceptions.EvaluateException;
import expression.exceptions.ExpressionParser;
import expression.exceptions.ParsingException;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private final Map<String, TypeOperation<? extends Number>> operationMap = Map.of("i", new IntegerOperation(true), "d", new DoubleOperation(),
            "bi", new BigIntegerOperation(), "u", new IntegerOperation(false), "f", new FloatOperation(), "s", new ShortOperation());
    /**
     * Вычисляет значения функции в заданных диапазонах переменных.
     *
     * @param mode       режим работы
     *                   <ul>
     *                     <li><b>i</b> — int с детекцией переполнений</li>
     *                     <li><b>d</b> — double</li>
     *                     <li><b>bi</b> — BigInteger</li>
     *                   </ul>
     * @param expression вычисляемое выражение
     * @param x1         начальное значение диапазона переменной x (включительно)
     * @param x2         конечное значение диапазона переменной x (включительно)
     * @param y1         начальное значение диапазона переменной y (включительно)
     * @param y2         конечное значение диапазона переменной y (включительно)
     * @param z1         начальное значение диапазона переменной z (включительно)
     * @param z2         конечное значение диапазона переменной z (включительно)
     * @return трёхмерный массив значений функции, где R[i][j][k] соответствует
     *         x = x1 + i, y = y1 + j, z = z1 + k. Если вычисление завершилось ошибкой,
     *         в соответствующей ячейке содержится {@code null}.
     */
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

        return fillTable(table, expression, x1, x2, y1, y2, z1, z2, operationMap.get(mode));
    }

    private <T extends Number> Object[][][] fillTable(Object[][][] table, String expression, int x1, int x2, int y1, int y2, int z1, int z2, TypeOperation<T> typeOperation){
        ExpressionParser<T> parser = new ExpressionParser<>(typeOperation);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        table[i-x1][j-y1][k-z1] = parser.parse(expression).evaluate(typeOperation.castType(String.valueOf(i)), typeOperation.castType(String.valueOf(j)), typeOperation.castType(String.valueOf(k)));
                    } catch (ParsingException | EvaluateException e) {
                        table[i-x1][j-y1][k-z1] = null;
                    }
                }
            }
        }
        return table;
    }
}
