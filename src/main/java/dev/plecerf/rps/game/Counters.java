package dev.plecerf.rps.game;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public final class Counters
{
    private int rounds = 0;
    private int player = 0;
    private int cpu = 0;

    public int rounds()
    {
        return this.rounds;
    }

    public int player()
    {
        return this.player;
    }

    public int cpu()
    {
        return this.cpu;
    }

    public Counters incrementRounds()
    {
        this.rounds += 1;
        return this;
    }

    public Counters incrementPlayerScore()
    {
        this.player += 1;
        return this;
    }

    public Counters incrementCPUScore()
    {
        this.cpu += 1;
        return this;
    }

    public String getScoreString()
    {
        return this.player+" - "+this.cpu;
    }
}
