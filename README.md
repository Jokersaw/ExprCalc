# ExprCalc
Класс expression.generic.GenericTabulator, реализующий интерфейс expression.generic.Tabulator:

    public interface Tabulator {
        Object[][][] tabulate(
            String mode, String expression,
            int x1, int x2, int y1, int y2, int z1, int z2
        ) throws Exception;
    }
Аргументы

mode — режим работы
Режим	Тип
i	int с детекцией переполнений
d	double
bi	BigInteger
expression — вычисляемое выражение;
x1, x2; y1, y2; z1, z2 — диапазоны изменения переменных (включительно).
Возвращаемое значение — таблица значений функции, где R[i][j][k] соответствует x = x1 + i, y = y1 + j, z = z1 + k. Если вычисление завершилось ошибкой, в соответствующей ячейке должен быть null.

В классе Main можно запустить решение, пример ввода: "i" "((x + y) * z) / 10" <ваши диапазоны значений x,y,z>
