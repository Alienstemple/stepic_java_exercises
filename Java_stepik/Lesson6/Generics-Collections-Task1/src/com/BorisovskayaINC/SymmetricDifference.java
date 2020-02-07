package com.BorisovskayaINC;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class SymmetricDifference {
    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> set3 = new LinkedHashSet<>(set1); // set3 - объединение
        set3.addAll(set2);
        Set<T> set4 = new LinkedHashSet<>(set1); // set4 - пересечение
        set4.retainAll(set2);
        Set<T> set5 = new LinkedHashSet<>(set3); // set5 - симметрич разность - пересеч\объед
        set5.removeAll(set4);
        return set5;
    }
}
