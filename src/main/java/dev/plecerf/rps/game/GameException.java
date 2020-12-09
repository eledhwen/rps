package dev.plecerf.rps.game;

/**
 * RoundExceptions are game-breaking errors which should bubble-up to the whichever components started the GameLoop.
 *
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class GameException extends Exception
{
    /**
     *
     * @param message
     */
    public GameException(String message)
    {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public GameException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
