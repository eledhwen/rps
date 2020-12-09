package dev.plecerf.rps.game.outcome;

import dev.plecerf.rps.game.Action;
import dev.plecerf.rps.game.Counters;
import dev.plecerf.rps.game.GameException;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public interface GameEvaluator
{
    /**
     *
     * @param playerAction
     * @param cpuAction
     * @param counters
     * @return
     * @throws GameException
     */
    Outcome evaluate(Action playerAction, Action cpuAction, Counters counters) throws GameException;

    /**
     *
     * @param counters
     * @return
     * @throws GameException
     */
    Outcome evaluate(Counters counters) throws GameException;
}
