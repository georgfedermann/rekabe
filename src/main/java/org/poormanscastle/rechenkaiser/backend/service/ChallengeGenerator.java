package org.poormanscastle.rechenkaiser.backend.service;

import org.poormanscastle.rechenkaiser.backend.domain.Challenge;
import org.poormanscastle.rechenkaiser.backend.domain.ChallengeType;
import org.poormanscastle.rechenkaiser.backend.domain.SmallMultiplicationTable;
import org.poormanscastle.rechenkaiser.backend.domain.Zahlenraum;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChallengeGenerator {

    public Challenge generateChallenge(ChallengeType challengeType, Optional<Zahlenraum> zahlenraum) {
        switch (challengeType) {
            case SIMPLE_SUM:
                return generateSimpleSumChallenge(zahlenraum.orElseGet(() -> Zahlenraum.HUNDRED));
            case SIMPLE_SUM_WITH_BLANKS:
                return generateSimpleSumWithBlanks(zahlenraum.orElseGet(() -> Zahlenraum.HUNDRED));
            case SMALL_MULTIPLICATION_TABLE:
                return generateSimpleSmallMultiplicationTable();
            default:
                throw new UnsupportedOperationException();
        }
    }

    private Challenge generateSimpleSmallMultiplicationTable() {
        SmallMultiplicationTable smt = SmallMultiplicationTable.getRandom();
        Challenge challenge = new Challenge(
                UUID.randomUUID().toString(), smt.getPrettyClassic(), smt.getProduct());
        challenge.setChallengeType(ChallengeType.SMALL_MULTIPLICATION_TABLE);
        return challenge;
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
