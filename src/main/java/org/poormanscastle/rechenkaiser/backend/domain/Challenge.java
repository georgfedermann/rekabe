package org.poormanscastle.rechenkaiser.backend.domain;

/**
 * This type represents a challenge for the user which they can
 * respond to by solving a basic arithmetic equation, or so.
 */
public class Challenge {

    private String challengeId;
    private ChallengeType challengeType;
    private Zahlenraum zahlenraum = new Zahlenraum(0, 100);

    /**
     * a challenge String that can be displayed to the user.
     */
    private String pretty;
    private int expectedResult;

    public Challenge(String challengeId, String pretty, int expectedResult) {
        this.challengeId = challengeId;
        this.pretty = pretty;
        this.expectedResult = expectedResult;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }

    public Zahlenraum getZahlenraum() {
        return zahlenraum;
    }

    public void setZahlenraum(Zahlenraum zahlenraum) {
        this.zahlenraum = zahlenraum;
    }

    public String getPretty() {
        return pretty;
    }

    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    public int getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(int expectedResult) {
        this.expectedResult = expectedResult;
    }
}
