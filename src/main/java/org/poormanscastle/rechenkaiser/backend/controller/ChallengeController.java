package org.poormanscastle.rechenkaiser.backend.controller;

import org.poormanscastle.rechenkaiser.backend.domain.Challenge;
import org.poormanscastle.rechenkaiser.backend.domain.ChallengeType;
import org.poormanscastle.rechenkaiser.backend.domain.Zahlenraum;
import org.poormanscastle.rechenkaiser.backend.service.ChallengeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ChallengeController {

    private ChallengeGenerator challengeGenerator;

    @Autowired
    public ChallengeController(ChallengeGenerator challengeGenerator) {
        this.challengeGenerator = challengeGenerator;
    }

    @GetMapping("/rest/challenge/{challengeType}")
    public Challenge getChallenge(@PathVariable String challengeType) {
        Challenge challenge = challengeGenerator.generateChallenge(
                ChallengeType.valueOf(challengeType.toUpperCase()), Optional.of(Zahlenraum.HUNDRED));
        return challenge;
    }
}
