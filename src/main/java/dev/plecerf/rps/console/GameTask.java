package dev.plecerf.rps.console;

import com.noleme.console.Console;
import com.noleme.console.input.Input;
import com.noleme.console.input.InputType;
import com.noleme.console.output.Output;
import com.noleme.console.task.Task;
import com.noleme.console.task.description.ParameterDescription;
import com.noleme.console.task.description.TaskDescription;
import com.noleme.console.task.description.preparator.EnumPreparator;
import com.noleme.console.task.description.preparator.IntegerPreparator;
import com.noleme.console.task.description.validator.IntegerValidator;
import com.noleme.console.task.exception.TaskException;
import dev.plecerf.rps.game.GameException;
import dev.plecerf.rps.game.GameLoop;
import dev.plecerf.rps.game.strategy.GameStrategy;
import dev.plecerf.rps.game.strategy.MarkovChainStrategy;
import dev.plecerf.rps.game.strategy.MirrorStrategy;
import dev.plecerf.rps.game.strategy.RandomStrategy;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/08
 */
public class GameTask implements Task
{
    @Override
    public TaskDescription setup()
    {
        return new TaskDescription()
            .setDescription("Starts a game of Rock Paper Scissors.")
            .addParameter("player", "player", InputType.OPTIONAL, "anon")
            .addParameter("strategy", new ParameterDescription("strategy", InputType.OPTIONAL)
                .setDefaultValue(Strategy.MARKOV_CHAIN.name())
                .setPreparator(new EnumPreparator<>(Strategy.class))
            )
            .addParameter("rounds", new ParameterDescription("rounds", InputType.OPTIONAL)
                .setDefaultValue("3")
                .setPreparator(new IntegerPreparator())
                .setValidator(new IntegerValidator.Higher(0))
            )
        ;
    }

    @Override
    public void run(Console console, Input input, Output output) throws TaskException
    {
        String name = input.parameters.getString("player");
        GameStrategy strategy = input.parameters.getEnum("strategy", Strategy.class).strategy;
        int rounds = input.parameters.getInteger("rounds");

        try {
            new GameLoop(name, strategy, rounds).start(output);
        }
        catch (GameException e) {
            throw new TaskException(e.getMessage(), e);
        }
    }

    private enum Strategy
    {
        RANDOM(new RandomStrategy()),
        MIRROR(new MirrorStrategy()),
        MARKOV_CHAIN(new MarkovChainStrategy()),
        ;

        final GameStrategy strategy;

        Strategy(GameStrategy strategy)
        {
            this.strategy = strategy;
        }
    }
}
