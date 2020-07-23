package org.poormanscastle.rechenkaiser.backend.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;

class ZahlenraumTest {

    @Test
    void getNumber() {
        Zahlenraum zr = Zahlenraum.TEN;
        assertThat(zr.getIntervalWidth()).isEqualTo(10);
        Set<Integer> numbers = new HashSet<>();
        IntStream.range(0, 100).forEach(x -> numbers.add(zr.getNumber()));
        assertThat(numbers.size()).isEqualTo(11);
        assertThat(numbers).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void getNumberLessThanOrEqual() {
        Zahlenraum zr = Zahlenraum.TEN;
        Set<Integer> numbers = new HashSet<>();
        IntStream.range(0, 100).forEach(x -> numbers.add(zr.getNumberLessThanOrEqual(6)));
        assertThat(numbers.size()).isEqualTo(7);
        assertThat(numbers).containsExactly(0, 1, 2, 3, 4, 5, 6);
    }

    @Test
    void getNumberLessThan() {
        Zahlenraum zr = Zahlenraum.TEN;
        Set<Integer> numbers = new HashSet<>();
        IntStream.range(0, 100).forEach(x -> numbers.add(zr.getNumberLessThan(6)));
        assertThat(numbers.size()).isEqualTo(6);
        assertThat(numbers).containsExactly(0, 1, 2, 3, 4, 5);
    }
}