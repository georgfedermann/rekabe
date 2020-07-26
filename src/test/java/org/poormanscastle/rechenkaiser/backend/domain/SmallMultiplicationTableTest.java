package org.poormanscastle.rechenkaiser.backend.domain;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class SmallMultiplicationTableTest {

    SmallMultiplicationTable smallMultiplicationTable;

    @BeforeEach
    public void init() {
        smallMultiplicationTable = SmallMultiplicationTable.get(106);
    }

    @Test
    void getIndex() {
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.getIndex()).isEqualTo(106);
    }

    @Test
    void illegalIndex() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SmallMultiplicationTable.get(-1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SmallMultiplicationTable.get(121);
        });
        SmallMultiplicationTable.get(0);
        SmallMultiplicationTable.get(120);
    }

    @Test
    void getFactor1() {
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.getFactor1()).isEqualTo(7);
    }

    @Test
    void getFactor2() {
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.getFactor2()).isEqualTo(9);
    }

    @Test
    void getProduct() {
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.getProduct()).isEqualTo(63);
    }

    @Test
    void getRandom() {
        smallMultiplicationTable = SmallMultiplicationTable.getRandom();
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.getIndex()).isIn(
                Range.range(0, BoundType.CLOSED, 120, BoundType.CLOSED));
    }

    @Test
    void getPrettyClassic() {
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.getPrettyClassic()).isEqualTo("7 · 9 = __");
    }

    @Test
    void getPrettyWithBlanks() {
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.getPrettyWithBlanks()).isEqualTo("7 · __ = 63");
    }

    @Test
    void toStringTest() {
        assertThat(smallMultiplicationTable).isNotNull();
        assertThat(smallMultiplicationTable.toString()).isEqualTo("7 · 9 = 63");
    }
}