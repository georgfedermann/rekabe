package org.poormanscastle.rechenkaiser.backend.domain;

public class SmallMultiplicationTable {

    private int index;

    private SmallMultiplicationTable(int idx) {
        index = idx;
    }

    public int getIndex() {
        return index;
    }

    public int getFactor1() {
        return index % 11;
    }

    public int getFactor2() {
        return index / 11;
    }

    public int getProduct() {
        return getFactor1() * getFactor2();
    }

    public static SmallMultiplicationTable getRandom() {
        return new SmallMultiplicationTable(Zahlenraum.HUNDREDTWENTY.getNumber());
    }

    public String getPrettyClassic() {
        return String.format("%s · %s = __", getFactor1(), getFactor2());
    }

    public String getPrettyWithBlanks() {
        return String.format("%s · __ = %s", getFactor1(), getProduct());
    }

    public String toString() {
        return String.format("%s · %s = %s", getFactor1(), getFactor2(), getProduct());
    }

    static SmallMultiplicationTable get(int index) {
        if (index < 0 || index > 120) {
            throw new IllegalArgumentException("index must be in [0;120]");
        } else {
            return new SmallMultiplicationTable(index);
        }
    }
}
