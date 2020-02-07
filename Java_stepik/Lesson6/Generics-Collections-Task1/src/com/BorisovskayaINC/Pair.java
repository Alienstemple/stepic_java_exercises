package com.BorisovskayaINC;

import java.util.Objects;

public class Pair<T, U> {

    private T valT;
    private U valU;

    private Pair(T valT, U valU) { // закрытый конструктор
        this.valT = valT;
        this.valU = valU;
    }

    public static <T, U> Pair<T, U> of(T valT, U valU) { // открытый метод для созд. экземпл.
        return new Pair<>(valT, valU);
    }

    public T getFirst() {
        return valT;
    }

    public U getSecond() {
        return valU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;   // вырожденный случай
        if (o == null || getClass() != o.getClass()) return false; // если объект нулевой, проверить его принадлежность классу
        Pair<?, ?> pair = (Pair<?, ?>) o;       // привели object к нашему типу Pair
        return Objects.equals(valT, pair.valT) && // проверяем равенство полей
                Objects.equals(valU, pair.valU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valT, valU);
    }
}
