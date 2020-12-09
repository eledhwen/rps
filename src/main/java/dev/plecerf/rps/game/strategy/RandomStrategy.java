package dev.plecerf.rps.game.strategy;

import dev.plecerf.rps.game.Action;
import dev.plecerf.rps.game.outcome.Outcome;

import static dev.plecerf.rps.game.Action.*;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class RandomStrategy implements GameStrategy
{
    private static final Action[] possibleActions = {ROCK, PAPER, SCISSOR};

    @Override
    public Action play()
    {
        return possibleActions[random(0, possibleActions.length)];
    }

    @Override
    public void acknowledgeLastPlay(Action lastPlay, Outcome outcome)
    {
        /* This is random, so we don't acknowledge anything, heh. */
    }

    /**
     * Returns a random number between min inclusive and max exclusive.
     *
     * @param min
     * @param max
     * @return
     */
    private static int random(int min, int max)
    {
        return (int) (Math.random() * (max - min)) + min;
    }
}
