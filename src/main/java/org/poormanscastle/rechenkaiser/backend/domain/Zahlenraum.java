package org.poormanscastle.rechenkaiser.backend.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zahlenraum {
    /**
     * inclusive lower border
     */
    private int lowerBorder;
    /**
     * inclusive upper border
     */
    private int upperBorder;

    public final static Zahlenraum HUNDRED = new Zahlenraum(0, 100);

    public final static Zahlenraum TEN = new Zahlenraum(0, 10);

    public final static Zahlenraum HUNDREDTWENTY = new Zahlenraum(0, 120);

    public Zahlenraum(int lowerBorder, int upperBorder) {
        if (upperBorder < lowerBorder) {
            throw new IllegalArgumentException(
                    String.format("upper_border %s is less than lower_border %s!",
                            upperBorder, lowerBorder));
        }
        this.lowerBorder = lowerBorder;
        this.upperBorder = upperBorder;
    }

    /**
     * Numbers are picked with a homogenous probability over the complete number range.
     *
     * @return a random number that belongs to the range represented by this zahlenraum.
     */
    public int getNumber() {
        return (int) (Math.random() * (getIntervalWidth() + 1));
    }

    /**
     * Probability that the returned number n > median of range is 7/8.
     * Thus, use this method if you need a bias for higher numbers in the allowed range,
     * e.g. for a bias towards more demanding drill questions.
     *
     * @return a random number that belongs to the range represented by this zahlenraum.
     */
    public int getHeighestOfThree() {
        return Stream.generate(() -> getNumber()).limit(3).max(Integer::compare).get();
    }

    /**
     * Drastically reduce the probabilities for numbers in the upper and lower fifth
     * of the range.
     * Thus, use this method if you need a bias for numbers in the middle 3 fifths of the range.
     *
     * @return a random number that belongs to the range represented by this zahlenraum.
     */
    public int getMediumOfThree() {
        List<Integer> result = Stream.generate(() -> getNumber()).limit(3)
                .sorted(Comparator.naturalOrder())
                .skip(1)
                .limit(1)
                .collect(Collectors.toList());
        result.stream().forEach(System.out::println);
        return result.get(0);
    }

    /**
     * @param upperLimit
     * @return a number that belongs to the range represented by this zahlenraum
     * which is smaller than the given upperLimit
     */
    public int getNumberLessThanOrEqual(int upperLimit) {
        if (!isNumberInRange(upperLimit)) {
            throw new IllegalArgumentException(String.format("given number %s is not in range", upperLimit));
        } else {
            return (int) (Math.random() * (upperLimit - lowerBorder + 1));
        }
    }

    public int getNumberLessThan(int upperLimit) {
        if (!isNumberInRange(upperLimit)) {
            throw new IllegalArgumentException(String.format("given number %s is not in range", upperLimit));
        } else {
            return (int) (Math.random() * (upperLimit - lowerBorder));
        }
    }

    public boolean isNumberInRange(int number) {
        return lowerBorder <= number && number <= upperBorder;
    }

    public int getIntervalWidth() {
        return upperBorder - lowerBorder;
    }

    public int getLowerBorder() {
        return lowerBorder;
    }

    public void setLowerBorder(int lowerBorder) {
        this.lowerBorder = lowerBorder;
    }

    public int getUpperBorder() {
        return upperBorder;
    }

    public void setUpperBorder(int upperBorder) {
        this.upperBorder = upperBorder;
    }

    @Override
    public String toString() {
        return "Zahlenraum{" +
                "lower_border=" + lowerBorder +
                ", upper_border=" + upperBorder +
                '}';
    }
}
