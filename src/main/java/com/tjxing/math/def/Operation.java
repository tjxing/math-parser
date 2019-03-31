package com.tjxing.math.def;

import java.util.function.BiFunction;

public class Operation implements Expr {

    public static enum Oper {
        PLUS("+", (Double left, Double right) -> left + right),
        MINUS("-", (Double left, Double right) -> left - right),
        TIMES("*", (Double left, Double right) -> left * right),
        DIV("/", (Double left, Double right) -> left / right);

        final private String name;
        final private BiFunction<Double, Double, Double> op;

        Oper(String name, BiFunction<Double, Double, Double> op) {
            this.name = name;
            this.op = op;
        }

        public String getName() {
            return name;
        }

        public java.lang.Number apply(Double left, Double right) {
            return op.apply(left, right);
        }
    }

    final private Expr left;
    final private Oper oper;
    final private Expr right;

    public Operation(Expr left, Oper oper, Expr right) {
        this.left = left;
        this.oper = oper;
        this.right = right;
    }

    @Override
    public java.lang.Number caculate() {
        return oper.apply(left.caculate().doubleValue(), right.caculate().doubleValue());
    }

}
