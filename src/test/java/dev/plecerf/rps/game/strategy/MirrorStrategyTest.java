package dev.plecerf.rps.game.strategy;

import dev.plecerf.rps.game.Action;
import dev.plecerf.rps.game.outcome.Outcome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dev.plecerf.rps.game.Action.*;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class MirrorStrategyTest
{
    @Test
    void test()
    {
        Action[] plays = {SCISSOR, ROCK, PAPER, ROCK, PAPER, SCISSOR, PAPER};
        GameStrategy strategy = new MirrorStrategy();

        /* First should be random, so we ignore that */
        strategy.play();

        for (Action play : plays)
        {
            strategy.acknowledgeLastPlay(play, Outcome.WON);
            Assertions.assertEquals(play, strategy.play());
        }
    }
}
