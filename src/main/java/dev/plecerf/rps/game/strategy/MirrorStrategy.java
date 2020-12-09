package dev.plecerf.rps.game.strategy;

import dev.plecerf.rps.game.Action;
import dev.plecerf.rps.game.outcome.Outcome;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class MirrorStrategy implements GameStrategy
{
    private final GameStrategy fallback = new RandomStrategy();
    private Action lastPlayedAction;

    @Override
    public Action play()
    {
        return this.lastPlayedAction == null
            ? this.fallback.play()
            : this.lastPlayedAction
        ;
    }

    @Override
    public void acknowledgeLastPlay(Action lastPlay, Outcome outcome)
    {
        this.lastPlayedAction = lastPlay;
    }
}
