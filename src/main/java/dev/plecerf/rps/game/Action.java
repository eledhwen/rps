package dev.plecerf.rps.game;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/08
 */
public enum Action
{
    ROCK,
    PAPER,
    SCISSOR,
    CONCEDE,
    ;

    private Action beats;

    static {
        ROCK.beats = SCISSOR;
        PAPER.beats = ROCK;
        SCISSOR.beats = PAPER;
    }

    public boolean beats(Action other)
    {
        return this.beats == other;
    }
}
