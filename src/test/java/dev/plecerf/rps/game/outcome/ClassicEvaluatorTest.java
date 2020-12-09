package dev.plecerf.rps.game.outcome;

import dev.plecerf.rps.game.Counters;
import dev.plecerf.rps.game.GameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dev.plecerf.rps.game.outcome.Outcome.*;
import static dev.plecerf.rps.game.Action.*;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class ClassicEvaluatorTest
{
    @Test
    void testOutcome() throws GameException
    {
        GameEvaluator evaluator = new ClassicEvaluator();

        var counters = new Counters();
        Outcome outcome;

        outcome = evaluator.evaluate(ROCK, ROCK, counters);
        computeOutcomeTest(DRAW, outcome, counters, 0, 0, 0);

        outcome = evaluator.evaluate(ROCK, PAPER, counters);
        computeOutcomeTest(LOST, outcome, counters, 1, 0, 1);

        outcome = evaluator.evaluate(ROCK, SCISSOR, counters);
        computeOutcomeTest(WON, outcome, counters, 2, 1, 1);

        outcome = evaluator.evaluate(PAPER, ROCK, counters);
        computeOutcomeTest(WON, outcome, counters, 3, 2, 1);

        outcome = evaluator.evaluate(PAPER, PAPER, counters);
        computeOutcomeTest(DRAW, outcome, counters, 3, 2, 1);

        outcome = evaluator.evaluate(PAPER, SCISSOR, counters);
        computeOutcomeTest(LOST, outcome, counters, 4, 2, 2);

        outcome = evaluator.evaluate(SCISSOR, ROCK, counters);
        computeOutcomeTest(LOST, outcome, counters, 5, 2, 3);

        outcome = evaluator.evaluate(SCISSOR, PAPER, counters);
        computeOutcomeTest(WON, outcome, counters, 6, 3, 3);

        outcome = evaluator.evaluate(SCISSOR, SCISSOR, counters);
        computeOutcomeTest(DRAW, outcome, counters, 6, 3, 3);
    }

    private static void computeOutcomeTest(Outcome expected, Outcome observed, Counters counters, int round, int playerScore, int cpuScore)
    {
        Assertions.assertEquals(expected, observed);
        Assertions.assertEquals(round, counters.rounds());
        Assertions.assertEquals(playerScore, counters.player());
        Assertions.assertEquals(cpuScore, counters.cpu());
    }

    @Test
    void testScore() throws GameException
    {
        GameEvaluator evaluator = new ClassicEvaluator();

        var winning = new Counters()
            .incrementPlayerScore()
            .incrementPlayerScore()
            .incrementCPUScore()
        ;

        var losing = new Counters()
            .incrementCPUScore()
            .incrementPlayerScore()
            .incrementCPUScore()
        ;

        var draw = new Counters()
            .incrementCPUScore()
            .incrementPlayerScore()
        ;

        Assertions.assertEquals(Outcome.WON, evaluator.evaluate(winning));
        Assertions.assertEquals(Outcome.LOST, evaluator.evaluate(losing));
        Assertions.assertEquals(Outcome.DRAW, evaluator.evaluate(draw));
    }
}
