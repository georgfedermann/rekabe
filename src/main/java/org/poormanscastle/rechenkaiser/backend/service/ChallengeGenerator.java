package org.poormanscastle.rechenkaiser.backend.service;

import org.poormanscastle.rechenkaiser.backend.domain.Challenge;
import org.poormanscastle.rechenkaiser.backend.domain.ChallengeType;
import org.poormanscastle.rechenkaiser.backend.domain.Zahlenraum;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChallengeGenerator {

    public Challenge generateChallenge(Zahlenraum zahlenraum, ChallengeType challengeType) {
        switch (challengeType) {
            case SIMPLE_SUM:
                return generateSimpleSumChallenge(zahlenraum);
            case SIMPLE_SUM_WITH_BLANKS:
                return generateSimpleSumWithBlanks(zahlenraum);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private Challenge generateSimpleSumWithBlanks(Zahlenraum zahlenraum) {
        int sum = zahlenraum.getHeighestOfThree();
        int summand1 = zahlenraum.getNumberLessThanOrEqual(sum);
        int summand2 = sum - summand1;
        String pretty = String.format("%s + __ = %s", summand1, sum);
        Challenge challenge = new Challenge(UUID.randomUUID().toString(), pretty, summand2);
        challenge.setChallengeType(ChallengeType.SIMPLE_SUM_WITH_BLANKS);
        return challenge;
    }

    private Challenge generateSimpleSumChallenge(Zahlenraum zahlenraum) {
        int sum = zahlenraum.getHeighestOfThree();
        int summand1 = zahlenraum.getNumberLessThanOrEqual(sum);
        int summand2 = sum - summand1;
        String pretty = String.format("%s + %s = __", summand1, summand2);
        Challenge challenge = new Challenge(UUID.randomUUID().toString(), pretty, sum);
        challenge.setChallengeType(ChallengeType.SIMPLE_SUM);
        return challenge;
    }

}
