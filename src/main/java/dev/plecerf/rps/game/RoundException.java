package dev.plecerf.rps.game;

/**
 * RoundExceptions are non-game-breaking errors which should "reset" the game loop.
 *
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class RoundException extends Exception
{
    /**
     *
     * @param message
     */
    public RoundException(String message)
    {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public RoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
