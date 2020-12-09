package dev.plecerf.rps.game.strategy;

import dev.plecerf.rps.game.Action;
import dev.plecerf.rps.game.outcome.Outcome;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public interface GameStrategy
{
    /**
     *
     * @return
     */
    Action play();

    /**
     *
     * @param lastPlay
     * @param outcome
     * @return
     */
    void acknowledgeLastPlay(Action lastPlay, Outcome outcome);
}
