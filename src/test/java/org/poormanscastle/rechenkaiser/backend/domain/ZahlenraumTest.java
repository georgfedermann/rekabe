package org.poormanscastle.rechenkaiser.backend.domain;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

class ZahlenraumTest {

    private final static Logger logger = LoggerFactory.getLogger(ZahlenraumTest.class);

    @Test
    void getNumber() {
        Zahlenraum zr = Zahlenraum.TEN;
        assertThat(zr.getIntervalWidth()).isEqualTo(10);
        Set<Integer> numbers = new HashSet<>();
        IntStream.range(0, 100)
                .forEach(x -> numbers.add(zr.getNumber()));
        assertThat(numbers.size()).isEqualTo(11);
        assertThat(numbers).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void getNumberLessThanOrEqual() {
        Zahlenraum zr = Zahlenraum.TEN;
        Set<Integer> numbers = new HashSet<>();
        IntStream.range(0, 100)
                .forEach(x -> numbers.add(zr.getNumberLessThanOrEqual(6)));
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

    @Test
    void getHeighestOfThreeStats() {
        final int NUMBER_OF_EXPERIMENTS = 101_000;
        Zahlenraum zr = Zahlenraum.HUNDRED;
        List<Integer> counter = Stream.generate(() -> 0)
                .limit(101)
                .collect(Collectors.toList());
        assertThat(counter.size()).isEqualTo(101);
        IntStream.range(0, NUMBER_OF_EXPERIMENTS).forEach(x -> {
            int number = zr.getHeighestOfThree();
            counter.set(number, counter.get(number) + 1);
        });
        assertThat(counter.stream().mapToInt(Integer::valueOf).sum()).isEqualTo(NUMBER_OF_EXPERIMENTS);
        IntStream.range(0, counter.size()).forEach(x -> logger.debug("{}\t->\t{}", x, counter.get(x)));
        int countLowerHalf = counter.stream()
                .limit(50)
                .mapToInt(Integer::valueOf)
                .sum();
        int countUpperHalf = counter.stream()
                .skip(50)
                .limit(50)
                .mapToInt(Integer::valueOf)
                .sum();
        double quotient = (double) countUpperHalf / countLowerHalf;
        logger.debug("#lowerHalf: {}; #upperHalf: {}; sum: {}; 101: {}, quotient: {}",
                countLowerHalf, countUpperHalf, countLowerHalf + countUpperHalf, counter.get(100), quotient);
        // simple check that probability of numbers being picked in upper half is 7/8
        assertThat(Math.abs(quotient - 7)).isLessThan(0.5);
    }

    @Test
    void getNumberStats() {
        final int NUMBER_OF_EXPERIMENTS = 101_000;
        Zahlenraum zr = Zahlenraum.HUNDRED;
        List<Integer> counter = Stream.generate(() -> 0)
                .limit(101)
                .collect(Collectors.toList());
        assertThat(counter.size()).isEqualTo(101);
        IntStream.range(0, NUMBER_OF_EXPERIMENTS)
                .forEach(x -> {
                    int number = zr.getNumber();
                    counter.set(number, counter.get(number) + 1);
                });
        assertThat(counter.stream().mapToInt(Integer::valueOf).sum()).isEqualTo(NUMBER_OF_EXPERIMENTS);
        int countLowerHalf = counter.stream()
                .limit(50)
                .mapToInt(Integer::valueOf)
                .sum();
        int countUpperHalf = counter.stream()
                .skip(50)
                .limit(50)
                .mapToInt(Integer::valueOf)
                .sum();
        double quotient = (double) countUpperHalf / countLowerHalf;
        logger.debug("#lowerHalf: {}; #upperHalf: {}; sum: {}; 101: {}, quotient: {}",
                countLowerHalf, countUpperHalf, countLowerHalf + countUpperHalf, counter.get(100), quotient);
        assertThat(countLowerHalf + countUpperHalf + counter.get(100)).isEqualTo(NUMBER_OF_EXPERIMENTS);
        // simple check to assert homogenous probability distribution for lower and upper half of interval.
        assertThat(Math.abs(quotient - 1)).isLessThan(0.02);
    }

    @Test
    void getMediumOfThree() {
        final int NUMBER_OF_EPERIMENTS = 101_000;
        Zahlenraum zr = Zahlenraum.HUNDRED;
        List<Integer> counter = Stream.iterate(0, i -> i)
                .limit(101)
                .collect(Collectors.toList());
        assertThat(counter.size()).isEqualTo(101);
        IntStream.range(0, NUMBER_OF_EPERIMENTS)
                .forEach(x -> {
                    int number = zr.getMediumOfThree();
                    counter.set(number, counter.get(number) + 1);
                });
        assertThat(counter.stream().mapToInt(Integer::valueOf).sum()).isEqualTo(NUMBER_OF_EPERIMENTS);
        IntStream.range(0, counter.size()).forEach(x -> logger.debug("{}\t->\t{}", x, counter.get(x)));
        // TODO implement the assertion that numbers in lowest and highest fifth are
        //  under represented in the result by the correct quotient.
    }
}