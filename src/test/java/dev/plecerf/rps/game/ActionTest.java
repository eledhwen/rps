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
        Assertions.assertTrue(ROCK.beats(SCISSORS));
        Assertions.assertTrue(SCISSORS.beats(PAPER));
        Assertions.assertTrue(PAPER.beats(ROCK));
    }

    @Test
    void beatenTest()
    {
        Assertions.assertFalse(SCISSORS.beats(ROCK));
        Assertions.assertFalse(PAPER.beats(SCISSORS));
        Assertions.assertFalse(ROCK.beats(PAPER));
    }

    @Test
    void drawTest()
    {
        Assertions.assertFalse(ROCK.beats(ROCK));
        Assertions.assertFalse(SCISSORS.beats(SCISSORS));
        Assertions.assertFalse(PAPER.beats(PAPER));
    }
}
