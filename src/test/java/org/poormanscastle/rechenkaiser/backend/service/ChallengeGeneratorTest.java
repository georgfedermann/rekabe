package org.poormanscastle.rechenkaiser.backend.service;

import org.junit.jupiter.api.Test;
import org.poormanscastle.rechenkaiser.backend.domain.Challenge;
import org.poormanscastle.rechenkaiser.backend.domain.ChallengeType;
import org.poormanscastle.rechenkaiser.backend.domain.Zahlenraum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

class ChallengeGeneratorTest {

    private final static Logger logger = LoggerFactory.getLogger(ChallengeGeneratorTest.class);

    @Test
    public void generateSimpleSumChallengeTest() {
        Challenge challenge = new ChallengeGenerator().generateChallenge(
                Zahlenraum.HUNDRED, ChallengeType.SIMPLE_SUM);
        assertThat(challenge.getChallengeId()).isNotEmpty();
        assertThat(challenge.getPretty()).isNotEmpty();
        assertThat(challenge.getPretty()).contains("=");
        assertThat(challenge.getExpectedResult()).isAtLeast(Zahlenraum.HUNDRED.getLowerBorder());
        assertThat(challenge.getExpectedResult()).isAtMost(Zahlenraum.HUNDRED.getUpperBorder());
        assertThat(challenge.getChallengeType()).isEqualTo(ChallengeType.SIMPLE_SUM);
        logger.debug("challenge pretty = {}, expectedResult = {}.",
                challenge.getPretty(), challenge.getExpectedResult());
    }

    @Test
    public void numberGeneratorTest() {
        Zahlenraum zahlenraum = Zahlenraum.TEN;
        Set<Integer> results = new HashSet<>();
        for (int c = 0; c < 1000; c++) {
            results.add(zahlenraum.getNumber());
        }
        assertThat(results).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

}