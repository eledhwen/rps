package dev.plecerf.rps.game;

import com.noleme.commons.container.Pair;
import com.noleme.commons.string.Strings;
import com.noleme.console.output.Output;
import com.noleme.console.output.style.ConsoleColor;
import dev.plecerf.rps.game.outcome.ClassicEvaluator;
import dev.plecerf.rps.game.outcome.GameEvaluator;
import dev.plecerf.rps.game.outcome.Outcome;
import dev.plecerf.rps.game.strategy.GameStrategy;

import java.util.Arrays;
import java.util.Map;

import static com.noleme.console.output.style.ConsoleColor.*;
import static dev.plecerf.rps.game.Action.*;
import static dev.plecerf.rps.game.outcome.Outcome.*;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class GameLoop
{
    private final GameEvaluator evaluator;
    private final GameStrategy strategy;
    private final String playerName;
    private final int rounds;

    private static final String cpuName = "CPU";
    /* FIXME: this should be handled via some configuration/I18n system */
    private static final Map<Outcome, Pair<String, ConsoleColor>> outcomes = Map.of(
        DRAW, new Pair<>("Draw, re-match.", CYAN),
        WON, new Pair<>("Well done!", GREEN),
        LOST, new Pair<>("Whoops.", YELLOW)
    );
    private static final Map<Outcome, Pair<String, ConsoleColor>> finalOutcomes = Map.of(
        DRAW, new Pair<>("Draw!", YELLOW),
        WON, new Pair<>("You win!", GREEN),
        LOST, new Pair<>("You lose!", RED)
    );

    /**
     *
     * @param playerName
     * @param strategy
     * @param rounds
     */
    public GameLoop(String playerName, GameStrategy strategy, int rounds)
    {
        this.evaluator = new ClassicEvaluator();
        this.strategy = strategy;
        this.playerName = playerName;
        this.rounds = rounds;
    }

    /**
     *
     * @param output
     * @throws GameException
     */
    public void start(Output output) throws GameException
    {
        output.line.info("Starting a game with "+this.rounds+" rounds");
        output.println();

        var ioConsole = System.console();

        var counters = new Counters();

        /* The game-loop runs until the target round count is reached or the player concedes */
        while (counters.rounds() < this.rounds)
        {
            try {
                /* We prompt the player */
                output.print(getPlayerLine(output, counters.rounds(), this.playerName, null));
                String play = ioConsole.readLine();

                /* ..and attempt to understand it */
                Action playerAction = getPlayerAction(play);

                /* If the action is a concede, we crash the whole party */
                if (playerAction == CONCEDE)
                {
                    output.block.block("You conceded defeat, "+cpuName+" won!", "Booh.", YELLOW);
                    return;
                }
                /* Otherwise CPU makes its play, then we compare the results */
                else {
                    /* The CPU's strategy produces an action */
                    Action cpuAction = this.strategy.play();
                    output.println(getPlayerLine(output, counters.rounds(), cpuName, cpuAction.name()));

                    /* We compute the round's outcome */
                    Outcome outcome = this.evaluator.evaluate(playerAction, cpuAction, counters);
                    printCountersForOutcome(output, counters, outcome, outcomes);

                    /* Finally, the CPU acknowledges the last player action and its outcome */
                    this.strategy.acknowledgeLastPlay(playerAction, outcome);
                }
            }
            catch (RoundException e) {
                output.block.error(e.getMessage());
            }
        }

        /* Once we reached the target round count, we check for final scores */
        Outcome finalOutcome = this.evaluator.evaluate(counters);
        printCountersForOutcome(output, counters, finalOutcome, finalOutcomes);
    }

    /**
     *
     * @param output
     * @param round
     * @param player
     * @param action
     * @return
     */
    private static String getPlayerLine(Output output, int round, String player, String action)
    {
        return output.color.wrap(Strings.padRight("#"+(round+1), 5), CYAN)
            + player+"'s play: "
            + (action != null ? action : "")
        ;
    }

    /**
     *
     * @param play
     * @return
     * @throws RoundException
     */
    private static Action getPlayerAction(String play) throws RoundException
    {
        try {
            return Action.valueOf(play.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new RoundException("Invalid action "+play+", valid actions are "+ Arrays.toString(Action.values()), e);
        }
    }

    /**
     *
     * @param output
     * @param counters
     * @param outcome
     * @param referential
     */
    private static void printCountersForOutcome(Output output, Counters counters, Outcome outcome, Map<Outcome, Pair<String, ConsoleColor>> referential)
    {
        output.block.block("Score: "+counters.getScoreString(), referential.get(outcome).first, referential.get(outcome).second);
    }
}
