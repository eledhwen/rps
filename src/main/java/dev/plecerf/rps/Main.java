package dev.plecerf.rps;

import com.noleme.console.Console;
import com.noleme.console.ConsoleRunner;
import dev.plecerf.rps.console.GameTask;

/**
 *
 */
public class Main
{
    public static void main(String[] args)
    {
        int status = ConsoleRunner.run(new Console()
            .setTask("play", new GameTask())
        , args, true);

        System.exit(status);
    }
}
