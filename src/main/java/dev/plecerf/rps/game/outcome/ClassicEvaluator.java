package dev.plecerf.rps.game.outcome;

import dev.plecerf.rps.game.Action;
import dev.plecerf.rps.game.Counters;

import static dev.plecerf.rps.game.outcome.Outcome.*;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class ClassicEvaluator implements GameEvaluator
{
    @Override
    public Outcome evaluate(Action playerAction, Action cpuAction, Counters counters)
    {
        /* If actions are the same, it's a draw round, we don't increment the round counter in such situations */
        if (playerAction == cpuAction)
            return DRAW;

        /* Otherwise, we determine who won and increment the round counter */
        counters.incrementRounds();

        if (playerAction.beats(cpuAction))
        {
            counters.incrementPlayerScore();
            return WON;
        }

        counters.incrementCPUScore();
        return LOST;
    }

    @Override
    public Outcome evaluate(Counters counters)
    {
        if (counters.player() > counters.cpu())
            return WON;
        else if (counters.player() == counters.cpu())
            return DRAW;
        return LOST;
    }
}
