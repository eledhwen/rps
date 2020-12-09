package dev.plecerf.rps.game;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/08
 */
public enum Action
{
    ROCK,
    PAPER,
    SCISSORS,
    CONCEDE,
    ;

    private Action beats;

    static {
        ROCK.beats = SCISSORS;
        PAPER.beats = ROCK;
        SCISSORS.beats = PAPER;
    }

    public boolean beats(Action other)
    {
        return this.beats == other;
    }
}
