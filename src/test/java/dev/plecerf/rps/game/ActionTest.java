package dev.plecerf.rps.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dev.plecerf.rps.game.Action.*;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class ActionTest
{
    @Test
    void beatTest()
    {
        Assertions.assertTrue(ROCK.beats(SCISSOR));
        Assertions.assertTrue(SCISSOR.beats(PAPER));
        Assertions.assertTrue(PAPER.beats(ROCK));
    }

    @Test
    void beatenTest()
    {
        Assertions.assertFalse(SCISSOR.beats(ROCK));
        Assertions.assertFalse(PAPER.beats(SCISSOR));
        Assertions.assertFalse(ROCK.beats(PAPER));
    }

    @Test
    void drawTest()
    {
        Assertions.assertFalse(ROCK.beats(ROCK));
        Assertions.assertFalse(SCISSOR.beats(SCISSOR));
        Assertions.assertFalse(PAPER.beats(PAPER));
    }
}
