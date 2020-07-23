package org.poormanscastle.rechenkaiser.backend.domain;

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
     * @return a random number that belongs to the range represented by this zahlenraum.
     */
    public int getNumber() {
        return (int) (Math.random() * (getIntervalWidth() + 1));
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
