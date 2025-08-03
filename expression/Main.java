package expression;

import expression.exceptions.*;
import expression.generic.DoubleOperation;
import expression.generic.GenericTabulator;

import java.util.ArrayList;
import java.util.Arrays;

/* Документация для метода tabulate описана */
public class Main {
    public static void main(String[] args) throws Exception {
        GenericTabulator tabulator = new GenericTabulator();
        Object[][][] result = tabulator.tabulate(args[0], args[1], -2, -2, -2, -2, -2, -2);
        System.out.println(Arrays.deepToString(result));
    }
}
